/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.transaction;

import javax.transaction.Status;
import javax.transaction.Synchronization;
import javax.transaction.SystemException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import org.hibernate.TransactionException;
import org.hibernate.jdbc.JDBCContext;
import org.hibernate.util.JTAHelper;

/**
 * Implements a basic transaction strategy for CMT transactions. All work is
 * done in the context of the container managed transaction.
 * <p/>
 * The term 'CMT' is potentially misleading here; the pertinent point simply
 * being that the transactions are being managed by something other than the
 * Hibernate transaction mechanism.
 *
 * @author Gavin King
 */
public class CMTTransaction implements Transaction {

	private static final Logger log = LoggerFactory.getLogger(CMTTransaction.class);

	protected final JDBCContext jdbcContext;
	protected final TransactionFactory.Context transactionContext;

	private boolean begun;

	public CMTTransaction(JDBCContext jdbcContext, TransactionFactory.Context transactionContext) {
		this.jdbcContext = jdbcContext;
		this.transactionContext = transactionContext;
	}

	/**
	 * {@inheritDoc}
	 */
	public void begin() throws HibernateException {
		if (begun) {
			return;
		}

		log.debug("begin");
		
		boolean synchronization = jdbcContext.registerSynchronizationIfPossible();

		if ( !synchronization ) {
			throw new TransactionException("Could not register synchronization for container transaction");
		}

		begun = true;
		
		jdbcContext.afterTransactionBegin(this);
	}

	/**
	 * {@inheritDoc}
	 */
	public void commit() throws HibernateException {
		if (!begun) {
			throw new TransactionException("Transaction not successfully started");
		}

		log.debug("commit");

		boolean flush = !transactionContext.isFlushModeNever() &&
		        !transactionContext.isFlushBeforeCompletionEnabled();

		if (flush) {
			transactionContext.managedFlush(); //if an exception occurs during flush, user must call rollback()
		}

		begun = false;

	}

	/**
	 * {@inheritDoc}
	 */
	public void rollback() throws HibernateException {
		if (!begun) {
			throw new TransactionException("Transaction not successfully started");
		}

		log.debug("rollback");

		try {
			getTransaction().setRollbackOnly();
		}
		catch (SystemException se) {
			log.error("Could not set transaction to rollback only", se);
			throw new TransactionException("Could not set transaction to rollback only", se);
		}

		begun = false;

	}

	/**
	 * Getter for property 'transaction'.
	 *
	 * @return Value for property 'transaction'.
	 */
	public javax.transaction.Transaction getTransaction() throws SystemException {
		return transactionContext.getFactory().getTransactionManager().getTransaction();
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean isActive() throws TransactionException {

		if (!begun) return false;

		final int status;
		try {
			status = getTransaction().getStatus();
		}
		catch (SystemException se) {
			log.error("Could not determine transaction status", se);
			throw new TransactionException("Could not determine transaction status: ", se);
		}
		if (status==Status.STATUS_UNKNOWN) {
			throw new TransactionException("Could not determine transaction status");
		}
		else {
			return status==Status.STATUS_ACTIVE;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean wasRolledBack() throws TransactionException {

		if (!begun) return false;

		final int status;
		try {
			status = getTransaction().getStatus();
		}
		catch (SystemException se) {
			log.error("Could not determine transaction status", se);
			throw new TransactionException("Could not determine transaction status", se);
		}
		if (status==Status.STATUS_UNKNOWN) {
			throw new TransactionException("Could not determine transaction status");
		}
		else {
			return JTAHelper.isRollback(status);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean wasCommitted() throws TransactionException {

		if ( !begun ) return false;

		final int status;
		try {
			status = getTransaction().getStatus();
		}
		catch (SystemException se) {
			log.error("Could not determine transaction status", se);
			throw new TransactionException("Could not determine transaction status: ", se);
		}
		if (status==Status.STATUS_UNKNOWN) {
			throw new TransactionException("Could not determine transaction status");
		}
		else {
			return status==Status.STATUS_COMMITTED;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void registerSynchronization(Synchronization sync) throws HibernateException {
		try {
			getTransaction().registerSynchronization(sync);
		}
		catch (Exception e) {
			throw new TransactionException("Could not register synchronization", e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void setTimeout(int seconds) {
		throw new UnsupportedOperationException("cannot set transaction timeout in CMT");
	}

}

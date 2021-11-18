/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.transaction;

import javax.transaction.Status;
import javax.transaction.Synchronization;
import javax.transaction.SystemException;
import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import org.hibernate.TransactionException;
import org.hibernate.jdbc.JDBCContext;
import org.hibernate.util.JTAHelper;

/**
 * {@link Transaction} implementation based on transaction management through
 * a JTA {@link UserTransaction}.  Similar to {@link CMTTransaction}, except
 * here we are actually managing the transactions through the Hibernate
 * transaction mechanism.
 *
 * @author Gavin King
 * @author Steve Ebersole
 * @author Les Hazlewood
 */
public class JTATransaction implements Transaction {

	private static final Logger log = LoggerFactory.getLogger( JTATransaction.class );

	private final JDBCContext jdbcContext;
	private final TransactionFactory.Context transactionContext;

	private UserTransaction userTransaction;
	private boolean newTransaction;
	private boolean begun;
	private boolean commitFailed;
	private boolean commitSucceeded;
	private boolean callback;

	public JTATransaction(
			UserTransaction userTransaction,
			JDBCContext jdbcContext,
			TransactionFactory.Context transactionContext) {
		this.jdbcContext = jdbcContext;
		this.transactionContext = transactionContext;
		this.userTransaction = userTransaction;
	}

	/**
	 * {@inheritDoc}
	 */
	public void begin() throws HibernateException {
		if ( begun ) {
			return;
		}
		if ( commitFailed ) {
			throw new TransactionException( "cannot re-start transaction after failed commit" );
		}

		log.debug( "begin" );

		try {
			newTransaction = userTransaction.getStatus() == Status.STATUS_NO_TRANSACTION;
			if ( newTransaction ) {
				userTransaction.begin();
				log.debug( "Began a new JTA transaction" );
			}
		}
		catch ( Exception e ) {
			log.error( "JTA transaction begin failed", e );
			throw new TransactionException( "JTA transaction begin failed", e );
		}

		/*if (newTransaction) {
			// don't need a synchronization since we are committing
			// or rolling back the transaction ourselves - assuming
			// that we do no work in beforeTransactionCompletion()
			synchronization = false;
		}*/

		boolean synchronization = jdbcContext.registerSynchronizationIfPossible();

		if ( !newTransaction && !synchronization ) {
			log.warn( "You should set hibernate.transaction.manager_lookup_class if cache is enabled" );
		}

		if ( !synchronization ) {
			//if we could not register a synchronization,
			//do the before/after completion callbacks
			//ourself (but we need to let jdbcContext
			//know that this is what we are going to
			//do, so it doesn't keep trying to register
			//synchronizations)
			callback = jdbcContext.registerCallbackIfNecessary();
		}

		begun = true;
		commitSucceeded = false;

		jdbcContext.afterTransactionBegin( this );
	}

	/**
	 * {@inheritDoc}
	 */
	public void commit() throws HibernateException {
		if ( !begun ) {
			throw new TransactionException( "Transaction not successfully started" );
		}

		log.debug( "commit" );

		boolean flush = !transactionContext.isFlushModeNever()
				&& ( callback || !transactionContext.isFlushBeforeCompletionEnabled() );

		if ( flush ) {
			transactionContext.managedFlush(); //if an exception occurs during flush, user must call rollback()
		}

		if ( callback && newTransaction ) {
			jdbcContext.beforeTransactionCompletion( this );
		}

		closeIfRequired();

		if ( newTransaction ) {
			try {
				userTransaction.commit();
				commitSucceeded = true;
				log.debug( "Committed JTA UserTransaction" );
			}
			catch ( Exception e ) {
				commitFailed = true; // so the transaction is already rolled back, by JTA spec
				log.error( "JTA commit failed", e );
				throw new TransactionException( "JTA commit failed: ", e );
			}
			finally {
				afterCommitRollback();
			}
		}
		else {
			// this one only really needed for badly-behaved applications!
			// (if the TransactionManager has a Sychronization registered,
			// its a noop)
			// (actually we do need it for downgrading locks)
			afterCommitRollback();
		}

	}

	/**
	 * {@inheritDoc}
	 */
	public void rollback() throws HibernateException {
		if ( !begun && !commitFailed ) {
			throw new TransactionException( "Transaction not successfully started" );
		}

		log.debug( "rollback" );

		try {
			closeIfRequired();
		}
		catch ( Exception e ) {
			// swallow it, and continue to roll back JTA transaction
			log.error( "could not close session during rollback", e );
		}

		try {
			if ( newTransaction ) {
				if ( !commitFailed ) {
					userTransaction.rollback();
					log.debug( "Rolled back JTA UserTransaction" );
				}
			}
			else {
				userTransaction.setRollbackOnly();
				log.debug( "set JTA UserTransaction to rollback only" );
			}
		}
		catch ( Exception e ) {
			log.error( "JTA rollback failed", e );
			throw new TransactionException( "JTA rollback failed", e );
		}
		finally {
			afterCommitRollback();
		}
	}

	private static final int NULL = Integer.MIN_VALUE;

	private void afterCommitRollback() throws TransactionException {

		begun = false;
		// this method is a noop if there is a Synchronization!
		if ( callback ) {
			if ( !newTransaction ) {
				log.warn( "You should set hibernate.transaction.manager_lookup_class if cache is enabled" );
			}
			int status = NULL;
			try {
				status = userTransaction.getStatus();
			}
			catch ( Exception e ) {
				log.error( "Could not determine transaction status after commit", e );
				throw new TransactionException( "Could not determine transaction status after commit", e );
			}
			finally {
				jdbcContext.afterTransactionCompletion( status == Status.STATUS_COMMITTED, this );
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean wasRolledBack() throws TransactionException {
		final int status;
		try {
			status = userTransaction.getStatus();
		}
		catch ( SystemException se ) {
			log.error( "Could not determine transaction status", se );
			throw new TransactionException( "Could not determine transaction status", se );
		}
		if ( status == Status.STATUS_UNKNOWN ) {
			throw new TransactionException( "Could not determine transaction status" );
		}
		else {
			return JTAHelper.isRollback( status );
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean wasCommitted() throws TransactionException {
		final int status;
		try {
			status = userTransaction.getStatus();
		}
		catch ( SystemException se ) {
			log.error( "Could not determine transaction status", se );
			throw new TransactionException( "Could not determine transaction status: ", se );
		}
		if ( status == Status.STATUS_UNKNOWN ) {
			throw new TransactionException( "Could not determine transaction status" );
		}
		else {
			return status == Status.STATUS_COMMITTED;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean isActive() throws TransactionException {
		if ( !begun || commitFailed || commitSucceeded ) {
			return false;
		}

		final int status;
		try {
			status = userTransaction.getStatus();
		}
		catch ( SystemException se ) {
			log.error( "Could not determine transaction status", se );
			throw new TransactionException( "Could not determine transaction status: ", se );
		}
		if ( status == Status.STATUS_UNKNOWN ) {
			throw new TransactionException( "Could not determine transaction status" );
		}
		else {
			return status == Status.STATUS_ACTIVE;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void registerSynchronization(Synchronization sync) throws HibernateException {
		if ( getTransactionManager() == null ) {
			throw new IllegalStateException( "JTA TransactionManager not available" );
		}
		else {
			try {
				getTransactionManager().getTransaction().registerSynchronization( sync );
			}
			catch ( Exception e ) {
				throw new TransactionException( "could not register synchronization", e );
			}
		}
	}

	/**
	 * Getter for property 'transactionManager'.
	 *
	 * @return Value for property 'transactionManager'.
	 */
	private TransactionManager getTransactionManager() {
		return transactionContext.getFactory().getTransactionManager();
	}

	private void closeIfRequired() throws HibernateException {
		boolean close = callback &&
				transactionContext.shouldAutoClose() &&
				!transactionContext.isClosed();
		if ( close ) {
			transactionContext.managedClose();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void setTimeout(int seconds) {
		try {
			userTransaction.setTransactionTimeout( seconds );
		}
		catch ( SystemException se ) {
			throw new TransactionException( "could not set transaction timeout", se );
		}
	}

	/**
	 * Getter for property 'userTransaction'.
	 *
	 * @return Value for property 'userTransaction'.
	 */
	protected UserTransaction getUserTransaction() {
		return userTransaction;
	}
}

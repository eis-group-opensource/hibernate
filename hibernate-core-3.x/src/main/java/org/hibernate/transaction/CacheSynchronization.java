/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.transaction;

import javax.transaction.Status;
import javax.transaction.Synchronization;
import javax.transaction.SystemException;
import javax.transaction.Transaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.hibernate.TransactionException;
import org.hibernate.jdbc.JDBCContext;
import org.hibernate.util.JTAHelper;

/**
 * A JTA transaction synch used to allow the {@link org.hibernate.Session} to know about transaction
 * events.
 *
 * @author Gavin King
 */
public final class CacheSynchronization implements Synchronization {

	private static final Logger log = LoggerFactory.getLogger(CacheSynchronization.class);

	private final TransactionFactory.Context ctx;
	private JDBCContext jdbcContext;
	private final Transaction transaction;
	private final org.hibernate.Transaction hibernateTransaction;

	public CacheSynchronization(
			TransactionFactory.Context ctx, 
			JDBCContext jdbcContext, 
			Transaction transaction, 
			org.hibernate.Transaction tx) {
		this.ctx = ctx;
		this.jdbcContext = jdbcContext;
		this.transaction = transaction;
		this.hibernateTransaction = tx;
	}

	/**
	 * {@inheritDoc}
	 */
	public void beforeCompletion() {
		log.trace("transaction before completion callback");

		boolean flush;
		try {
			flush = !ctx.isFlushModeNever() &&
			        ctx.isFlushBeforeCompletionEnabled() && 
			        !JTAHelper.isRollback( transaction.getStatus() ); 
					//actually, this last test is probably unnecessary, since 
					//beforeCompletion() doesn't get called during rollback
		}
		catch (SystemException se) {
			log.error("could not determine transaction status", se);
			setRollbackOnly();
			throw new TransactionException("could not determine transaction status in beforeCompletion()", se);
		}
		
		try {
			if (flush) {
				log.trace("automatically flushing session");
				ctx.managedFlush();
			}
		}
		catch (RuntimeException re) {
			setRollbackOnly();
			throw re;
		}
		finally {
			jdbcContext.beforeTransactionCompletion(hibernateTransaction);
		}
	}

	private void setRollbackOnly() {
		try {
			transaction.setRollbackOnly();
		}
		catch (SystemException se) {
			log.error("could not set transaction to rollback only", se);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void afterCompletion(int status) {
		if ( log.isTraceEnabled() ) {
			log.trace("transaction after completion callback, status: " + status);
		}
		try {
			jdbcContext.afterTransactionCompletion(status==Status.STATUS_COMMITTED, hibernateTransaction);
		}
		finally {
			if ( ctx.shouldAutoClose() && !ctx.isClosed() ) {
				log.trace("automatically closing session");
				ctx.managedClose();
			}
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	public String toString() {
		return CacheSynchronization.class.getName();
	}

}

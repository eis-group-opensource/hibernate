/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.transaction;

import java.util.Properties;

import org.hibernate.ConnectionReleaseMode;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import org.hibernate.jdbc.JDBCContext;

/**
 * Factory for {@link JDBCTransaction} instances.
 *
 * @author Anton van Straaten
 */
public final class JDBCTransactionFactory implements TransactionFactory {

	/**
	 * {@inheritDoc}
	 */
	public ConnectionReleaseMode getDefaultReleaseMode() {
		return ConnectionReleaseMode.AFTER_TRANSACTION;
	}

	/**
	 * {@inheritDoc}
	 */
	public Transaction createTransaction(JDBCContext jdbcContext, Context transactionContext)
	throws HibernateException {
		return new JDBCTransaction( jdbcContext, transactionContext );
	}

	/**
	 * {@inheritDoc}
	 */
	public void configure(Properties props) throws HibernateException {}

	/**
	 * {@inheritDoc}
	 */
	public boolean isTransactionManagerRequired() {
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean areCallbacksLocalToHibernateTransactions() {
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean isTransactionInProgress(
			JDBCContext jdbcContext,
	        Context transactionContext,
	        Transaction transaction) {
//		try {
//			// for JDBC-based transactions, we only want to return true
//			// here if we (this transaction) are managing the transaction
//			return transaction != null &&
//			       transaction.isActive() &&
//			       !jdbcContext.getConnectionManager().isAutoCommit();
//		}
//		catch ( SQLException e ) {
//			// assume we are in auto-commit!
//			return false;
//		}
		return transaction != null && transaction.isActive();
	}

}

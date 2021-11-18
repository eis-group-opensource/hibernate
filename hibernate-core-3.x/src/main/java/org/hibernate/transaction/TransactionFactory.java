/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.transaction;

import java.util.Properties;

import org.hibernate.ConnectionReleaseMode;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import org.hibernate.engine.SessionFactoryImplementor;
import org.hibernate.jdbc.JDBCContext;

/**
 * Contract for generating Hibernate {@link Transaction} instances.
 * <p/>
 * The concrete implementation to be used is specified by the
 * {@link org.hibernate.cfg.Environment#TRANSACTION_STRATEGY} configuration
 * setting.
 * <p/>
 * Implementors must be threadsafe and should declare a public default constructor.
 *
 * @see Transaction
 *
 * @author Anton van Straaten
 * @author Gavin King
 */
public interface TransactionFactory {

	/**
	 * Callback mechanism; a context is always a {@link org.hibernate.Session}
	 * in the Hibernate usage.
	 */
	public static interface Context {
		public SessionFactoryImplementor getFactory();
		public boolean isClosed();

		public boolean isFlushModeNever();
		public boolean isFlushBeforeCompletionEnabled();
		public void managedFlush();

		public boolean shouldAutoClose();
		public void managedClose();
	}

	/**
	 * Begin a transaction and return the associated <tt>Transaction</tt> instance.
	 *
	 * @param jdbcContext  The jdbc context to which the transaction belongs
	 * @param context The contract regarding the context in which this transaction will operate.
	 * @return Transaction
	 * @throws HibernateException Indicates a problem generating a transaction instance
	 */
	public Transaction createTransaction(JDBCContext jdbcContext, Context context) throws HibernateException;

	/**
	 * Configure from the given properties.
	 *
	 * @param props The configuration properties.
	 * @throws HibernateException Indicates a problem configuring this factory.
	 */
	public void configure(Properties props) throws HibernateException;
	
	/**
	 * Get the default connection release mode.
	 *
	 * @return The default release mode associated with this strategy
	 */
	public ConnectionReleaseMode getDefaultReleaseMode();
	
	/**
	 * Do we require access to the JTA TransactionManager for
	 * this strategy?
	 *
	 * @return True if this strategy requires access to the JTA TransactionManager;
	 * false otherwise.
	 */
	public boolean isTransactionManagerRequired();

	/**
	 * Are all transaction callbacks local to Hibernate Transactions?
	 * Or can the callbacks originate from some other source (e.g. a JTA
	 * Synchronization).
	 *
	 * @return true if callbacks only ever originate from the Hibernate
	 * {@link Transaction}; false otherwise.
	 */
	public boolean areCallbacksLocalToHibernateTransactions();

	/**
	 * Determine whether an underlying transaction is in progress.
	 * <p/>
	 * Mainly this is used in determining whether to register a
	 * synchronization as well as whether or not to circumvent
	 * auto flushing outside transactions.
	 *
	 * @param jdbcContext The JDBC context
	 * @param transactionContext The transaction context
	 * @param transaction The Hibernate transaction
	 * @return true if an underlying transaction is know to be in effect.
	 */
	public boolean isTransactionInProgress(JDBCContext jdbcContext, Context transactionContext, Transaction transaction);
}

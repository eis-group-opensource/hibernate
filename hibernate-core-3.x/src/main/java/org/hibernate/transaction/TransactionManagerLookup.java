/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.transaction;

import java.util.Properties;
import javax.transaction.Transaction;
import javax.transaction.TransactionManager;

import org.hibernate.HibernateException;

/**
 * Contract for locating the JTA {@link TransactionManager} on given platform.
 * <p/>
 * NOTE: this contract has expanded over time, and basically is a platform
 * abstraction contract for JTA-related information.
 *
 * @author Gavin King
 */
public interface TransactionManagerLookup {

	/**
	 * Obtain the JTA {@link TransactionManager}.
	 *
	 * @param props The configuration properties.
	 * @return The JTA {@link TransactionManager}.
	 *
	 * @throws HibernateException Indicates problem locating {@link TransactionManager}.
	 */
	public TransactionManager getTransactionManager(Properties props) throws HibernateException;

	/**
	 * Return the JNDI namespace of the JTA
	 * {@link javax.transaction.UserTransaction} for this platform or <tt>null</tt>;
	 * optional operation.
	 *
	 * @return The JNDI namespace where we can locate the
	 * {@link javax.transaction.UserTransaction} for this platform.
	 */
	public String getUserTransactionName();

	/**
	 * Determine an identifier for the given transaction appropriate for use in caching/lookup usages.
	 * <p/>
	 * Generally speaking the transaction itself will be returned here.  This method was added specifically
	 * for use in WebSphere and other unfriendly JEE containers (although WebSphere is still the only known
	 * such brain-dead, sales-driven impl).
	 *
	 * @param transaction The transaction to be identified.
	 * @return An appropropriate identifier
	 */
	public Object getTransactionIdentifier(Transaction transaction);
}


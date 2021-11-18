/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.transaction;

import java.util.Properties;

import javax.transaction.TransactionManager;
import javax.transaction.Transaction;

import org.hibernate.HibernateException;

/**
 * {@link TransactionManagerLookup} strategy for JOnAS
 */
public class JOnASTransactionManagerLookup implements TransactionManagerLookup {

	/**
	 * @see org.hibernate.transaction.TransactionManagerLookup#getTransactionManager(Properties)
	 */
	public TransactionManager getTransactionManager(Properties props) throws HibernateException {
		try {
			Class clazz = Class.forName("org.objectweb.jonas_tm.Current");
			return (TransactionManager) clazz.getMethod("getTransactionManager", null).invoke(null, null);
		}
		catch (Exception e) {
			throw new HibernateException( "Could not obtain JOnAS transaction manager instance", e );
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public String getUserTransactionName() {
		return "java:comp/UserTransaction";
	}

	/**
	 * {@inheritDoc}
	 */
	public Object getTransactionIdentifier(Transaction transaction) {
		return transaction;
	}
}


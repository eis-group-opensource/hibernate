/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.transaction;

import java.util.Properties;

import javax.naming.NamingException;
import javax.transaction.TransactionManager;
import javax.transaction.Transaction;

import org.hibernate.HibernateException;
import org.hibernate.util.NamingHelper;

/**
 * Template implementation of {@link TransactionManagerLookup} where the
 * underlying {@link TransactionManager} is available via JNDI lookup at the
 * specified location - {@link #getName}.
 *
 * @author Gavin King
 */
public abstract class JNDITransactionManagerLookup implements TransactionManagerLookup {

	/**
	 * Get the JNDI namespace under wich we can locate the {@link TransactionManager}.
	 *
	 * @return The {@link TransactionManager} JNDI namespace
	 */
	protected abstract String getName();

	/**
	 * {@inheritDoc}
	 */
	public TransactionManager getTransactionManager(Properties props) throws HibernateException {
		try {
			return (TransactionManager) NamingHelper.getInitialContext(props).lookup( getName() );
		}
		catch (NamingException ne) {
			throw new HibernateException( "Could not locate TransactionManager", ne );
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public Object getTransactionIdentifier(Transaction transaction) {
		// for sane JEE/JTA containers, the transaction itself functions as its identifier...
		return transaction;
	}
}







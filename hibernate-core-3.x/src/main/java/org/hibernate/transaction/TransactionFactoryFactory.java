/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.transaction;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.hibernate.HibernateException;
import org.hibernate.cfg.Environment;
import org.hibernate.util.ReflectHelper;

/**
 * Helper for creating {@link TransactionFactory} instances.
 *
 * @author Gavin King
 */
public final class TransactionFactoryFactory {

	private static final Logger log = LoggerFactory.getLogger( TransactionFactoryFactory.class );

	/**
	 * Create an appropriate transaction factory based on the given configuration
	 * properties.
	 *
	 * @param transactionProps transaction properties
	 *
	 * @return The appropriate transaction factory.
	 *
	 * @throws HibernateException Indicates a problem creating the appropriate
	 * transaction factory.
	 */
	public static TransactionFactory buildTransactionFactory(Properties transactionProps) throws HibernateException {
		String strategyClassName = transactionProps.getProperty( Environment.TRANSACTION_STRATEGY );
		if ( strategyClassName == null ) {
			log.info( "Using default transaction strategy (direct JDBC transactions)" );
			return new JDBCTransactionFactory();
		}
		log.info( "Transaction strategy: " + strategyClassName );
		TransactionFactory factory;
		try {
			factory = ( TransactionFactory ) ReflectHelper.classForName( strategyClassName ).newInstance();
		}
		catch ( ClassNotFoundException e ) {
			log.error( "TransactionFactory class not found", e );
			throw new HibernateException( "TransactionFactory class not found: " + strategyClassName );
		}
		catch ( IllegalAccessException e ) {
			log.error( "Failed to instantiate TransactionFactory", e );
			throw new HibernateException( "Failed to instantiate TransactionFactory: " + e );
		}
		catch ( java.lang.InstantiationException e ) {
			log.error( "Failed to instantiate TransactionFactory", e );
			throw new HibernateException( "Failed to instantiate TransactionFactory: " + e );
		}
		factory.configure( transactionProps );
		return factory;
	}

	/**
	 * Disallow instantiation
	 */
	private TransactionFactoryFactory() {
	}
}

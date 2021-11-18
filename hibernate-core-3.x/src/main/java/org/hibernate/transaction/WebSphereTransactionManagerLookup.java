/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.transaction;

import java.util.Properties;

import javax.transaction.TransactionManager;
import javax.transaction.Transaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.hibernate.HibernateException;

/**
 * {@link TransactionManagerLookup} strategy for WebSphere (versions 4, 5.0 and 5.1)
 *
 * @author Gavin King
 */
public class WebSphereTransactionManagerLookup implements TransactionManagerLookup {

	private static final Logger log = LoggerFactory.getLogger(WebSphereTransactionManagerLookup.class);
	private final int wsVersion;
	private final Class tmfClass;
	
	/**
	 * Constructs a new WebSphereTransactionManagerLookup.
	 */
	public WebSphereTransactionManagerLookup() {
		try {
			Class clazz;
			int version;
			try {
				clazz = Class.forName( "com.ibm.ws.Transaction.TransactionManagerFactory" );
				version = 5;
				log.info( "WebSphere 5.1" );
			}
			catch ( Exception e ) {
				try {
					clazz = Class.forName( "com.ibm.ejs.jts.jta.TransactionManagerFactory" );
					version = 5;
					log.info( "WebSphere 5.0" );
				} 
				catch ( Exception e2 ) {
					clazz = Class.forName( "com.ibm.ejs.jts.jta.JTSXA" );
					version = 4;
					log.info( "WebSphere 4" );
				}
			}

			tmfClass = clazz;
			wsVersion = version;
		}
		catch ( Exception e ) {
			throw new HibernateException( "Could not obtain WebSphere TransactionManagerFactory instance", e );
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public TransactionManager getTransactionManager(Properties props) throws HibernateException {
		try {
			return ( TransactionManager ) tmfClass.getMethod( "getTransactionManager", null ).invoke( null, null );
		}
		catch ( Exception e ) {
			throw new HibernateException( "Could not obtain WebSphere TransactionManager", e );
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public String getUserTransactionName() {
		return wsVersion == 5
				? "java:comp/UserTransaction"
				: "jta/usertransaction";
	}

	/**
	 * {@inheritDoc}
	 */
	public Object getTransactionIdentifier(Transaction transaction) {
		return transaction;
	}
}
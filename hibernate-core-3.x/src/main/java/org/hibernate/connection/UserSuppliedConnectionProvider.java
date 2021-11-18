/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.connection;

import java.sql.Connection;
import java.util.Properties;

import org.slf4j.LoggerFactory;
import org.hibernate.HibernateException;

/**
 * An implementation of the <literal>ConnectionProvider</literal> interface that
 * simply throws an exception when a connection is requested. This implementation
 * indicates that the user is expected to supply a JDBC connection.
 * @see ConnectionProvider
 * @author Gavin King
 */
public class UserSuppliedConnectionProvider implements ConnectionProvider {

	/**
	 * @see org.hibernate.connection.ConnectionProvider#configure(Properties)
	 */
	public void configure(Properties props) throws HibernateException {
		LoggerFactory.getLogger( UserSuppliedConnectionProvider.class )
				.warn( "No connection properties specified - the user must supply JDBC connections" );
	}

	/**
	 * @see org.hibernate.connection.ConnectionProvider#getConnection()
	 */
	public Connection getConnection() {
		throw new UnsupportedOperationException("The user must supply a JDBC connection");
	}

	/**
	 * @see org.hibernate.connection.ConnectionProvider#closeConnection(Connection)
	 */
	public void closeConnection(Connection conn) {
		throw new UnsupportedOperationException("The user must supply a JDBC connection");
	}

	public void close() {
	}

	/**
	 * @see ConnectionProvider#supportsAggressiveRelease()
	 */
	public boolean supportsAggressiveRelease() {
		return false;
	}

}







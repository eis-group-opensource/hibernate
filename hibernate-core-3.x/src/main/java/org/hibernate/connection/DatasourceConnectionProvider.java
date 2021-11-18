/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.connection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.HibernateException;
import org.hibernate.cfg.Environment;
import org.hibernate.util.NamingHelper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A connection provider that uses a <tt>DataSource</tt> registered with JNDI.
 * Hibernate will use this <tt>ConnectionProvider</tt> by default if the
 * property <tt>hibernate.connection.datasource</tt> is set.
 * @see ConnectionProvider
 * @author Gavin King
 */
public class DatasourceConnectionProvider implements ConnectionProvider {
	private DataSource ds;
	private String user;
	private String pass;

	private static final Logger log = LoggerFactory.getLogger(DatasourceConnectionProvider.class);

	public DataSource getDataSource() {
		return ds;
	}

	public void setDataSource(DataSource ds) {
		this.ds = ds;
	}

	public void configure(Properties props) throws HibernateException {

		String jndiName = props.getProperty( Environment.DATASOURCE );
		if ( jndiName == null ) {
			String msg = "datasource JNDI name was not specified by property " + Environment.DATASOURCE;
			log.error( msg );
			throw new HibernateException( msg );
		}

		user = props.getProperty( Environment.USER );
		pass = props.getProperty( Environment.PASS );

		try {
			ds = ( DataSource ) NamingHelper.getInitialContext( props ).lookup( jndiName );
		}
		catch ( Exception e ) {
			log.error( "Could not find datasource: " + jndiName, e );
			throw new HibernateException( "Could not find datasource", e );
		}
		if ( ds == null ) {
			throw new HibernateException( "Could not find datasource: " + jndiName );
		}
		log.info( "Using datasource: " + jndiName );
	}

	public Connection getConnection() throws SQLException {
		if (user != null || pass != null) {
			return ds.getConnection(user, pass);
		}
		else {
			return ds.getConnection();
		}
	}

	public void closeConnection(Connection conn) throws SQLException {
		conn.close();
	}

	public void close() {}

	/**
	 * @see ConnectionProvider#supportsAggressiveRelease()
	 */
	public boolean supportsAggressiveRelease() {
		return true;
	}

}








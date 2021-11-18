/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.dialect.resolver;

import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.hibernate.dialect.Dialect;
import org.hibernate.exception.JDBCConnectionException;
import org.hibernate.JDBCException;

/**
 * A templated resolver impl which delegates to the {@link #resolveDialectInternal} method
 * and handles any thrown {@link SQLException}s.
 *
 * @author Steve Ebersole
 */
public abstract class AbstractDialectResolver implements DialectResolver {
	private static final Logger log = LoggerFactory.getLogger( AbstractDialectResolver.class );

	/**
	 * {@inheritDoc}
	 * <p/>
	 * Here we template the resolution, delegating to {@link #resolveDialectInternal} and handling
	 * {@link java.sql.SQLException}s properly.
	 */
	public final Dialect resolveDialect(DatabaseMetaData metaData) {
		try {
			return resolveDialectInternal( metaData );
		}
		catch ( SQLException sqlException ) {
			JDBCException jdbcException = BasicSQLExceptionConverter.INSTANCE.convert( sqlException );
			if ( jdbcException instanceof JDBCConnectionException ) {
				throw jdbcException;
			}
			else {
				log.warn( BasicSQLExceptionConverter.MSG + " : " + sqlException.getMessage() );
				return null;
			}
		}
		catch ( Throwable t ) {
			log.warn( "Error executing resolver [" + this + "] : " + t.getMessage() );
			return null;
		}
	}

	/**
	 * Perform the actual resolution without caring about handling {@link SQLException}s.
	 *
	 * @param metaData The database metadata
	 * @return The resolved dialect, or null if we could not resolve.
	 * @throws SQLException Indicates problems accessing the metadata.
	 */
	protected abstract Dialect resolveDialectInternal(DatabaseMetaData metaData) throws SQLException;
}

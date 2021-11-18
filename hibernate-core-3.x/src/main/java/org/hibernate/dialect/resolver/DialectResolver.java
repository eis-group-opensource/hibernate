/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.dialect.resolver;

import java.sql.Connection;
import java.sql.DatabaseMetaData;

import org.hibernate.dialect.Dialect;
import org.hibernate.exception.JDBCConnectionException;

/**
 * Contract for determining the {@link Dialect} to use based on a JDBC {@link Connection}.
 * 
 * @author Tomoto Shimizu Washio
 * @author Steve Ebersole
 */
public interface DialectResolver {
	/**
	 * Determine the {@link Dialect} to use based on the given JDBC {@link DatabaseMetaData}.  Implementations are
	 * expected to return the {@link Dialect} instance to use, or null if the {@link DatabaseMetaData} does not match
	 * the criteria handled by this impl.
	 * 
	 * @param metaData The JDBC metadata.
	 * @return The dialect to use, or null.
	 * @throws JDBCConnectionException Indicates a 'non transient connection problem', which indicates that
	 * we should stop resolution attempts.
	 */
	public Dialect resolveDialect(DatabaseMetaData metaData) throws JDBCConnectionException;
}

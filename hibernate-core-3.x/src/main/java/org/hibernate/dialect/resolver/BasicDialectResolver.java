/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.dialect.resolver;

import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import org.hibernate.dialect.Dialect;
import org.hibernate.HibernateException;

/**
 * Intended as support for custom resolvers.
 *
 * @author Steve Ebersole
 */
public class BasicDialectResolver extends AbstractDialectResolver {
	public static final int VERSION_INSENSITIVE_VERSION = -9999;

	private final String matchingName;
	private final int matchingVersion;
	private final Class dialectClass;

	public BasicDialectResolver(String matchingName, Class dialectClass) {
		this( matchingName, VERSION_INSENSITIVE_VERSION, dialectClass );
	}

	public BasicDialectResolver(String matchingName, int matchingVersion, Class dialectClass) {
		this.matchingName = matchingName;
		this.matchingVersion = matchingVersion;
		this.dialectClass = dialectClass;
	}

	protected final Dialect resolveDialectInternal(DatabaseMetaData metaData) throws SQLException {
		final String databaseName = metaData.getDatabaseProductName();
		final int databaseMajorVersion = metaData.getDatabaseMajorVersion();

		if ( matchingName.equalsIgnoreCase( databaseName )
				&& ( matchingVersion == VERSION_INSENSITIVE_VERSION || matchingVersion == databaseMajorVersion ) ) {
			try {
				return ( Dialect ) dialectClass.newInstance();
			}
			catch ( HibernateException e ) {
				// conceivable that the dialect ctor could throw HibernateExceptions, so don't re-wrap
				throw e;
			}
			catch ( Throwable t ) {
				throw new HibernateException(
						"Could not instantiate specified Dialect class [" + dialectClass.getName() + "]",
						t
				);
			}
		}

		return null;
	}
}

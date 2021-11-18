/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.dialect.resolver;

import java.sql.DatabaseMetaData;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.hibernate.dialect.Dialect;
import org.hibernate.exception.JDBCConnectionException;

/**
 * A {@link DialectResolver} implementation which coordinates resolution by delegating to its
 * registered sub-resolvers.  Sub-resolvers may be registered by calling either {@link #addResolver} or
 * {@link #addResolverAtFirst}.
 *
 * @author Tomoto Shimizu Washio
 */
public class DialectResolverSet implements DialectResolver {
	private static Logger log = LoggerFactory.getLogger( DialectResolverSet.class );

	private List resolvers = new ArrayList();

	/**
	 * {@inheritDoc}
	 */
	public Dialect resolveDialect(DatabaseMetaData metaData) {
		Iterator i = resolvers.iterator();
		while ( i.hasNext() ) {
			final DialectResolver resolver = ( DialectResolver ) i.next();
			try {
				Dialect dialect = resolver.resolveDialect( metaData );
				if ( dialect != null ) {
					return dialect;
				}
			}
			catch ( JDBCConnectionException e ) {
				throw e;
			}
			catch ( Throwable t ) {
				log.info( "sub-resolver threw unexpected exception, continuing to next : " + t.getMessage() );
			}
		}
		return null;
	}

	/**
	 * Add a resolver at the end of the underlying resolver list.  The resolver added by this method is at lower
	 * priority than any other existing resolvers.
	 *
	 * @param resolver The resolver to add.
	 */
	public void addResolver(DialectResolver resolver) {
		resolvers.add( resolver );
	}

	/**
	 * Add a resolver at the beginning of the underlying resolver list.  The resolver added by this method is at higher
	 * priority than any other existing resolvers.
	 *
	 * @param resolver The resolver to add.
	 */
	public void addResolverAtFirst(DialectResolver resolver) {
		resolvers.add( 0, resolver );
	}
}

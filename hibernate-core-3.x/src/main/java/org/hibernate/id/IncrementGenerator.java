/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.id;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.cfg.ObjectNameNormalizer;
import org.hibernate.dialect.Dialect;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.exception.JDBCExceptionHelper;
import org.hibernate.mapping.Table;
import org.hibernate.type.Type;
import org.hibernate.util.StringHelper;

/**
 * <b>increment</b><br>
 * <br>
 * An <tt>IdentifierGenerator</tt> that returns a <tt>long</tt>, constructed by
 * counting from the maximum primary key value at startup. Not safe for use in a
 * cluster!<br>
 * <br>
 * Mapping parameters supported, but not usually needed: tables, column.
 * (The tables parameter specified a comma-separated list of table names.)
 *
 * @author Gavin King
 * @author Steve Ebersole
 */
public class IncrementGenerator implements IdentifierGenerator, Configurable {
	private static final Logger log = LoggerFactory.getLogger(IncrementGenerator.class);

	private Class returnClass;
	private String sql;

	private IntegralDataTypeHolder previousValueHolder;

	public synchronized Serializable generate(SessionImplementor session, Object object) throws HibernateException {
		if ( sql != null ) {
			initializePreviousValueHolder( session );
		}
		return previousValueHolder.makeValueThenIncrement();
	}

	public void configure(Type type, Properties params, Dialect dialect) throws MappingException {
		returnClass = type.getReturnedClass();

		ObjectNameNormalizer normalizer =
				( ObjectNameNormalizer ) params.get( PersistentIdentifierGenerator.IDENTIFIER_NORMALIZER );

		String column = params.getProperty( "column" );
		if ( column == null ) {
			column = params.getProperty( PersistentIdentifierGenerator.PK );
		}
		column = dialect.quote( normalizer.normalizeIdentifierQuoting( column ) );

		String tableList = params.getProperty( "tables" );
		if ( tableList == null ) {
			tableList = params.getProperty( PersistentIdentifierGenerator.TABLES );
		}
		String[] tables = StringHelper.split( ", ", tableList );

		final String schema = dialect.quote(
				normalizer.normalizeIdentifierQuoting(
						params.getProperty( PersistentIdentifierGenerator.SCHEMA )
				)
		);
		final String catalog = dialect.quote(
				normalizer.normalizeIdentifierQuoting(
						params.getProperty( PersistentIdentifierGenerator.CATALOG )
				)
		);

		StringBuffer buf = new StringBuffer();
		for ( int i=0; i < tables.length; i++ ) {
			final String tableName = dialect.quote( normalizer.normalizeIdentifierQuoting( tables[i] ) );
			if ( tables.length > 1 ) {
				buf.append( "select " ).append( column ).append( " from " );
			}
			buf.append( Table.qualify( catalog, schema, tableName ) );
			if ( i < tables.length-1 ) {
				buf.append( " union " );
			}
		}
		if ( tables.length > 1 ) {
			buf.insert( 0, "( " ).append( " ) ids_" );
			column = "ids_." + column;
		}
		
		sql = "select max(" + column + ") from " + buf.toString();
	}

	private void initializePreviousValueHolder(SessionImplementor session) {
		previousValueHolder = IdentifierGeneratorHelper.getIntegralDataTypeHolder( returnClass );

		log.debug( "fetching initial value: " + sql );
		try {
			PreparedStatement st = session.getBatcher().prepareSelectStatement( sql );
			try {
				ResultSet rs = st.executeQuery();
				try {
					if ( rs.next() ) {
						previousValueHolder.initialize( rs, 0L ).increment();
					}
					else {
						previousValueHolder.initialize( 1L );
					}
					sql = null;
					log.debug( "first free id: " + previousValueHolder.makeValue() );
				}
				finally {
					rs.close();
				}
			}
			finally {
				session.getBatcher().closeStatement(st);
			}
		}
		catch (SQLException sqle) {
			throw JDBCExceptionHelper.convert(
					session.getFactory().getSQLExceptionConverter(),
					sqle,
					"could not fetch initial value for increment generator",
					sql
			);
		}
	}

}

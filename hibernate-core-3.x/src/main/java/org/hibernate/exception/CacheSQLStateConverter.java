/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.exception;

import org.hibernate.JDBCException;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

/**
 * A SQLExceptionConverter implementation specific to Cach&eacute; SQL,
 * accounting for its custom integrity constraint violation error codes.
 *
 * @author Jonathan Levinson
 */
public class CacheSQLStateConverter implements SQLExceptionConverter {

	private ViolatedConstraintNameExtracter extracter;

	private static final Set SQL_GRAMMAR_CATEGORIES = new HashSet();
	private static final Set DATA_CATEGORIES = new HashSet();
	private static final Set INTEGRITY_VIOLATION_CATEGORIES = new HashSet();
	private static final Set CONNECTION_CATEGORIES = new HashSet();

	static {
		SQL_GRAMMAR_CATEGORIES.add( "07" );
		SQL_GRAMMAR_CATEGORIES.add( "37" );
		SQL_GRAMMAR_CATEGORIES.add( "42" );
		SQL_GRAMMAR_CATEGORIES.add( "65" );
		SQL_GRAMMAR_CATEGORIES.add( "S0" );
		SQL_GRAMMAR_CATEGORIES.add( "20" );

		DATA_CATEGORIES.add( "22" );
		DATA_CATEGORIES.add( "21" );
		DATA_CATEGORIES.add( "02" );

		INTEGRITY_VIOLATION_CATEGORIES.add( new Integer( 119 ) );
		INTEGRITY_VIOLATION_CATEGORIES.add( new Integer( 120 ) );
		INTEGRITY_VIOLATION_CATEGORIES.add( new Integer( 121 ) );
		INTEGRITY_VIOLATION_CATEGORIES.add( new Integer( 122 ) );
		INTEGRITY_VIOLATION_CATEGORIES.add( new Integer( 123 ) );
		INTEGRITY_VIOLATION_CATEGORIES.add( new Integer( 124 ) );
		INTEGRITY_VIOLATION_CATEGORIES.add( new Integer( 125 ) );
		INTEGRITY_VIOLATION_CATEGORIES.add( new Integer( 127 ) );

		CONNECTION_CATEGORIES.add( "08" );
	}

	public CacheSQLStateConverter(ViolatedConstraintNameExtracter extracter) {
		this.extracter = extracter;
	}

	/**
	 * Convert the given SQLException into Hibernate's JDBCException hierarchy.
	 *
	 * @param sqlException The SQLException to be converted.
	 * @param message	  An optional error message.
	 * @param sql		  Optionally, the sql being performed when the exception occurred.
	 * @return The resulting JDBCException.
	 */
	public JDBCException convert(SQLException sqlException, String message, String sql) {
		String sqlStateClassCode = JDBCExceptionHelper.extractSqlStateClassCode( sqlException );
		Integer errorCode = new Integer( JDBCExceptionHelper.extractErrorCode( sqlException ) );
		if ( sqlStateClassCode != null ) {
			if ( SQL_GRAMMAR_CATEGORIES.contains( sqlStateClassCode ) ) {
				return new SQLGrammarException( message, sqlException, sql );
			}
			else if ( INTEGRITY_VIOLATION_CATEGORIES.contains( errorCode ) ) {
				String constraintName = extracter.extractConstraintName( sqlException );
				return new ConstraintViolationException( message, sqlException, sql, constraintName );
			}
			else if ( CONNECTION_CATEGORIES.contains( sqlStateClassCode ) ) {
				return new JDBCConnectionException( message, sqlException, sql );
			}
			else if ( DATA_CATEGORIES.contains( sqlStateClassCode ) ) {
				return new DataException( message, sqlException, sql );
			}
		}
		return handledNonSpecificException( sqlException, message, sql );
	}

	/**
	 * Handle an exception not converted to a specific type based on the SQLState.
	 *
	 * @param sqlException The exception to be handled.
	 * @param message	  An optional message
	 * @param sql		  Optionally, the sql being performed when the exception occurred.
	 * @return The converted exception; should <b>never</b> be null.
	 */
	protected JDBCException handledNonSpecificException(SQLException sqlException, String message, String sql) {
		return new GenericJDBCException( message, sqlException, sql );
	}
}

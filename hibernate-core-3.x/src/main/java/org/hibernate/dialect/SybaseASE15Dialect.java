/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.dialect;

import java.sql.Types;

import org.hibernate.Hibernate;
import org.hibernate.dialect.function.AnsiTrimEmulationFunction;
import org.hibernate.dialect.function.SQLFunctionTemplate;

/**
 * An SQL dialect targetting Sybase Adaptive Server Enterprise (ASE) 15 and higher.
 * <p/>
 * TODO : verify if this also works with 12/12.5
 * 
 * @author Gavin King
 */
public class SybaseASE15Dialect extends AbstractTransactSQLDialect {
	public SybaseASE15Dialect() {
		super();

		registerColumnType( Types.LONGVARBINARY, "image" );
		registerColumnType( Types.LONGVARCHAR, "text" );

		registerFunction( "second", new SQLFunctionTemplate(Hibernate.INTEGER, "datepart(second, ?1)") );
		registerFunction( "minute", new SQLFunctionTemplate(Hibernate.INTEGER, "datepart(minute, ?1)") );
		registerFunction( "hour", new SQLFunctionTemplate(Hibernate.INTEGER, "datepart(hour, ?1)") );
		registerFunction( "extract", new SQLFunctionTemplate( Hibernate.INTEGER, "datepart(?1, ?3)" ) );
		registerFunction( "mod", new SQLFunctionTemplate( Hibernate.INTEGER, "?1 % ?2" ) );
		registerFunction( "bit_length", new SQLFunctionTemplate( Hibernate.INTEGER, "datalength(?1) * 8" ) );
		registerFunction( "trim", new AnsiTrimEmulationFunction( AnsiTrimEmulationFunction.LTRIM, AnsiTrimEmulationFunction.RTRIM, "str_replace" ) ); 
	}

	// Overridden informational metadata ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	public boolean supportsCascadeDelete() {
		return false;
	}

    /**
     * By default, Sybase string comparisons are case-insensitive.
	 * <p/>
     * If the DB is configured to be case-sensitive, then this return
	 * value will be incorrect.
     */
    public boolean areStringComparisonsCaseInsensitive() {
        return true;
    }

	/**
	 * Actually Sybase does not support LOB locators at al.
	 *
	 * @return false.
	 */
	public boolean supportsExpectedLobUsagePattern() {
		return false;
	}
}

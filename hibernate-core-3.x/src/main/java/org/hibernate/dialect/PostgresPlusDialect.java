/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.dialect;

import java.sql.Types;
import java.sql.SQLException;
import java.sql.CallableStatement;
import java.sql.ResultSet;

import org.hibernate.Hibernate;
import org.hibernate.dialect.function.NoArgSQLFunction;
import org.hibernate.dialect.function.NvlFunction;
import org.hibernate.dialect.function.StandardSQLFunction;

/**
 * An SQL dialect for Postgres Plus
 *
 * @author Jim Mlodgenski
 */
public class PostgresPlusDialect extends PostgreSQLDialect {

	public PostgresPlusDialect() {
		super();

		registerFunction( "ltrim", new StandardSQLFunction( "ltrim" ) );
		registerFunction( "rtrim", new StandardSQLFunction( "rtrim" ) );
		registerFunction( "soundex", new StandardSQLFunction( "soundex" ) );
		registerFunction( "sysdate", new NoArgSQLFunction( "sysdate", Hibernate.DATE, false ) );
		registerFunction( "rowid", new NoArgSQLFunction( "rowid", Hibernate.LONG, false ) );
		registerFunction( "rownum", new NoArgSQLFunction( "rownum", Hibernate.LONG, false ) );
		registerFunction( "instr", new StandardSQLFunction( "instr", Hibernate.INTEGER ) );
		registerFunction( "lpad", new StandardSQLFunction( "lpad", Hibernate.STRING ) );
		registerFunction( "replace", new StandardSQLFunction( "replace", Hibernate.STRING ) );
		registerFunction( "rpad", new StandardSQLFunction( "rpad", Hibernate.STRING ) );
		registerFunction( "translate", new StandardSQLFunction( "translate", Hibernate.STRING ) );
		registerFunction( "substring", new StandardSQLFunction( "substr", Hibernate.STRING ) );
		registerFunction( "coalesce", new NvlFunction() );
		registerFunction( "atan2", new StandardSQLFunction( "atan2", Hibernate.FLOAT ) );
		registerFunction( "mod", new StandardSQLFunction( "mod", Hibernate.INTEGER ) );
		registerFunction( "nvl", new StandardSQLFunction( "nvl" ) );
		registerFunction( "nvl2", new StandardSQLFunction( "nvl2" ) );
		registerFunction( "power", new StandardSQLFunction( "power", Hibernate.FLOAT ) );
		registerFunction( "add_months", new StandardSQLFunction( "add_months", Hibernate.DATE ) );
		registerFunction( "months_between", new StandardSQLFunction( "months_between", Hibernate.FLOAT ) );
		registerFunction( "next_day", new StandardSQLFunction( "next_day", Hibernate.DATE ) );
	}

	public String getCurrentTimestampSelectString() {
		return "select sysdate";
	}

	public String getCurrentTimestampSQLFunctionName() {
		return "sysdate";
	}

	public int registerResultSetOutParameter(CallableStatement statement, int col) throws SQLException {
		statement.registerOutParameter( col, Types.REF );
		col++;
		return col;
	}

	public ResultSet getResultSet(CallableStatement ps) throws SQLException {
		ps.execute();
		return ( ResultSet ) ps.getObject( 1 );
	}

	public String getSelectGUIDString() {
		return "select uuid_generate_v1";
	}

}

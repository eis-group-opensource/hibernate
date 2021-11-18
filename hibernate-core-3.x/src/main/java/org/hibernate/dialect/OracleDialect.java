/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.dialect;

import java.sql.Types;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.hibernate.sql.CaseFragment;
import org.hibernate.sql.DecodeCaseFragment;
import org.hibernate.sql.JoinFragment;
import org.hibernate.sql.OracleJoinFragment;

/**
 * An SQL dialect for Oracle, compatible with Oracle 8.
 *
 * @deprecated Use Oracle8iDialect instead.
 * @author Gavin King
 */
public class OracleDialect extends Oracle9Dialect {

	private static final Logger log = LoggerFactory.getLogger( OracleDialect.class );

	public OracleDialect() {
		super();
		log.warn( "The OracleDialect dialect has been deprecated; use Oracle8iDialect instead" );
		// Oracle8 and previous define only a "DATE" type which
		//      is used to represent all aspects of date/time
		registerColumnType( Types.TIMESTAMP, "date" );
		registerColumnType( Types.CHAR, "char(1)" );
		registerColumnType( Types.VARCHAR, 4000, "varchar2($l)" );
	}

	public JoinFragment createOuterJoinFragment() {
		return new OracleJoinFragment();
	}
	public CaseFragment createCaseFragment() {
		return new DecodeCaseFragment();
	}

	public String getLimitString(String sql, boolean hasOffset) {

		sql = sql.trim();
		boolean isForUpdate = false;
		if ( sql.toLowerCase().endsWith(" for update") ) {
			sql = sql.substring( 0, sql.length()-11 );
			isForUpdate = true;
		}
		
		StringBuffer pagingSelect = new StringBuffer( sql.length()+100 );
		if (hasOffset) {
			pagingSelect.append("select * from ( select row_.*, rownum rownum_ from ( ");
		}
		else {
			pagingSelect.append("select * from ( ");
		}
		pagingSelect.append(sql);
		if (hasOffset) {
			pagingSelect.append(" ) row_ ) where rownum_ <= ? and rownum_ > ?");
		}
		else {
			pagingSelect.append(" ) where rownum <= ?");
		}

		if ( isForUpdate ) {
			pagingSelect.append( " for update" );
		}
		
		return pagingSelect.toString();
	}

	public String getSelectClauseNullString(int sqlType) {
		switch(sqlType) {
			case Types.VARCHAR:
			case Types.CHAR:
				return "to_char(null)";
			case Types.DATE:
			case Types.TIMESTAMP:
			case Types.TIME:
				return "to_date(null)";
			default:
				return "to_number(null)";
		}
	}

	public String getCurrentTimestampSelectString() {
		return "select sysdate from dual";
	}

	public String getCurrentTimestampSQLFunctionName() {
		return "sysdate";
	}
}

/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.dialect;

/**
 * An SQL dialect for DB2/390. This class provides support for
 * DB2 Universal Database for OS/390, also known as DB2/390.
 *
 * @author Kristoffer Dyrkorn
 */
public class DB2390Dialect extends DB2Dialect {

	public boolean supportsSequences() {
		return false;
	}

	public String getIdentitySelectString() {
		return "select identity_val_local() from sysibm.sysdummy1";
	}

	public boolean supportsLimit() {
		return true;
	}

	public boolean supportsLimitOffset() {
		return false;
	}

	public boolean useMaxForLimit() {
		return true;
	}

	public boolean supportsVariableLimit() {
		return false;
	}

	public String getLimitString(String sql, int offset, int limit) {
		if ( offset > 0 ) {
			throw new UnsupportedOperationException( "query result offset is not supported" );
		}
		return new StringBuffer( sql.length() + 40 )
				.append( sql )
				.append( " fetch first " )
				.append( limit )
				.append( " rows only " )
				.toString();
	}

}
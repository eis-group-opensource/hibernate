/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.dialect;

/**
 * An SQL dialect for Firebird.
 *
 * @author Reha CENANI
 */
public class FirebirdDialect extends InterbaseDialect {

	public String getDropSequenceString(String sequenceName) {
		return "drop generator " + sequenceName;
	}

	public String getLimitString(String sql, boolean hasOffset) {
		return new StringBuffer( sql.length() + 20 )
				.append( sql )
				.insert( 6, hasOffset ? " first ? skip ?" : " first ?" )
				.toString();
	}

	public boolean bindLimitParametersFirst() {
		return true;
	}

	public boolean bindLimitParametersInReverseOrder() {
		return true;
	}

}

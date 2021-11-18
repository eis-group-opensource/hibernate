/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate;

import java.sql.SQLException;

/**
 *
 * Thrown when a database query timeout occurs.
 *
 * @author Scott Marlow
 */

public class QueryTimeoutException extends JDBCException {

	public QueryTimeoutException( String s, JDBCException je, String sql ) {
		super(s, je.getSQLException(), sql);
	}

	public QueryTimeoutException( String s, SQLException se, String sql ) {
		super(s, se, sql);
	}

}

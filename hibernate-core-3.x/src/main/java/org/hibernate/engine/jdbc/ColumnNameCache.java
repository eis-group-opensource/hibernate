/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.engine.jdbc;

import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

/**
 * Cache of column-name -> column-index resolutions
 *
 * @author Steve Ebersole
 */
public class ColumnNameCache {
	public static final float LOAD_FACTOR = .75f;

	private final Map columnNameToIndexCache;

	public ColumnNameCache(int columnCount) {
		// should *not* need to grow beyond the size of the total number of columns in the rs
		this.columnNameToIndexCache = new HashMap( columnCount + (int)( columnCount * LOAD_FACTOR ) + 1, LOAD_FACTOR );
	}

	public int getIndexForColumnName(String columnName, ResultSet rs) throws SQLException {
		Integer cached = ( Integer ) columnNameToIndexCache.get( columnName );
		if ( cached != null ) {
			return cached.intValue();
		}
		else {
			int index = rs.findColumn( columnName );
			columnNameToIndexCache.put( columnName, new Integer(index) );
			return index;
		}
	}
}

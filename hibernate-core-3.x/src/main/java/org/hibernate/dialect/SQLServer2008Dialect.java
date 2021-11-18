/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.dialect;

import java.sql.Types;

import org.hibernate.Hibernate;
import org.hibernate.dialect.function.NoArgSQLFunction;

/**
 * A dialect for Microsoft SQL Server 2008 with JDBC Driver 3.0 and above
 *
 * @author Strong Liu
 * @author kbublys
 * 
 */
public class SQLServer2008Dialect extends SQLServerDialect {
	
	private static final String SELECT = "select";
    private static final String SELECT_DISTINCT = "select distinct";
	
	public SQLServer2008Dialect(){
		super();
		
		registerColumnType( Types.DATE, "date" );
		registerColumnType( Types.TIME, "time" );
		registerColumnType( Types.TIMESTAMP, "datetime2" );
		registerFunction( "current_timestamp", new NoArgSQLFunction("current_timestamp", Hibernate.TIMESTAMP,false) );
	}
	
	/**
     * Add a LIMIT clause to the given SQL SELECT The LIMIT SQL will look like:
     * 
     * <pre>
     * WITH query AS (SELECT ROW_NUMBER() OVER (ORDER BY orderby) as __hibernate_row_nr__, original_query_without_orderby)
     * SELECT * FROM query WHERE __hibernate_row_nr__ BEETWIN offset AND offset + last
     * --ORDER BY __hibernate_row_nr__: Don't think that wee need this last order by clause
     * </pre>
     * 
     * @param querySqlString The SQL statement to base the limit query off of.
     * @param offset Offset of the first row to be returned by the query (zero-based)
     * @param limit Maximum number of rows to be returned by the query
     * @return A new SQL statement with the LIMIT clause applied.
     */
    public String getLimitString(String querySqlString, int offset, int limit) {
        if (offset <= 0) {
            return super.getLimitString(querySqlString, offset, limit);
        }

        StringBuilder sb = new StringBuilder(querySqlString.trim());

        String querySqlLowered = querySqlString.trim().toLowerCase();
        int orderByIndex = querySqlLowered.toLowerCase().indexOf("order by");
        String orderby = orderByIndex > 0 ? querySqlString.substring(orderByIndex) : "ORDER BY CURRENT_TIMESTAMP";

        // Delete the order by clause at the end of the query
        if (orderByIndex > 0) {
            sb.delete(orderByIndex, orderByIndex + orderby.length());
        }

        // Find the end of the select statement
        int selectIndex = querySqlLowered.trim().indexOf(SQLServer2008Dialect.SELECT_DISTINCT);
        if (selectIndex != -1) {
            selectIndex += SQLServer2008Dialect.SELECT_DISTINCT.length();
        } else {
            selectIndex = querySqlLowered.trim().indexOf(SQLServer2008Dialect.SELECT);
            if (selectIndex != -1) {
                selectIndex += SQLServer2008Dialect.SELECT.length();
            }
        }

        // Isert after the select statement the row_number() function:
        sb.insert(selectIndex, " ROW_NUMBER() OVER (" + orderby + ") as __hibernate_row_nr__,");

        //Wrap the query within a with statement:
        sb.insert(0, "WITH query AS (").append(") SELECT * FROM query ");
        sb.append("WHERE __hibernate_row_nr__ BETWEEN ").append(offset + 1).append(" AND ").append(limit);

        return sb.toString();
    }
	
	public boolean supportsLimit() {
		return true;
	}
	
	public boolean supportsLimitOffset() {
        return true;
    }
	
	public boolean bindLimitParametersFirst() {
        return false;
    }
	
	public boolean useMaxForLimit() {
        return true;
    }
	
}

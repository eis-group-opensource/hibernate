/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.hql;

import org.hibernate.engine.JoinSequence;
import org.hibernate.sql.JoinFragment;
import org.hibernate.MappingException;
import org.hibernate.QueryException;
import org.hibernate.util.StringHelper;

import java.util.Map;

/**
 * Provides the SQL for collection subqueries.
 * <br>
 * Moved here from PathExpressionParser to make it re-useable.
 * 
 * @author josh
 */
public final class CollectionSubqueryFactory {

	//TODO: refactor to .sql package

	private CollectionSubqueryFactory() {
	}

	public static String createCollectionSubquery(
			JoinSequence joinSequence,
	        Map enabledFilters,
	        String[] columns) {
		try {
			JoinFragment join = joinSequence.toJoinFragment( enabledFilters, true );
			return new StringBuffer( "select " )
					.append( StringHelper.join( ", ", columns ) )
					.append( " from " )
					.append( join.toFromFragmentString().substring( 2 ) )// remove initial ", "
					.append( " where " )
					.append( join.toWhereFragmentString().substring( 5 ) )// remove initial " and "
					.toString();
		}
		catch ( MappingException me ) {
			throw new QueryException( me );
		}
	}
}

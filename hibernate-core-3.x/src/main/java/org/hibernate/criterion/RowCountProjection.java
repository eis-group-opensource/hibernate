/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.criterion;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.dialect.function.SQLFunction;
import org.hibernate.type.Type;

/**
 * A row count
 *
 * @author Gavin King
 */
public class RowCountProjection extends SimpleProjection {
	private static List ARGS = java.util.Collections.singletonList( "*" );

	public String toString() {
		return "count(*)";
	}

	public Type[] getTypes(Criteria criteria, CriteriaQuery criteriaQuery) throws HibernateException {
		return new Type[] {
				getFunction( criteriaQuery ).getReturnType( null, criteriaQuery.getFactory() )
		};
	}

	public String toSqlString(Criteria criteria, int position, CriteriaQuery criteriaQuery) throws HibernateException {
		return getFunction( criteriaQuery ).render( ARGS, criteriaQuery.getFactory() )
				+ " as y" + position + '_';
	}

	protected SQLFunction getFunction(CriteriaQuery criteriaQuery) {
		SQLFunction function = criteriaQuery.getFactory()
				.getSqlFunctionRegistry()
				.findSQLFunction( "count" );
		if ( function == null ) {
			throw new HibernateException( "Unable to locate count function mapping" );
		}
		return function;
	}
}

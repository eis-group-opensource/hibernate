/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.criterion;

import java.util.Collections;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.dialect.function.SQLFunction;
import org.hibernate.type.Type;

/**
 * Base class for standard aggregation functions.
 *
 * @author max
 */
public class AggregateProjection extends SimpleProjection {
	protected final String propertyName;
	private final String functionName;
	
	protected AggregateProjection(String functionName, String propertyName) {
		this.functionName = functionName;
		this.propertyName = propertyName;
	}

	public String getFunctionName() {
		return functionName;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public String toString() {
		return functionName + "(" + propertyName + ')';
	}

	/**
	 * {@inheritDoc}
	 */
	public Type[] getTypes(Criteria criteria, CriteriaQuery criteriaQuery) throws HibernateException {
		return new Type[] {
				getFunction( criteriaQuery ).getReturnType(
						criteriaQuery.getType( criteria, getPropertyName() ),
						criteriaQuery.getFactory()
				)
		};
	}

	/**
	 * {@inheritDoc}
	 */
	public String toSqlString(Criteria criteria, int loc, CriteriaQuery criteriaQuery)
			throws HibernateException {
		final String functionFragment = getFunction( criteriaQuery ).render(
				buildFunctionParameterList( criteria, criteriaQuery ),
				criteriaQuery.getFactory()
		);
		return functionFragment + " as y" + loc + '_';
	}

	protected SQLFunction getFunction(CriteriaQuery criteriaQuery) {
		return getFunction( getFunctionName(), criteriaQuery );
	}

	protected SQLFunction getFunction(String functionName, CriteriaQuery criteriaQuery) {
		SQLFunction function = criteriaQuery.getFactory()
				.getSqlFunctionRegistry()
				.findSQLFunction( functionName );
		if ( function == null ) {
			throw new HibernateException( "Unable to locate mapping for function named [" + functionName + "]" );
		}
		return function;
	}

	protected List buildFunctionParameterList(Criteria criteria, CriteriaQuery criteriaQuery) {
		return buildFunctionParameterList( criteriaQuery.getColumn( criteria, getPropertyName() ) );
	}

	protected List buildFunctionParameterList(String column) {
		return Collections.singletonList( column );
	}
}

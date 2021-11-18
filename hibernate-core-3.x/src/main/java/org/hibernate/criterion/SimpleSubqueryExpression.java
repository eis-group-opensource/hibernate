/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.criterion;

import org.hibernate.Criteria;
import org.hibernate.EntityMode;
import org.hibernate.HibernateException;
import org.hibernate.engine.TypedValue;

/**
 * A comparison between a constant value and the the result of a subquery
 * @author Gavin King
 */
public class SimpleSubqueryExpression extends SubqueryExpression {
	
	private Object value;
	
	protected SimpleSubqueryExpression(Object value, String op, String quantifier, DetachedCriteria dc) {
		super(op, quantifier, dc);
		this.value = value;
	}
	
	
	public TypedValue[] getTypedValues(Criteria criteria, CriteriaQuery criteriaQuery) 
	throws HibernateException {
		TypedValue[] superTv = super.getTypedValues(criteria, criteriaQuery);
		TypedValue[] result = new TypedValue[superTv.length+1];
		System.arraycopy(superTv, 0, result, 1, superTv.length);
		result[0] = new TypedValue( getTypes()[0], value, EntityMode.POJO );
		return result;
	}
	
	protected String toLeftSqlString(Criteria criteria, CriteriaQuery criteriaQuery) {
		return "?";
	}
}

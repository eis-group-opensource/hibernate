/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.criterion;


import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.engine.TypedValue;
import org.hibernate.util.StringHelper;

/**
 * Constrains a property to be null
 * @author Gavin King
 */
public class NullExpression implements Criterion {

	private final String propertyName;

	private static final TypedValue[] NO_VALUES = new TypedValue[0];

	protected NullExpression(String propertyName) {
		this.propertyName = propertyName;
	}

	public String toSqlString(Criteria criteria, CriteriaQuery criteriaQuery) 
	throws HibernateException {
		String[] columns = criteriaQuery.getColumnsUsingProjection(criteria, propertyName);
		String result = StringHelper.join(
			" and ",
			StringHelper.suffix( columns, " is null" )
		);
		if (columns.length>1) result = '(' + result + ')';
		return result;

		//TODO: get SQL rendering out of this package!
	}

	public TypedValue[] getTypedValues(Criteria criteria, CriteriaQuery criteriaQuery) 
	throws HibernateException {
		return NO_VALUES;
	}

	public String toString() {
		return propertyName + " is null";
	}

}

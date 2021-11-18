/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.criterion;


import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.engine.TypedValue;
import org.hibernate.util.StringHelper;

/**
 * Constrains a property to between two values
 * @author Gavin King
 */
public class BetweenExpression implements Criterion {

	private final String propertyName;
	private final Object lo;
	private final Object hi;

	protected BetweenExpression(String propertyName, Object lo, Object hi) {
		this.propertyName = propertyName;
		this.lo = lo;
		this.hi = hi;
	}

	public String toSqlString(Criteria criteria, CriteriaQuery criteriaQuery)
	throws HibernateException {
		return StringHelper.join(
			" and ",
			StringHelper.suffix( criteriaQuery.getColumnsUsingProjection(criteria, propertyName), " between ? and ?" )
		);

		//TODO: get SQL rendering out of this package!
	}

	public TypedValue[] getTypedValues(Criteria criteria, CriteriaQuery criteriaQuery)
	throws HibernateException {
		return new TypedValue[] {
				criteriaQuery.getTypedValue(criteria, propertyName, lo),
				criteriaQuery.getTypedValue(criteria, propertyName, hi)
		};
	}

	public String toString() {
		return propertyName + " between " + lo + " and " + hi;
	}

}

/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.criterion;


import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.dialect.MySQLDialect;
import org.hibernate.engine.TypedValue;

/**
 * Negates another criterion
 * @author Gavin King
 */
public class NotExpression implements Criterion {

	private Criterion criterion;

	protected NotExpression(Criterion criterion) {
		this.criterion = criterion;
	}

	public String toSqlString(Criteria criteria, CriteriaQuery criteriaQuery)
	throws HibernateException {
		if ( criteriaQuery.getFactory().getDialect() instanceof MySQLDialect ) {
			return "not (" + criterion.toSqlString(criteria, criteriaQuery) + ')';
		}
		else {
			return "not " + criterion.toSqlString(criteria, criteriaQuery);
		}
	}

	public TypedValue[] getTypedValues(
		Criteria criteria, CriteriaQuery criteriaQuery)
	throws HibernateException {
		return criterion.getTypedValues(criteria, criteriaQuery);
	}

	public String toString() {
		return "not " + criterion.toString();
	}

}

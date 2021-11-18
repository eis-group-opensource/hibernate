/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.criterion;


import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.engine.TypedValue;

/**
 * Superclass of binary logical expressions
 * @author Gavin King
 */
public class LogicalExpression implements Criterion {

	private final Criterion lhs;
	private final Criterion rhs;
	private final String op;

	protected LogicalExpression(Criterion lhs, Criterion rhs, String op) {
		this.lhs = lhs;
		this.rhs = rhs;
		this.op = op;
	}

	public TypedValue[] getTypedValues(Criteria criteria, CriteriaQuery criteriaQuery)
	throws HibernateException {

		TypedValue[] lhstv = lhs.getTypedValues(criteria, criteriaQuery);
		TypedValue[] rhstv = rhs.getTypedValues(criteria, criteriaQuery);
		TypedValue[] result = new TypedValue[ lhstv.length + rhstv.length ];
		System.arraycopy(lhstv, 0, result, 0, lhstv.length);
		System.arraycopy(rhstv, 0, result, lhstv.length, rhstv.length);
		return result;
	}

	public String toSqlString(Criteria criteria, CriteriaQuery criteriaQuery)
	throws HibernateException {

		return '(' +
			lhs.toSqlString(criteria, criteriaQuery) +
			' ' +
			getOp() +
			' ' +
			rhs.toSqlString(criteria, criteriaQuery) +
			')';
	}

	public String getOp() {
		return op;
	}

	public String toString() {
		return lhs.toString() + ' ' + getOp() + ' ' + rhs.toString();
	}
}

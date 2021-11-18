/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.criterion;

import org.hibernate.Criteria;

/**
 * A comparison between a property value in the outer query and the
 * result of a subquery
 * @author Gavin King
 */
public class PropertySubqueryExpression extends SubqueryExpression {
	private String propertyName;

	protected PropertySubqueryExpression(String propertyName, String op, String quantifier, DetachedCriteria dc) {
		super(op, quantifier, dc);
		this.propertyName = propertyName;
	}

	protected String toLeftSqlString(Criteria criteria, CriteriaQuery criteriaQuery) {
		return criteriaQuery.getColumn(criteria, propertyName);
	}

}

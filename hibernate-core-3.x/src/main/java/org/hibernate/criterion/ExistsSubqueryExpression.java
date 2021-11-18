/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.criterion;

import org.hibernate.Criteria;

/**
 * @author Gavin King
 */
public class ExistsSubqueryExpression extends SubqueryExpression {

	protected String toLeftSqlString(Criteria criteria, CriteriaQuery outerQuery) {
		return "";
	}
	
	protected ExistsSubqueryExpression(String quantifier, DetachedCriteria dc) {
		super(null, quantifier, dc);
	}
}

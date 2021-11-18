/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.criterion;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.engine.TypedValue;

/**
 * @author Gavin King
 */
public class NaturalIdentifier implements Criterion {
		
	private Junction conjunction = new Conjunction();

	public TypedValue[] getTypedValues(Criteria criteria, CriteriaQuery criteriaQuery) throws HibernateException {
		return conjunction.getTypedValues(criteria, criteriaQuery);
	}

	public String toSqlString(Criteria criteria, CriteriaQuery criteriaQuery) throws HibernateException {
		return conjunction.toSqlString(criteria, criteriaQuery);
	}
	
	public NaturalIdentifier set(String property, Object value) {
		conjunction.add( Restrictions.eq(property, value) );
		return this;
	}

}

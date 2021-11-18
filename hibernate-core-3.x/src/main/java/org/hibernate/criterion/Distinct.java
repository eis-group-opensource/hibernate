/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.criterion;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.type.Type;

/**
 * @author Gavin King
 */
public class Distinct implements EnhancedProjection {

	private final Projection projection;
	
	public Distinct(Projection proj) {
		this.projection = proj;
	}

	public String toSqlString(Criteria criteria, int position, CriteriaQuery criteriaQuery)
			throws HibernateException {
		return "distinct " + projection.toSqlString(criteria, position, criteriaQuery);
	}

	public String toGroupSqlString(Criteria criteria, CriteriaQuery criteriaQuery)
			throws HibernateException {
		return projection.toGroupSqlString(criteria, criteriaQuery);
	}

	public Type[] getTypes(Criteria criteria, CriteriaQuery criteriaQuery)
			throws HibernateException {
		return projection.getTypes(criteria, criteriaQuery);
	}

	public Type[] getTypes(String alias, Criteria criteria, CriteriaQuery criteriaQuery)
			throws HibernateException {
		return projection.getTypes(alias, criteria, criteriaQuery);
	}

	public String[] getColumnAliases(int loc) {
		return projection.getColumnAliases(loc);
	}

	public String[] getColumnAliases(int loc, Criteria criteria, CriteriaQuery criteriaQuery) {
		return projection instanceof EnhancedProjection ?
				( ( EnhancedProjection ) projection ).getColumnAliases( loc, criteria, criteriaQuery ) :
				getColumnAliases( loc );
	}

	public String[] getColumnAliases(String alias, int loc) {
		return projection.getColumnAliases(alias, loc);
	}

	public String[] getColumnAliases(String alias, int loc, Criteria criteria, CriteriaQuery criteriaQuery) {
		return projection instanceof EnhancedProjection ?
				( ( EnhancedProjection ) projection ).getColumnAliases( alias, loc, criteria, criteriaQuery ) :
				getColumnAliases( alias, loc );
	}

	public String[] getAliases() {
		return projection.getAliases();
	}

	public boolean isGrouped() {
		return projection.isGrouped();
	}

	public String toString() {
		return "distinct " + projection.toString();
	}
}

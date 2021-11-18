/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.criterion;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.type.Type;
import org.hibernate.util.StringHelper;

/**
 * A property value, or grouped property value
 * @author Gavin King
 */
public class PropertyProjection extends SimpleProjection {

	private String propertyName;
	private boolean grouped;
	
	protected PropertyProjection(String prop, boolean grouped) {
		this.propertyName = prop;
		this.grouped = grouped;
	}
	
	protected PropertyProjection(String prop) {
		this(prop, false);
	}

	public String getPropertyName() {
		return propertyName;
	}
	
	public String toString() {
		return propertyName;
	}

	public Type[] getTypes(Criteria criteria, CriteriaQuery criteriaQuery) 
	throws HibernateException {
		return new Type[] { criteriaQuery.getType(criteria, propertyName) };
	}

	public String toSqlString(Criteria criteria, int position, CriteriaQuery criteriaQuery) 
	throws HibernateException {
		StringBuffer buf = new StringBuffer();
		String[] cols = criteriaQuery.getColumns( propertyName, criteria );
		for ( int i=0; i<cols.length; i++ ) {
			buf.append( cols[i] )
				.append(" as y")
				.append(position + i)
				.append('_');
			if (i < cols.length -1)
			   buf.append(", ");
		}
		return buf.toString();
	}

	public boolean isGrouped() {
		return grouped;
	}
	
	public String toGroupSqlString(Criteria criteria, CriteriaQuery criteriaQuery) 
	throws HibernateException {
		if (!grouped) {
			return super.toGroupSqlString(criteria, criteriaQuery);
		}
		else {
			return StringHelper.join( ", ", criteriaQuery.getColumns( propertyName, criteria ) );
		}
	}

}

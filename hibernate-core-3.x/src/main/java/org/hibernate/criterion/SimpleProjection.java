/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.criterion;

import java.util.Iterator;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.type.Type;


/**
 * A single-column projection that may be aliased
 * @author Gavin King
 */
public abstract class SimpleProjection implements EnhancedProjection {

	public Projection as(String alias) {
		return Projections.alias(this, alias);
	}

	public String[] getColumnAliases(String alias, int loc) {
		return null;
	}

	public String[] getColumnAliases(String alias, int loc, Criteria criteria, CriteriaQuery criteriaQuery) {
		return getColumnAliases( alias, loc );
	}

	public Type[] getTypes(String alias, Criteria criteria, CriteriaQuery criteriaQuery) 
	throws HibernateException {
		return null;
	}

	public String[] getColumnAliases(int loc) {
		return new String[] { "y" + loc + "_" };
	}

	public int getColumnCount(Criteria criteria, CriteriaQuery criteriaQuery) {
		Type types[] = getTypes( criteria, criteriaQuery );
		int count = 0;
		for ( int i=0; i<types.length; i++ ) {
			count += types[ i ].getColumnSpan( criteriaQuery.getFactory() );
		}
		return count;
	}

	public String[] getColumnAliases(int loc, Criteria criteria, CriteriaQuery criteriaQuery) {
		int numColumns =  getColumnCount( criteria, criteriaQuery );
		String[] aliases = new String[ numColumns ];
		for (int i = 0; i < numColumns; i++) {
			aliases[i] = "y" + loc + "_";
			loc++;
		}
		return aliases;
	}

	public String[] getAliases() {
		return new String[1];
	}

	public String toGroupSqlString(Criteria criteria, CriteriaQuery criteriaQuery) 
	throws HibernateException {
		throw new UnsupportedOperationException("not a grouping projection");
	}

	public boolean isGrouped() {
		return false;
	}

}

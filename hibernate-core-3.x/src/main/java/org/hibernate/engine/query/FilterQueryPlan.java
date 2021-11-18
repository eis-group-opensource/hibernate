/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.engine.query;

import org.hibernate.engine.SessionFactoryImplementor;

import java.io.Serializable;
import java.util.Map;

/**
 * Extends an HQLQueryPlan to maintain a reference to the collection-role name
 * being filtered.
 *
 * @author Steve Ebersole
 */
public class FilterQueryPlan extends HQLQueryPlan implements Serializable {

	private final String collectionRole;

	public FilterQueryPlan(
			String hql,
	        String collectionRole,
	        boolean shallow,
	        Map enabledFilters,
	        SessionFactoryImplementor factory) {
		super( hql, collectionRole, shallow, enabledFilters, factory );
		this.collectionRole = collectionRole;
	}

	public String getCollectionRole() {
		return collectionRole;
	}
}

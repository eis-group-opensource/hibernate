/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.loader.custom;

import org.hibernate.LockMode;
import org.hibernate.HibernateException;

/**
 * Represents some non-scalar (entity/collection) return within the query result.
 *
 * @author Steve Ebersole
 */
public abstract class NonScalarReturn implements Return {
	private final String alias;
	private final LockMode lockMode;

	public NonScalarReturn(String alias, LockMode lockMode) {
		this.alias = alias;
		if ( alias == null ) {
			throw new HibernateException("alias must be specified");
		}
		this.lockMode = lockMode;
	}

	public String getAlias() {
		return alias;
	}

	public LockMode getLockMode() {
		return lockMode;
	}
}

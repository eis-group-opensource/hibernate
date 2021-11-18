/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.loader.custom;

import org.hibernate.loader.EntityAliases;
import org.hibernate.LockMode;

/**
 * Spefically a fetch return that refers to an entity association.
 *
 * @author Steve Ebersole
 */
public class EntityFetchReturn extends FetchReturn {
	private final EntityAliases entityAliases;

	public EntityFetchReturn(
			String alias,
			EntityAliases entityAliases,
			NonScalarReturn owner,
			String ownerProperty,
			LockMode lockMode) {
		super( owner, ownerProperty, alias, lockMode );
		this.entityAliases = entityAliases;
	}

	public EntityAliases getEntityAliases() {
		return entityAliases;
	}
}

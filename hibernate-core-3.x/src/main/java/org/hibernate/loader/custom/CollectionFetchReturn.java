/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.loader.custom;

import org.hibernate.loader.CollectionAliases;
import org.hibernate.loader.EntityAliases;
import org.hibernate.LockMode;

/**
 * Spefically a fetch return that refers to a collection association.
 *
 * @author Steve Ebersole
 */
public class CollectionFetchReturn extends FetchReturn {
	private final CollectionAliases collectionAliases;
	private final EntityAliases elementEntityAliases;

	public CollectionFetchReturn(
			String alias,
			NonScalarReturn owner,
			String ownerProperty,
			CollectionAliases collectionAliases,
	        EntityAliases elementEntityAliases,
			LockMode lockMode) {
		super( owner, ownerProperty, alias, lockMode );
		this.collectionAliases = collectionAliases;
		this.elementEntityAliases = elementEntityAliases;
	}

	public CollectionAliases getCollectionAliases() {
		return collectionAliases;
	}

	public EntityAliases getElementEntityAliases() {
		return elementEntityAliases;
	}
}

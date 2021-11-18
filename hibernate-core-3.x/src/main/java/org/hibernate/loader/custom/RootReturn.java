/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.loader.custom;

import org.hibernate.LockMode;
import org.hibernate.loader.EntityAliases;

/**
 * Represents a return which names a "root" entity.
 * <p/>
 * A root entity means it is explicitly a "column" in the result, as opposed to
 * a fetched association.
 *
 * @author Steve Ebersole
 */
public class RootReturn extends NonScalarReturn {
	private final String entityName;
	private final EntityAliases entityAliases;

	public RootReturn(
			String alias,
			String entityName,
			EntityAliases entityAliases,
			LockMode lockMode) {
		super( alias, lockMode );
		this.entityName = entityName;
		this.entityAliases = entityAliases;
	}

	public String getEntityName() {
		return entityName;
	}

	public EntityAliases getEntityAliases() {
		return entityAliases;
	}
}

/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.engine.profile;

import org.hibernate.persister.entity.EntityPersister;

/**
 * Models the association of a given fetch.
 *
 * @author Steve Ebersole
 */
public class Association {
	private final EntityPersister owner;
	private final String associationPath;
	private final String role;

	public Association(EntityPersister owner, String associationPath) {
		this.owner = owner;
		this.associationPath = associationPath;
		this.role = owner.getEntityName() + '.' + associationPath;
	}

	public EntityPersister getOwner() {
		return owner;
	}

	public String getAssociationPath() {
		return associationPath;
	}

	public String getRole() {
		return role;
	}
}

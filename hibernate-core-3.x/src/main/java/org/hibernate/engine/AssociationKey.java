/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.engine;

import java.io.Serializable;

/**
 * Identifies a named association belonging to a particular
 * entity instance. Used to record the fact that an association
 * is null during loading.
 * 
 * @author Gavin King
 */
final class AssociationKey implements Serializable {
	private EntityKey ownerKey;
	private String propertyName;
	
	public AssociationKey(EntityKey ownerKey, String propertyName) {
		this.ownerKey = ownerKey;
		this.propertyName = propertyName;
	}
	
	public boolean equals(Object that) {
		AssociationKey key = (AssociationKey) that;
		return key.propertyName.equals(propertyName) && 
			key.ownerKey.equals(ownerKey);
	}
	
	public int hashCode() {
		return ownerKey.hashCode() + propertyName.hashCode();
	}
}
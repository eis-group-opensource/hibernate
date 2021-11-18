/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate;

/**
 * Contract for resolving an entity-name from a given entity instance.
 *
 * @author Steve Ebersole
 */
public interface EntityNameResolver {
	/**
	 * Given an entity instance, determine its entity-name.
	 *
	 * @param entity The entity instance.
	 *
	 * @return The corresponding entity-name, or null if this impl does not know how to perform resolution
	 *         for the given entity instance.
	 */
	public String resolveEntityName(Object entity);
}

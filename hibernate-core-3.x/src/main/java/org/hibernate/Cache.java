/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate;

import java.io.Serializable;

/**
 * Provides an API for querying/managing the second level cache regions.
 * <p/>
 * CAUTION: None of these methods respect any isolation or transactional
 * semantics associated with the underlying caches.  Specifically, evictions
 * perform an immediate "hard" removal outside any transactions and/or locking
 * scheme(s).
 *
 * @author Steve Ebersole
 */
public interface Cache {
	/**
	 * Determine whether the cache contains data for the given entity "instance".
	 * <p/>
	 * The semantic here is whether the cache contains data visible for the
	 * current call context.
	 *
	 * @param entityClass The entity class.
	 * @param identifier The entity identifier
	 *
	 * @return True if the underlying cache contains corresponding data; false
	 * otherwise.
	 */
	public boolean containsEntity(Class entityClass, Serializable identifier);

	/**
	 * Determine whether the cache contains data for the given entity "instance".
	 * <p/>
	 * The semantic here is whether the cache contains data visible for the
	 * current call context.
	 *
	 * @param entityName The entity name.
	 * @param identifier The entity identifier
	 *
	 * @return True if the underlying cache contains corresponding data; false otherwise.
	 */
	public boolean containsEntity(String entityName, Serializable identifier);

	/**
	 * Evicts the entity data for a particular entity "instance".
	 *
	 * @param entityClass The entity class.
	 * @param identifier The entity identifier
	 */
	public void evictEntity(Class entityClass, Serializable identifier);

	/**
	 * Evicts the entity data for a particular entity "instance".
	 *
	 * @param entityName The entity name.
	 * @param identifier The entity identifier
	 */
	public void evictEntity(String entityName, Serializable identifier);

	/**
	 * Evicts all entity data from the given region (i.e. for all entities of
	 * type).
	 *
	 * @param entityClass The entity class.
	 */
	public void evictEntityRegion(Class entityClass);

	/**
	 * Evicts all entity data from the given region (i.e. for all entities of
	 * type).
	 *
	 * @param entityName The entity name.
	 */
	public void evictEntityRegion(String entityName);

	/**
	 * Evict data from all entity regions.
	 */
	public void evictEntityRegions();

	/**
	 * Determine whether the cache contains data for the given collection.
	 * <p/>
	 * The semantic here is whether the cache contains data visible for the
	 * current call context.
	 *
	 * @param role The name of the collection role (in form
	 * [owner-entity-name].[collection-property-name]) whose regions should be
	 * evicted.
	 * @param ownerIdentifier The identifier of the owning entity
	 *
	 * @return True if the underlying cache contains corresponding data; false otherwise.
	 */
	public boolean containsCollection(String role, Serializable ownerIdentifier);

	/**
	 * Evicts the cache data for the given identified collection instance.
	 *
	 * @param role The "collection role" (in form [owner-entity-name].[collection-property-name]).
	 * @param ownerIdentifier The identifier of the owning entity
	 */
	public void evictCollection(String role, Serializable ownerIdentifier);

	/**
	 * Evicts all entity data from the given region (i.e. evicts cached data
	 * for all of the specified c9ollection role).
	 *
	 * @param role The "collection role" (in form [owner-entity-name].[collection-property-name]).
	 */
	public void evictCollectionRegion(String role);

	/**
	 * Evict data from all collection regions.
	 */
	public void evictCollectionRegions();

	/**
	 * Determine whether the cache contains data for the given query.
	 * <p/>
	 * The semantic here is whether the cache contains any data for the given
	 * region name since query result caches are not transactionally isolated.
	 *
	 * @param regionName The cache name given to the query.
	 *
	 * @return True if the underlying cache contains corresponding data; false otherwise.
	 */
	public boolean containsQuery(String regionName);

	/**
	 * Evicts all cached query results from the default region.
	 */
	public void evictDefaultQueryRegion();

	/**
	 * Evicts all cached query results under the given name.
	 *
	 * @param regionName The cache name associated to the queries being cached.
	 */
	public void evictQueryRegion(String regionName);

	/**
	 * Evict data from all query regions.
	 */
	public void evictQueryRegions();
}

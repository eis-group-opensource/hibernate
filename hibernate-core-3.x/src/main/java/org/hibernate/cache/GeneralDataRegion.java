/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.cache;

/**
 * Contract for general-purpose cache regions.
 *
 * @author Steve Ebersole
 */
public interface GeneralDataRegion extends Region {

	/**
	 * Get an item from the cache.
	 *
	 * @param key The key of the item to be retrieved.
	 * @return the cached object or <tt>null</tt>
	 * @throws CacheException Indicates a problem accessing the item or region.
	 */
	public Object get(Object key) throws CacheException;

	/**
	 * Put an item into the cache.
	 *
	 * @param key The key under which to cache the item.
	 * @param value The item to cache.
	 * @throws CacheException Indicates a problem accessing the region.
	 */
	public void put(Object key, Object value) throws CacheException;

	/**
	 * Evict an item from the cache immediately (without regard for transaction
	 * isolation).
	 *
	 * @param key The key of the item to remove
	 * @throws CacheException Indicates a problem accessing the item or region.
	 */
	public void evict(Object key) throws CacheException;

	/**
	 * Evict all contents of this particular cache region (without regard for transaction
	 * isolation).
	 *
	 * @throws CacheException Indicates problem accessing the region.
	 */
	public void evictAll() throws CacheException;
}

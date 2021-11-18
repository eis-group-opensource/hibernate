/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.cache.access;

import org.hibernate.cache.CacheException;
import org.hibernate.cache.CollectionRegion;

/**
 * Contract for managing transactional and concurrent access to cached collection
 * data.  For cached collection data, all modification actions actually just
 * invalidate the entry(s).  The call sequence here is:
 * {@link #lockItem} -> {@link #remove} -> {@link #unlockItem}
 * <p/>
 * There is another usage pattern that is used to invalidate entries
 * after performing "bulk" HQL/SQL operations:
 * {@link #lockRegion} -> {@link #removeAll} -> {@link #unlockRegion}
 *
 * @author Gavin King
 * @author Steve Ebersole
 */
public interface CollectionRegionAccessStrategy {

	/**
	 * Get the wrapped collection cache region
	 *
	 * @return The underlying region
	 */
	public CollectionRegion getRegion();

	/**
	 * Attempt to retrieve an object from the cache. Mainly used in attempting
	 * to resolve entities/collections from the second level cache.
	 *
	 * @param key The key of the item to be retrieved.
	 * @param txTimestamp a timestamp prior to the transaction start time
	 * @return the cached object or <tt>null</tt>
	 * @throws org.hibernate.cache.CacheException Propogated from underlying {@link org.hibernate.cache.Region}
	 */
	public Object get(Object key, long txTimestamp) throws CacheException;

	/**
	 * Attempt to cache an object, after loading from the database.
	 *
	 * @param key The item key
	 * @param value The item
	 * @param txTimestamp a timestamp prior to the transaction start time
	 * @param version the item version number
	 * @return <tt>true</tt> if the object was successfully cached
	 * @throws CacheException Propogated from underlying {@link org.hibernate.cache.Region}
	 */
	public boolean putFromLoad(
			Object key,
			Object value,
			long txTimestamp,
			Object version) throws CacheException;

	/**
	 * Attempt to cache an object, after loading from the database, explicitly
	 * specifying the minimalPut behavior.
	 *
	 * @param key The item key
	 * @param value The item
	 * @param txTimestamp a timestamp prior to the transaction start time
	 * @param version the item version number
	 * @param minimalPutOverride Explicit minimalPut flag
	 * @return <tt>true</tt> if the object was successfully cached
	 * @throws CacheException Propogated from underlying {@link org.hibernate.cache.Region}
	 */
	public boolean putFromLoad(
			Object key,
			Object value,
			long txTimestamp,
			Object version,
			boolean minimalPutOverride) throws CacheException;

	/**
	 * We are going to attempt to update/delete the keyed object. This
	 * method is used by "asynchronous" concurrency strategies.
	 * <p/>
	 * The returned object must be passed back to release(), to release the
	 * lock. Concurrency strategies which do not support client-visible
	 * locks may silently return null.
	 *
	 * @param key The key of the item to lock
	 * @param version The item's current version value
	 * @return A representation of our lock on the item; or null.
	 * @throws CacheException Propogated from underlying {@link org.hibernate.cache.Region}
	 */
	public SoftLock lockItem(Object key, Object version) throws CacheException;

	/**
	 * Lock the entire region
	 *
	 * @return A representation of our lock on the item; or null.
	 * @throws CacheException Propogated from underlying {@link org.hibernate.cache.Region}
	 */
	public SoftLock lockRegion() throws CacheException;

	/**
	 * Called when we have finished the attempted update/delete (which may or
	 * may not have been successful), after transaction completion.  This method
	 * is used by "asynchronous" concurrency strategies.
	 *
	 * @param key The item key
	 * @param lock The lock previously obtained from {@link #lockItem}
	 * @throws CacheException Propogated from underlying {@link org.hibernate.cache.Region}
	 */
	public void unlockItem(Object key, SoftLock lock) throws CacheException;

	/**
	 * Called after we have finished the attempted invalidation of the entire
	 * region
	 *
	 * @param lock The lock previously obtained from {@link #lockRegion}
	 * @throws CacheException Propogated from underlying {@link org.hibernate.cache.Region}
	 */
	public void unlockRegion(SoftLock lock) throws CacheException;

	/**
	 * Called after an item has become stale (before the transaction completes).
	 * This method is used by "synchronous" concurrency strategies.
	 *
	 * @param key The key of the item to remove
	 * @throws CacheException Propogated from underlying {@link org.hibernate.cache.Region}
	 */
	public void remove(Object key) throws CacheException;

	/**
	 * Called to evict data from the entire region
	 *
	 * @throws CacheException Propogated from underlying {@link org.hibernate.cache.Region}
	 */
	public void removeAll() throws CacheException;

	/**
	 * Forcibly evict an item from the cache immediately without regard for transaction
	 * isolation.
	 *
	 * @param key The key of the item to remove
	 * @throws CacheException Propogated from underlying {@link org.hibernate.cache.Region}
	 */
	public void evict(Object key) throws CacheException;

	/**
	 * Forcibly evict all items from the cache immediately without regard for transaction
	 * isolation.
	 *
	 * @throws CacheException Propogated from underlying {@link org.hibernate.cache.Region}
	 */
	public void evictAll() throws CacheException;
}

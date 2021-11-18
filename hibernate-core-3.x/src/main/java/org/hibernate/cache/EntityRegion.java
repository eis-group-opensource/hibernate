/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.cache;

import org.hibernate.cache.access.EntityRegionAccessStrategy;
import org.hibernate.cache.access.AccessType;

/**
 * Defines the contract for a cache region which will specifically be used to
 * store entity data.
 *
 * @author Steve Ebersole
 */
public interface EntityRegion extends TransactionalDataRegion {

	/**
	 * Build an access strategy for the requested access type.
	 *
	 * @param accessType The type of access strategy to build; never null.
	 * @return The appropriate strategy contract for accessing this region
	 * for the requested type of access.
	 * @throws CacheException Usually indicates mis-configuration.
	 */
	public EntityRegionAccessStrategy buildAccessStrategy(AccessType accessType) throws CacheException;
}

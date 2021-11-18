/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.cache.impl.bridge;

import org.hibernate.cache.TransactionalDataRegion;
import org.hibernate.cache.Cache;
import org.hibernate.cache.CacheDataDescription;
import org.hibernate.cfg.Settings;

/**
 * {@inheritDoc}
 *
 * @author Steve Ebersole
 */
public abstract class BaseTransactionalDataRegionAdapter
		extends BaseRegionAdapter
		implements TransactionalDataRegion {

	protected final CacheDataDescription metadata;

	protected BaseTransactionalDataRegionAdapter(Cache underlyingCache, Settings settings, CacheDataDescription metadata) {
		super( underlyingCache, settings );
		this.metadata = metadata;
	}

	public boolean isTransactionAware() {
		return underlyingCache instanceof org.hibernate.cache.TransactionAwareCache;
	}

	public CacheDataDescription getCacheDataDescription() {
		return metadata;
	}
}

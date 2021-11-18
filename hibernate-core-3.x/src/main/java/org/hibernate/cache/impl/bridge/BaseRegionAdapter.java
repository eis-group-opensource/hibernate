/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.cache.impl.bridge;

import java.util.Map;

import org.hibernate.cache.Region;
import org.hibernate.cache.Cache;
import org.hibernate.cache.CacheException;
import org.hibernate.cfg.Settings;

/**
 * Basic adapter bridging between {@link Region} and {@link Cache}.
 *
 * @author Steve Ebersole
 */
public abstract class BaseRegionAdapter implements Region {
	protected final Cache underlyingCache;
	protected final Settings settings;

	protected BaseRegionAdapter(Cache underlyingCache, Settings settings) {
		this.underlyingCache = underlyingCache;
		this.settings = settings;
	}

	public String getName() {
		return underlyingCache.getRegionName();
	}

	public void clear() throws CacheException {
		underlyingCache.clear();
	}

	public void destroy() throws CacheException {
		underlyingCache.destroy();
	}

	public boolean contains(Object key) {
		// safer to utilize the toMap() as oposed to say get(key) != null
		return underlyingCache.toMap().containsKey( key );
	}

	public long getSizeInMemory() {
		return underlyingCache.getSizeInMemory();
	}

	public long getElementCountInMemory() {
		return underlyingCache.getElementCountInMemory();
	}

	public long getElementCountOnDisk() {
		return underlyingCache.getElementCountOnDisk();
	}

	public Map toMap() {
		return underlyingCache.toMap();
	}

	public long nextTimestamp() {
		return underlyingCache.nextTimestamp();
	}

	public int getTimeout() {
		return underlyingCache.getTimeout();
	}
}

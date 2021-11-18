/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.cache.impl.bridge;

import java.util.Comparator;

import org.hibernate.cache.OptimisticCacheSource;
import org.hibernate.cache.CacheDataDescription;

/**
 * {@inheritDoc}
*
* @author Steve Ebersole
*/
public class OptimisticCacheSourceAdapter implements OptimisticCacheSource {
	private final CacheDataDescription dataDescription;

	public OptimisticCacheSourceAdapter(CacheDataDescription dataDescription) {
		this.dataDescription = dataDescription;
	}

	public boolean isVersioned() {
		return dataDescription.isVersioned();
	}

	public Comparator getVersionComparator() {
		return dataDescription.getVersionComparator();
	}
}

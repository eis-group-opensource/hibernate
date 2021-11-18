/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.stat;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.hibernate.cache.CacheKey;
import org.hibernate.cache.Region;

/**
 * Second level cache statistics of a specific region
 *
 * @author Gavin King
 */
public class SecondLevelCacheStatisticsImpl extends CategorizedStatistics implements SecondLevelCacheStatistics {

	private transient Region region;
	long hitCount;
	long missCount;
	long putCount;

	SecondLevelCacheStatisticsImpl(Region region) {
		super(region.getName());
		this.region = region;
	}

	public long getHitCount() {
		return hitCount;
	}

	public long getMissCount() {
		return missCount;
	}

	public long getPutCount() {
		return putCount;
	}

	public long getElementCountInMemory() {
		return region.getElementCountInMemory();
	}

	public long getElementCountOnDisk() {
		return region.getElementCountOnDisk();
	}

	public long getSizeInMemory() {
		return region.getSizeInMemory();
	}

	public Map getEntries() {
		Map map = new HashMap();
		Iterator iter = region.toMap().entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry me = (Map.Entry) iter.next();
			map.put(((CacheKey) me.getKey()).getKey(), me.getValue());
		}
		return map;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder()
				.append("SecondLevelCacheStatistics")
				.append("[hitCount=").append(this.hitCount)
				.append(",missCount=").append(this.missCount)
				.append(",putCount=").append(this.putCount);
		//not sure if this would ever be null but wanted to be careful
		if (region != null) {
			builder.append(",elementCountInMemory=").append(this.getElementCountInMemory())
					.append(",elementCountOnDisk=").append(this.getElementCountOnDisk())
					.append(",sizeInMemory=").append(this.getSizeInMemory());
		}
		builder.append(']');
		return builder.toString();
	}
}

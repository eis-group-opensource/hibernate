/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.stat;

import java.io.Serializable;

/**
 * Query statistics (HQL and SQL)
 * <p/>
 * Note that for a cached query, the cache miss is equals to the db count
 *
 * @author Gavin King
 * @author Alex Snaps
 */
public interface QueryStatistics extends Serializable {
	long getExecutionCount();

	long getCacheHitCount();

	long getCachePutCount();

	long getCacheMissCount();

	long getExecutionRowCount();

	long getExecutionAvgTime();

	long getExecutionMaxTime();

	long getExecutionMinTime();
}

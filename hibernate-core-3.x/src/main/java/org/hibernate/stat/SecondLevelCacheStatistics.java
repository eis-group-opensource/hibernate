/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.stat;

import java.io.Serializable;
import java.util.Map;

/**
 * Second level cache statistics of a specific region
 *
 * @author Gavin King
 * @author Alex Snaps
 */
public interface SecondLevelCacheStatistics extends Serializable {
	
	long getHitCount();

	long getMissCount();

	long getPutCount();

	long getElementCountInMemory();

	long getElementCountOnDisk();

	long getSizeInMemory();

	Map getEntries();
}

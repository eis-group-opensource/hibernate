/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.cache.impl.bridge;

import org.hibernate.cache.TimestampsRegion;
import org.hibernate.cache.Cache;
import org.hibernate.cfg.Settings;

/**
 * Adapter specifically briding {@link TimestampsRegion} to {@link Cache}.
*
* @author Steve Ebersole
 */
public class TimestampsRegionAdapter extends BaseGeneralDataRegionAdapter implements TimestampsRegion {
	protected TimestampsRegionAdapter(Cache underlyingCache, Settings settings) {
		super( underlyingCache, settings );
	}
}

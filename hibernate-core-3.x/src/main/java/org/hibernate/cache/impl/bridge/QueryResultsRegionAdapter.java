/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.cache.impl.bridge;

import org.hibernate.cache.QueryResultsRegion;
import org.hibernate.cache.Cache;
import org.hibernate.cfg.Settings;

/**
 * Adapter specifically briding {@link QueryResultsRegion} to {@link Cache}.
*
* @author Steve Ebersole
 */
public class QueryResultsRegionAdapter extends BaseGeneralDataRegionAdapter implements QueryResultsRegion {
	protected QueryResultsRegionAdapter(Cache underlyingCache, Settings settings) {
		super( underlyingCache, settings );
	}
}

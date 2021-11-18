/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.cache;

import org.hibernate.HibernateException;
import org.hibernate.cfg.Settings;

import java.util.Properties;

/**
 * Standard Hibernate implementation of the QueryCacheFactory interface.  Returns
 * instances of {@link StandardQueryCache}.
 */
public class StandardQueryCacheFactory implements QueryCacheFactory {

	public QueryCache getQueryCache(
	        final String regionName,
	        final UpdateTimestampsCache updateTimestampsCache,
	        final Settings settings,
	        final Properties props) 
	throws HibernateException {
		return new StandardQueryCache(settings, props, updateTimestampsCache, regionName);
	}

}

/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.cache.impl.bridge;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.hibernate.cache.RegionFactory;
import org.hibernate.cache.CacheProvider;
import org.hibernate.cache.CacheException;
import org.hibernate.cache.EntityRegion;
import org.hibernate.cache.CollectionRegion;
import org.hibernate.cache.QueryResultsRegion;
import org.hibernate.cache.NoCacheProvider;
import org.hibernate.cache.TimestampsRegion;
import org.hibernate.cache.CacheDataDescription;
import org.hibernate.cache.access.AccessType;
import org.hibernate.util.PropertiesHelper;
import org.hibernate.util.ReflectHelper;
import org.hibernate.cfg.Environment;
import org.hibernate.cfg.Settings;

/**
 * Acts as a bridge between the {@link RegionFactory} contract and the older
 * {@link CacheProvider} contract.
 *
 * @author Steve Ebersole
 */
public class RegionFactoryCacheProviderBridge implements RegionFactory {
	public static final String DEF_PROVIDER = NoCacheProvider.class.getName();
	private static final Logger log = LoggerFactory.getLogger( RegionFactoryCacheProviderBridge.class );

	private CacheProvider cacheProvider;
	private Settings settings;

	public RegionFactoryCacheProviderBridge(Properties properties) {
		String providerClassName = PropertiesHelper.getString( Environment.CACHE_PROVIDER, properties, DEF_PROVIDER );
		log.info( "Cache provider: " + providerClassName );
		try {
			cacheProvider = ( CacheProvider ) ReflectHelper.classForName( providerClassName ).newInstance();
		}
		catch ( Exception cnfe ) {
			throw new CacheException( "could not instantiate CacheProvider [" + providerClassName + "]", cnfe );
		}
	}

	public void start(Settings settings, Properties properties) throws CacheException {
		this.settings = settings;
		cacheProvider.start( properties );
	}

	public void stop() {
		cacheProvider.stop();
		cacheProvider = null;
	}

	public boolean isMinimalPutsEnabledByDefault() {
		return cacheProvider.isMinimalPutsEnabledByDefault();
	}

	/**
	 * {@inheritDoc}
	 */
	public AccessType getDefaultAccessType() {
		// we really have no idea
		return null;
	}

	public long nextTimestamp() {
		return cacheProvider.nextTimestamp();
	}

	public CacheProvider getCacheProvider() {
		return cacheProvider;
	}

	public EntityRegion buildEntityRegion(
			String regionName,
			Properties properties,
			CacheDataDescription metadata) throws CacheException {
		return new EntityRegionAdapter( cacheProvider.buildCache( regionName, properties ), settings, metadata );
	}

	public CollectionRegion buildCollectionRegion(
			String regionName,
			Properties properties,
			CacheDataDescription metadata) throws CacheException {
		return new CollectionRegionAdapter( cacheProvider.buildCache( regionName, properties ), settings, metadata );
	}

	public QueryResultsRegion buildQueryResultsRegion(String regionName, Properties properties) throws CacheException {
		return new QueryResultsRegionAdapter( cacheProvider.buildCache( regionName, properties ), settings );
	}

	public TimestampsRegion buildTimestampsRegion(String regionName, Properties properties) throws CacheException {
		return new TimestampsRegionAdapter( cacheProvider.buildCache( regionName, properties ), settings );
	}


}

/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.event.def;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.hibernate.HibernateException;
import org.hibernate.cache.CacheKey;
import org.hibernate.cache.entry.CollectionCacheEntry;
import org.hibernate.collection.PersistentCollection;
import org.hibernate.engine.CollectionEntry;
import org.hibernate.engine.PersistenceContext;
import org.hibernate.engine.SessionFactoryImplementor;
import org.hibernate.event.InitializeCollectionEvent;
import org.hibernate.event.InitializeCollectionEventListener;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.persister.collection.CollectionPersister;
import org.hibernate.pretty.MessageHelper;

/**
 * @author Gavin King
 */
public class DefaultInitializeCollectionEventListener implements InitializeCollectionEventListener {

	private static final Logger log = LoggerFactory.getLogger(DefaultInitializeCollectionEventListener.class);

	/**
	 * called by a collection that wants to initialize itself
	 */
	public void onInitializeCollection(InitializeCollectionEvent event)
	throws HibernateException {

		PersistentCollection collection = event.getCollection();
		SessionImplementor source = event.getSession();

		CollectionEntry ce = source.getPersistenceContext().getCollectionEntry(collection);
		if (ce==null) throw new HibernateException("collection was evicted");
		if ( !collection.wasInitialized() ) {
			if ( log.isTraceEnabled() ) {
				log.trace(
						"initializing collection " +
						MessageHelper.collectionInfoString( ce.getLoadedPersister(), ce.getLoadedKey(), source.getFactory() )
					);
			}

			log.trace("checking second-level cache");
			final boolean foundInCache = initializeCollectionFromCache(
					ce.getLoadedKey(),
					ce.getLoadedPersister(),
					collection,
					source
				);

			if (foundInCache) {
				log.trace("collection initialized from cache");
			}
			else {
				log.trace("collection not cached");
				ce.getLoadedPersister().initialize( ce.getLoadedKey(), source );
				log.trace("collection initialized");

				if ( source.getFactory().getStatistics().isStatisticsEnabled() ) {
					source.getFactory().getStatisticsImplementor().fetchCollection( 
							ce.getLoadedPersister().getRole() 
						);
				}
			}
		}
	}

	/**
	 * Try to initialize a collection from the cache
	 *
	 * @param id The id of the collection of initialize
	 * @param persister The collection persister
	 * @param collection The collection to initialize
	 * @param source The originating session
	 * @return true if we were able to initialize the collection from the cache;
	 * false otherwise.
	 */
	private boolean initializeCollectionFromCache(
			Serializable id,
			CollectionPersister persister,
			PersistentCollection collection,
			SessionImplementor source) {

		if ( !source.getEnabledFilters().isEmpty() && persister.isAffectedByEnabledFilters( source ) ) {
			log.trace( "disregarding cached version (if any) of collection due to enabled filters ");
			return false;
		}

		final boolean useCache = persister.hasCache() && 
				source.getCacheMode().isGetEnabled();

		if ( !useCache ) {
			return false;
		}
		else {
			
			final SessionFactoryImplementor factory = source.getFactory();

			final CacheKey ck = new CacheKey( 
					id, 
					persister.getKeyType(), 
					persister.getRole(), 
					source.getEntityMode(), 
					source.getFactory() 
				);
			Object ce = persister.getCacheAccessStrategy().get( ck, source.getTimestamp() );
			
			if ( factory.getStatistics().isStatisticsEnabled() ) {
				if ( ce == null ) {
					factory.getStatisticsImplementor().secondLevelCacheMiss(
							persister.getCacheAccessStrategy().getRegion().getName()
					);
				}
				else {
					factory.getStatisticsImplementor().secondLevelCacheHit(
							persister.getCacheAccessStrategy().getRegion().getName()
					);
				}

				
			}
			
			if (ce==null) {
				return false;
			}
			else {

				CollectionCacheEntry cacheEntry = (CollectionCacheEntry) persister.getCacheEntryStructure()
						.destructure(ce, factory);
			
				final PersistenceContext persistenceContext = source.getPersistenceContext();
				cacheEntry.assemble(
						collection, 
						persister,  
						persistenceContext.getCollectionOwner(id, persister)
					);
				persistenceContext.getCollectionEntry(collection).postInitialize(collection);
				//addInitializedCollection(collection, persister, id);
				return true;
			}
			
		}
	}


}

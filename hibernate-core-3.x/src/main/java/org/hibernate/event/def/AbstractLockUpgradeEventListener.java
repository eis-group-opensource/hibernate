/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.event.def;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.hibernate.LockMode;
import org.hibernate.ObjectDeletedException;
import org.hibernate.LockOptions;
import org.hibernate.event.EventSource;
import org.hibernate.cache.CacheKey;
import org.hibernate.cache.access.SoftLock;
import org.hibernate.engine.EntityEntry;
import org.hibernate.engine.Status;
import org.hibernate.persister.entity.EntityPersister;
import org.hibernate.pretty.MessageHelper;

/**
 * A convenience base class for listeners that respond to requests to perform a
 * pessimistic lock upgrade on an entity.
 *
 * @author Gavin King
 */
public class AbstractLockUpgradeEventListener extends AbstractReassociateEventListener {

	private static final Logger log = LoggerFactory.getLogger(AbstractLockUpgradeEventListener.class);

	/**
	 * Performs a pessimistic lock upgrade on a given entity, if needed.
	 *
	 * @param object The entity for which to upgrade the lock.
	 * @param entry The entity's EntityEntry instance.
	 * @param lockOptions contains the requested lock mode.
	 * @param source The session which is the source of the event being processed.
	 */
	protected void upgradeLock(Object object, EntityEntry entry, LockOptions lockOptions, EventSource source) {

		LockMode requestedLockMode = lockOptions.getLockMode();
		if ( requestedLockMode.greaterThan( entry.getLockMode() ) ) {
			// The user requested a "greater" (i.e. more restrictive) form of
			// pessimistic lock

			if ( entry.getStatus() != Status.MANAGED ) {
				throw new ObjectDeletedException(
						"attempted to lock a deleted instance",
						entry.getId(),
						entry.getPersister().getEntityName()
				);
			}

			final EntityPersister persister = entry.getPersister();

			if ( log.isTraceEnabled() )
				log.trace(
						"locking " +
						MessageHelper.infoString( persister, entry.getId(), source.getFactory() ) +
						" in mode: " +
						requestedLockMode
				);

			final SoftLock lock;
			final CacheKey ck;
			if ( persister.hasCache() ) {
				ck = new CacheKey( 
						entry.getId(), 
						persister.getIdentifierType(), 
						persister.getRootEntityName(), 
						source.getEntityMode(), 
						source.getFactory() 
				);
				lock = persister.getCacheAccessStrategy().lockItem( ck, entry.getVersion() );
			}
			else {
				ck = null;
				lock = null;
			}
			
			try {
				if ( persister.isVersioned() && requestedLockMode == LockMode.FORCE  ) {
					// todo : should we check the current isolation mode explicitly?
					Object nextVersion = persister.forceVersionIncrement(
							entry.getId(), entry.getVersion(), source
					);
					entry.forceLocked( object, nextVersion );
				}
				else {
					persister.lock( entry.getId(), entry.getVersion(), object, lockOptions, source );
				}
				entry.setLockMode(requestedLockMode);
			}
			finally {
				// the database now holds a lock + the object is flushed from the cache,
				// so release the soft lock
				if ( persister.hasCache() ) {
					persister.getCacheAccessStrategy().unlockItem( ck, lock );
				}
			}

		}
	}

}

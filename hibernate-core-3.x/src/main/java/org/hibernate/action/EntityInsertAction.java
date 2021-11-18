/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.action;

import java.io.Serializable;

import org.hibernate.AssertionFailure;
import org.hibernate.HibernateException;
import org.hibernate.cache.CacheKey;
import org.hibernate.cache.entry.CacheEntry;
import org.hibernate.engine.EntityEntry;
import org.hibernate.engine.SessionFactoryImplementor;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.engine.Versioning;
import org.hibernate.event.PostInsertEvent;
import org.hibernate.event.PostInsertEventListener;
import org.hibernate.event.PreInsertEvent;
import org.hibernate.event.PreInsertEventListener;
import org.hibernate.event.EventSource;
import org.hibernate.persister.entity.EntityPersister;

public final class EntityInsertAction extends EntityAction {

	private Object[] state;
	private Object version;
	private Object cacheEntry;

	public EntityInsertAction(
	        Serializable id,
	        Object[] state,
	        Object instance,
	        Object version,
	        EntityPersister persister,
	        SessionImplementor session) throws HibernateException {
		super( session, id, instance, persister );
		this.state = state;
		this.version = version;
	}

	public Object[] getState() {
		return state;
	}

	public void execute() throws HibernateException {
		EntityPersister persister = getPersister();
		SessionImplementor session = getSession();
		Object instance = getInstance();
		Serializable id = getId();

		boolean veto = preInsert();

		// Don't need to lock the cache here, since if someone
		// else inserted the same pk first, the insert would fail

		if ( !veto ) {
			
			persister.insert( id, state, instance, session );
		
			EntityEntry entry = session.getPersistenceContext().getEntry( instance );
			if ( entry == null ) {
				throw new AssertionFailure( "possible nonthreadsafe access to session" );
			}
			
			entry.postInsert();
	
			if ( persister.hasInsertGeneratedProperties() ) {
				persister.processInsertGeneratedProperties( id, instance, state, session );
				if ( persister.isVersionPropertyGenerated() ) {
					version = Versioning.getVersion(state, persister);
				}
				entry.postUpdate(instance, state, version);
			}
			
		}

		final SessionFactoryImplementor factory = getSession().getFactory();

		if ( isCachePutEnabled( persister, session ) ) {
			
			CacheEntry ce = new CacheEntry(
					state,
					persister, 
					persister.hasUninitializedLazyProperties( instance, session.getEntityMode() ),
					version,
					session,
					instance
				);
			
			cacheEntry = persister.getCacheEntryStructure().structure(ce);
			final CacheKey ck = new CacheKey( 
					id, 
					persister.getIdentifierType(), 
					persister.getRootEntityName(), 
					session.getEntityMode(), 
					session.getFactory() 
				);
			boolean put = persister.getCacheAccessStrategy().insert( ck, cacheEntry, version );
			
			if ( put && factory.getStatistics().isStatisticsEnabled() ) {
				factory.getStatisticsImplementor().secondLevelCachePut( getPersister().getCacheAccessStrategy().getRegion().getName() );
			}
			
		}

		postInsert();

		if ( factory.getStatistics().isStatisticsEnabled() && !veto ) {
			factory.getStatisticsImplementor()
					.insertEntity( getPersister().getEntityName() );
		}

	}

	private void postInsert() {
		PostInsertEventListener[] postListeners = getSession().getListeners()
				.getPostInsertEventListeners();
		if ( postListeners.length > 0 ) {
			PostInsertEvent postEvent = new PostInsertEvent(
					getInstance(),
					getId(),
					state,
					getPersister(),
					(EventSource) getSession()
			);
			for ( int i = 0; i < postListeners.length; i++ ) {
				postListeners[i].onPostInsert(postEvent);
			}
		}
	}

	private void postCommitInsert() {
		PostInsertEventListener[] postListeners = getSession().getListeners()
				.getPostCommitInsertEventListeners();
		if ( postListeners.length > 0 ) {
			PostInsertEvent postEvent = new PostInsertEvent(
					getInstance(),
					getId(),
					state,
					getPersister(),
					(EventSource) getSession() 
			);
			for ( int i = 0; i < postListeners.length; i++ ) {
				postListeners[i].onPostInsert(postEvent);
			}
		}
	}

	private boolean preInsert() {
		PreInsertEventListener[] preListeners = getSession().getListeners()
				.getPreInsertEventListeners();
		boolean veto = false;
		if (preListeners.length>0) {
			PreInsertEvent preEvent = new PreInsertEvent( getInstance(), getId(), state, getPersister(), (EventSource)getSession() );
			for ( int i = 0; i < preListeners.length; i++ ) {
				veto = preListeners[i].onPreInsert(preEvent) || veto;
			}
		}
		return veto;
	}

	/**
	 * {@inheritDoc}
	 */
	public void doAfterTransactionCompletion(boolean success, SessionImplementor session) throws HibernateException {
		EntityPersister persister = getPersister();
		if ( success && isCachePutEnabled( persister, getSession() ) ) {
			final CacheKey ck = new CacheKey( 
					getId(), 
					persister.getIdentifierType(), 
					persister.getRootEntityName(), 
					getSession().getEntityMode(), 
					getSession().getFactory() 
			);
			boolean put = persister.getCacheAccessStrategy().afterInsert( ck, cacheEntry, version );
			
			if ( put && getSession().getFactory().getStatistics().isStatisticsEnabled() ) {
				getSession().getFactory().getStatisticsImplementor()
						.secondLevelCachePut( getPersister().getCacheAccessStrategy().getRegion().getName() );
			}
		}
		postCommitInsert();
	}

	protected boolean hasPostCommitEventListeners() {
		return getSession().getListeners().getPostCommitInsertEventListeners().length>0;
	}
	
	private boolean isCachePutEnabled(EntityPersister persister, SessionImplementor session) {
		return persister.hasCache()
				&& !persister.isCacheInvalidationRequired()
				&& session.getCacheMode().isPutEnabled();
	}

}

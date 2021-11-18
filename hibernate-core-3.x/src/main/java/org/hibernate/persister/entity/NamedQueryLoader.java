/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.persister.entity;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.LockOptions;
import org.hibernate.engine.EntityKey;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.impl.AbstractQueryImpl;
import org.hibernate.loader.entity.UniqueEntityLoader;

/**
 * Not really a <tt>Loader</tt>, just a wrapper around a
 * named query.
 *
 * @author Gavin King
 * @author Steve Ebersole
 */
public final class NamedQueryLoader implements UniqueEntityLoader {
	private final String queryName;
	private final EntityPersister persister;
	
	private static final Logger log = LoggerFactory.getLogger(NamedQueryLoader.class);

	public NamedQueryLoader(String queryName, EntityPersister persister) {
		super();
		this.queryName = queryName;
		this.persister = persister;
	}

	public Object load(Serializable id, Object optionalObject, SessionImplementor session, LockOptions lockOptions) {
		if ( lockOptions != null ) {
			log.debug( "Ignoring lock-options passed to named query loader" );
		}
		return load( id, optionalObject, session );
	}

	public Object load(Serializable id, Object optionalObject, SessionImplementor session) {
		if ( log.isDebugEnabled() ) {
			log.debug(
					"loading entity: " + persister.getEntityName() + 
					" using named query: " + queryName 
				);
		}
		
		AbstractQueryImpl query = (AbstractQueryImpl) session.getNamedQuery(queryName);
		if ( query.hasNamedParameters() ) {
			query.setParameter( 
					query.getNamedParameters()[0], 
					id, 
					persister.getIdentifierType() 
				);
		}
		else {
			query.setParameter( 0, id, persister.getIdentifierType() );
		}
		query.setOptionalId(id);
		query.setOptionalEntityName( persister.getEntityName() );
		query.setOptionalObject(optionalObject);
		query.setFlushMode( FlushMode.MANUAL );
		query.list();
		
		// now look up the object we are really interested in!
		// (this lets us correctly handle proxies and multi-row
		// or multi-column queries)
		return session.getPersistenceContext()
				.getEntity( new EntityKey( id, persister, session.getEntityMode() ) );

	}
}
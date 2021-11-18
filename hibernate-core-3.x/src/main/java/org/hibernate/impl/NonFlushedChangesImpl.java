/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.impl;

/**
 * An implementation of NonFlushedChanges.
 *
 * @author Gail Badner
 */

import org.hibernate.engine.NonFlushedChanges;
import org.hibernate.engine.ActionQueue;
import org.hibernate.engine.StatefulPersistenceContext;
import org.hibernate.event.EventSource;
import org.hibernate.AssertionFailure;
import org.hibernate.EntityMode;

import java.util.Map;
import java.util.HashMap;
import java.io.Serializable;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class NonFlushedChangesImpl implements NonFlushedChanges {

	private static final Logger log = LoggerFactory.getLogger(NonFlushedChangesImpl.class);

	private static class SessionNonFlushedChanges implements Serializable {
		private transient EntityMode entityMode;
		private transient ActionQueue actionQueue;
		private transient StatefulPersistenceContext persistenceContext;

		public SessionNonFlushedChanges(EventSource session) {
			this.entityMode = session.getEntityMode();
			this.actionQueue = session.getActionQueue();
			this.persistenceContext = ( StatefulPersistenceContext ) session.getPersistenceContext();
		}

		private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
			ois.defaultReadObject();
			entityMode = EntityMode.parse( ( String ) ois.readObject() );
			persistenceContext = StatefulPersistenceContext.deserialize( ois, null );
			actionQueue = ActionQueue.deserialize( ois, null );
		}

		private void writeObject(ObjectOutputStream oos) throws IOException {
			log.trace( "serializing SessionNonFlushedChanges" );
			oos.defaultWriteObject();
			oos.writeObject( entityMode.toString() );
			persistenceContext.serialize( oos );
			actionQueue.serialize( oos );
		}
	}
	private Map nonFlushedChangesByEntityMode = new HashMap();

	public NonFlushedChangesImpl( EventSource session ) {
		extractFromSession( session );
	}

	public void extractFromSession(EventSource session) {
		if ( nonFlushedChangesByEntityMode.containsKey( session.getEntityMode() ) ) {
			throw new AssertionFailure( "Already has non-flushed changes for entity mode: " + session.getEntityMode() );
		}
		nonFlushedChangesByEntityMode.put( session.getEntityMode(), new SessionNonFlushedChanges( session ) );
	}

	private SessionNonFlushedChanges getSessionNonFlushedChanges(EntityMode entityMode) {
		return ( SessionNonFlushedChanges ) nonFlushedChangesByEntityMode.get( entityMode );
	}

	/* package-protected */
	ActionQueue getActionQueue(EntityMode entityMode) {
		return getSessionNonFlushedChanges( entityMode ).actionQueue;
	}	

	/* package-protected */
	StatefulPersistenceContext getPersistenceContext(EntityMode entityMode) {
		return getSessionNonFlushedChanges( entityMode ).persistenceContext;
	}

	public void clear() {
		nonFlushedChangesByEntityMode.clear();
	}
}
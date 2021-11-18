/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.event.def;

import java.io.Serializable;

import org.hibernate.HibernateException;
import org.hibernate.ObjectDeletedException;
import org.hibernate.EntityMode;
import org.hibernate.engine.EntityEntry;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.engine.Status;
import org.hibernate.event.SaveOrUpdateEvent;
import org.hibernate.persister.entity.EntityPersister;

/**
 * An event handler for update() events
 * @author Gavin King
 */
public class DefaultUpdateEventListener extends DefaultSaveOrUpdateEventListener {

	protected Serializable performSaveOrUpdate(SaveOrUpdateEvent event) {
		// this implementation is supposed to tolerate incorrect unsaved-value
		// mappings, for the purpose of backward-compatibility
		EntityEntry entry = event.getSession().getPersistenceContext().getEntry( event.getEntity() );
		if ( entry!=null ) {
			if ( entry.getStatus()==Status.DELETED ) {
				throw new ObjectDeletedException( "deleted instance passed to update()", null, event.getEntityName() );
			}
			else {
				return entityIsPersistent(event);
			}
		}
		else {
			entityIsDetached(event);
			return null;
		}
	}
	
	/**
	 * If the user specified an id, assign it to the instance and use that, 
	 * otherwise use the id already assigned to the instance
	 */
	protected Serializable getUpdateId(
			Object entity,
			EntityPersister persister,
			Serializable requestedId,
			SessionImplementor session) throws HibernateException {
		if ( requestedId == null ) {
			return super.getUpdateId( entity, persister, requestedId, session );
		}
		else {
			persister.setIdentifier( entity, requestedId, session );
			return requestedId;
		}
	}

}

/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.event;

import java.io.Serializable;

import org.hibernate.persister.entity.EntityPersister;

/**
 * Represents a <tt>pre-delete</tt> event, which occurs just prior to
 * performing the deletion of an entity from the database.
 * 
 * @author Gavin King
 * @author Steve Ebersole
 */
public class PreDeleteEvent extends AbstractPreDatabaseOperationEvent {
	private Object[] deletedState;

	/**
	 *
	 * Constructs an event containing the pertinent information.
	 *
	 * @param entity The entity to be deleted.
	 * @param id The id to use in the deletion.
	 * @param deletedState The entity's state at deletion time.
	 * @param persister The entity's persister.
	 * @param source The session from which the event originated.
	 */
	public PreDeleteEvent(
			Object entity,
			Serializable id,
			Object[] deletedState,
			EntityPersister persister,
			EventSource source) {
	    super( source, entity, id, persister );
		this.deletedState = deletedState;
	}

	/**
	 * Getter for property 'deletedState'.  This is the entity state at the
	 * time of deletion (useful for optomistic locking and such).
	 *
	 * @return Value for property 'deletedState'.
	 */
	public Object[] getDeletedState() {
		return deletedState;
	}

}

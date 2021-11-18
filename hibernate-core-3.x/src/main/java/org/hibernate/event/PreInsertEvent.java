/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.event;

import java.io.Serializable;

import org.hibernate.persister.entity.EntityPersister;

/**
 * Represents a <tt>pre-insert</tt> event, which occurs just prior to
 * performing the insert of an entity into the database.
 *
 * @author Gavin King
 * @author Steve Ebersole
 */
public class PreInsertEvent extends AbstractPreDatabaseOperationEvent {
	private Object[] state;

	/**
	 * Constructs an event containing the pertinent information.
	 *
	 * @param entity The entity to be inserted.
	 * @param id The id to use in the insertion.
	 * @param state The state to be inserted.
	 * @param persister The entity's persister.
	 * @param source The session from which the event originated.
	 */
	public PreInsertEvent(
			Object entity,
			Serializable id,
			Object[] state,
			EntityPersister persister,
			EventSource source) {
		super( source, entity, id, persister );
		this.state = state;
	}

	/**
	 * Getter for property 'state'.  These are the values to be inserted.
	 *
	 * @return Value for property 'state'.
	 */
	public Object[] getState() {
		return state;
	}
}

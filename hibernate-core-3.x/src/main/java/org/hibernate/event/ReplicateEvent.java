/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.event;

import org.hibernate.ReplicationMode;

/**
 *  Defines an event class for the replication of an entity.
 *
 * @author Steve Ebersole
 */
public class ReplicateEvent extends AbstractEvent {

	private Object object;
	private ReplicationMode replicationMode;
	private String entityName;

	public ReplicateEvent(Object object, ReplicationMode replicationMode, EventSource source) {
		this(null, object, replicationMode, source);
	}
	
	public ReplicateEvent(String entityName, Object object, ReplicationMode replicationMode, EventSource source) {
		super(source);
		this.entityName = entityName;

		if ( object == null ) {
			throw new IllegalArgumentException(
					"attempt to create replication strategy with null entity"
			);
		}
		if ( replicationMode == null ) {
			throw new IllegalArgumentException(
					"attempt to create replication strategy with null replication mode"
			);
		}

		this.object = object;
		this.replicationMode = replicationMode;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public ReplicationMode getReplicationMode() {
		return replicationMode;
	}

	public void setReplicationMode(ReplicationMode replicationMode) {
		this.replicationMode = replicationMode;
	}

	public String getEntityName() {
		return entityName;
	}
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
}

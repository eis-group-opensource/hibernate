/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.event;

import org.hibernate.engine.EntityEntry;

/**
 * @author Gavin King
 */
public class FlushEntityEvent extends AbstractEvent {
	
	private Object entity;
	private Object[] propertyValues;
	private Object[] databaseSnapshot;
	private int[] dirtyProperties;
	private boolean hasDirtyCollection;
	private boolean dirtyCheckPossible;
	private boolean dirtyCheckHandledByInterceptor;
	private EntityEntry entityEntry;
	
	public FlushEntityEvent(EventSource source, Object entity, EntityEntry entry) {
		super(source);
		this.entity = entity;
		this.entityEntry = entry;
	}

	public EntityEntry getEntityEntry() {
		return entityEntry;
	}
	public Object[] getDatabaseSnapshot() {
		return databaseSnapshot;
	}
	public void setDatabaseSnapshot(Object[] databaseSnapshot) {
		this.databaseSnapshot = databaseSnapshot;
	}
	public boolean hasDatabaseSnapshot() {
		return databaseSnapshot!=null;
	}
	public boolean isDirtyCheckHandledByInterceptor() {
		return dirtyCheckHandledByInterceptor;
	}
	public void setDirtyCheckHandledByInterceptor(boolean dirtyCheckHandledByInterceptor) {
		this.dirtyCheckHandledByInterceptor = dirtyCheckHandledByInterceptor;
	}
	public boolean isDirtyCheckPossible() {
		return dirtyCheckPossible;
	}
	public void setDirtyCheckPossible(boolean dirtyCheckPossible) {
		this.dirtyCheckPossible = dirtyCheckPossible;
	}
	public int[] getDirtyProperties() {
		return dirtyProperties;
	}
	public void setDirtyProperties(int[] dirtyProperties) {
		this.dirtyProperties = dirtyProperties;
	}
	public boolean hasDirtyCollection() {
		return hasDirtyCollection;
	}
	public void setHasDirtyCollection(boolean hasDirtyCollection) {
		this.hasDirtyCollection = hasDirtyCollection;
	}
	public Object[] getPropertyValues() {
		return propertyValues;
	}
	public void setPropertyValues(Object[] propertyValues) {
		this.propertyValues = propertyValues;
	}
	public Object getEntity() {
		return entity;
	}
}

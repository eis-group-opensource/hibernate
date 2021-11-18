/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.event;

import java.io.Serializable;

import org.hibernate.persister.entity.EntityPersister;

/**
 * Called before injecting property values into a newly 
 * loaded entity instance.
 *
 * @author Gavin King
 */
public class PreLoadEvent extends AbstractEvent {
	private Object entity;
	private Object[] state;
	private Serializable id;
	private EntityPersister persister;

	public PreLoadEvent(EventSource session) {
		super(session);
	}

	public Object getEntity() {
		return entity;
	}
	
	public Serializable getId() {
		return id;
	}
	
	public EntityPersister getPersister() {
		return persister;
	}
	
	public Object[] getState() {
		return state;
	}

	public PreLoadEvent setEntity(Object entity) {
		this.entity = entity;
		return this;
	}
	
	public PreLoadEvent setId(Serializable id) {
		this.id = id;
		return this;
	}
	
	public PreLoadEvent setPersister(EntityPersister persister) {
		this.persister = persister;
		return this;
	}
	
	public PreLoadEvent setState(Object[] state) {
		this.state = state;
		return this;
	}
	
}

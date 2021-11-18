/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.loader.entity;

import java.io.Serializable;

import org.hibernate.HibernateException;
import org.hibernate.LockOptions;
import org.hibernate.engine.SessionImplementor;

/**
 * Loads entities for a <tt>EntityPersister</tt>
 *
 * @author Gavin King
 * @author Steve Ebersole
 */
public interface UniqueEntityLoader {
	/**
	 * Load an entity instance. If <tt>optionalObject</tt> is supplied,
	 * load the entity state into the given (uninitialized) object.
	 *
	 * @deprecated use {@link #load(java.io.Serializable, Object, SessionImplementor, LockOptions)} instead.
	 * @noinspection JavaDoc
	 */
	public Object load(Serializable id, Object optionalObject, SessionImplementor session) throws HibernateException;

	/**
	 * Load an entity instance by id.  If <tt>optionalObject</tt> is supplied (non-<tt>null</tt>,
	 * the entity state is loaded into that object instance instead of instantiating a new one.
	 *
	 * @param id The id to be loaded
	 * @param optionalObject The (optional) entity instance in to which to load the state
	 * @param session The session from which the request originated
	 * @param lockOptions The lock options.
	 *
	 * @return The loaded entity
	 *
	 * @throws HibernateException indicates problem performing the load.
	 */
	public Object load(Serializable id, Object optionalObject, SessionImplementor session, LockOptions lockOptions);
}

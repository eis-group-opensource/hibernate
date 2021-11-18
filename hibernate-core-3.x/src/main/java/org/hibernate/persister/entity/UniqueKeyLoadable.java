/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.persister.entity;

import org.hibernate.HibernateException;
import org.hibernate.engine.SessionImplementor;

/**
 * @author Gavin King
 */
public interface UniqueKeyLoadable extends Loadable {
	/**
	 * Load an instance of the persistent class, by a unique key other
	 * than the primary key.
	 */
	public Object loadByUniqueKey(String propertyName, Object uniqueKey, SessionImplementor session) 
	throws HibernateException;
	/**
	 * Get the property number of the unique key property
	 */
	public int getPropertyIndex(String propertyName);

}

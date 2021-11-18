/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.property;

import java.io.Serializable;
import java.lang.reflect.Method;

import org.hibernate.HibernateException;
import org.hibernate.engine.SessionFactoryImplementor;

/**
 * Sets values to a particular property.
 * 
 * @author Gavin King
 */
public interface Setter extends Serializable {
	/**
	 * Set the property value from the given instance
	 *
	 * @param target The instance upon which to set the given value.
	 * @param value The value to be set on the target.
	 * @param factory The session factory from which this request originated.
	 * @throws HibernateException
	 */
	public void set(Object target, Object value, SessionFactoryImplementor factory) throws HibernateException;
	/**
	 * Optional operation (return null)
	 */
	public String getMethodName();
	/**
	 * Optional operation (return null)
	 */
	public Method getMethod();
}

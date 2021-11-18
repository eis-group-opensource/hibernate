/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.type;

import java.util.Comparator;

import org.hibernate.engine.SessionImplementor;

/**
 * A <tt>Type</tt> that may be used to version data.
 * @author Gavin King
 */
public interface VersionType extends Type {
	/**
	 * Generate an initial version.
	 *
	 * @param session The session from which this request originates.
	 * @return an instance of the type
	 */
	public Object seed(SessionImplementor session);

	/**
	 * Increment the version.
	 *
	 * @param session The session from which this request originates.
	 * @param current the current version
	 * @return an instance of the type
	 */
	public Object next(Object current, SessionImplementor session);

	/**
	 * Get a comparator for version values.
	 *
	 * @return The comparator to use to compare different version values.
	 */
	public Comparator getComparator();

	/**
	 * Are the two version values considered equal?
	 *
	 * @param x One value to check.
	 * @param y The other value to check.
	 * @return true if the values are equal, false otherwise.
	 */
	public boolean isEqual(Object x, Object y);
}







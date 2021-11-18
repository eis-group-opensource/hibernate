/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.stat;

import java.util.Set;

/**
 * Information about the first-level (session) cache
 * for a particular session instance
 * @author Gavin King
 */
public interface SessionStatistics {

	/**
	 * Get the number of entity instances associated with the session
	 */
	public int getEntityCount();
	/**
	 * Get the number of collection instances associated with the session
	 */
	public int getCollectionCount();

	/**
	 * Get the set of all <tt>EntityKey</tt>s
	 * @see org.hibernate.engine.EntityKey
	 */
	public Set getEntityKeys();
	/**
	 * Get the set of all <tt>CollectionKey</tt>s
	 * @see org.hibernate.engine.CollectionKey
	 */
	public Set getCollectionKeys();
	
}

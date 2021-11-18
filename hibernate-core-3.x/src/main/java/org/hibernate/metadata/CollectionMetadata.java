/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.metadata;

import org.hibernate.type.Type;

/**
 * Exposes collection metadata to the application
 *
 * @author Gavin King
 */
public interface CollectionMetadata {
	/**
	 * The collection key type
	 */
	public Type getKeyType();
	/**
	 * The collection element type
	 */
	public Type getElementType();
	/**
	 * The collection index type (or null if the collection has no index)
	 */
	public Type getIndexType();
	/**
	 * Is this collection indexed?
	 */
	public boolean hasIndex();
	/**
	 * The name of this collection role
	 */
	public String getRole();
	/**
	 * Is the collection an array?
	 */
	public boolean isArray();
	/**
	 * Is the collection a primitive array?
	 */
	public boolean isPrimitiveArray();
	/**
	 * Is the collection lazily initialized?
	 */
	public boolean isLazy();
}







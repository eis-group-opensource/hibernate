/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.cache.entry;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import org.hibernate.engine.SessionFactoryImplementor;

/**
 * @author Gavin King
 */
public class StructuredCollectionCacheEntry implements CacheEntryStructure {

	public Object structure(Object item) {
		CollectionCacheEntry entry = (CollectionCacheEntry) item;
		return Arrays.asList( entry.getState() );
	}
	
	public Object destructure(Object item, SessionFactoryImplementor factory) {
		List list = (List) item;
		return new CollectionCacheEntry( list.toArray( new Serializable[list.size()] ) );
	}

}

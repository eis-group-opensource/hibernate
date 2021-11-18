/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.cache.entry;

import org.hibernate.engine.SessionFactoryImplementor;


/**
 * @author Gavin King
 */
public class UnstructuredCacheEntry implements CacheEntryStructure {

	public Object structure(Object item) {
		return item;
	}

	public Object destructure(Object map, SessionFactoryImplementor factory) {
		return map;
	}

}

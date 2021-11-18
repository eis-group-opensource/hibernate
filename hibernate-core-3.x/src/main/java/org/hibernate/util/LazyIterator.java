/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.util;

import java.util.Iterator;
import java.util.Map;

public final class LazyIterator implements Iterator {
	
	private final Map map;
	private Iterator iterator;
	
	private Iterator getIterator() {
		if (iterator==null) {
			iterator = map.values().iterator();
		}
		return iterator;
	}

	public LazyIterator(Map map) {
		this.map = map;
	}
	
	public boolean hasNext() {
		return getIterator().hasNext();
	}

	public Object next() {
		return getIterator().next();
	}

	public void remove() {
		throw new UnsupportedOperationException();
	}

}

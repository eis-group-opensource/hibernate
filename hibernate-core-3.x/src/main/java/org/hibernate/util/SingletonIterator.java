/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.util;

import java.util.Iterator;

/**
 * @author Gavin King
 */
public final class SingletonIterator implements Iterator {

	private Object value;
	private boolean hasNext = true;

	public boolean hasNext() {
		return hasNext;
	}

	public Object next() {
		if (hasNext) {
			hasNext = false;
			return value;
		}
		else {
			throw new IllegalStateException();
		}
	}

	public void remove() {
		throw new UnsupportedOperationException();
	}

	public SingletonIterator(Object value) {
		this.value = value;
	}

}

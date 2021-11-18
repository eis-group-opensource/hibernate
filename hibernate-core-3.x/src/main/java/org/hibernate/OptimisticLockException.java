/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate;

/**
 *
 * Throw when an optimistic locking conflict occurs.
 *
 * @author Scott Marlow
 */
public class OptimisticLockException extends HibernateException {

	Object entity;

	public OptimisticLockException(String s) {
		super(s);
	}

	public OptimisticLockException(String s, Object entity) {
		super(s);
		this.entity = entity;
	}

	public Object getEntity() {
		return entity;
	}


}
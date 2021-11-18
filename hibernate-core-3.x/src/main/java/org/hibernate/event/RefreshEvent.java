/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.event;

import org.hibernate.LockMode;
import org.hibernate.LockOptions;

/**
 *  Defines an event class for the refreshing of an object.
 *
 * @author Steve Ebersole
 */
public class RefreshEvent extends AbstractEvent {

	private Object object;
	private LockOptions lockOptions = new LockOptions().setLockMode(LockMode.READ);

	public RefreshEvent(Object object, EventSource source) {
		super(source);
		if (object == null) {
			throw new IllegalArgumentException("Attempt to generate refresh event with null object");
		}
		this.object = object;
	}

	public RefreshEvent(Object object, LockMode lockMode, EventSource source) {
		this(object, source);
		if (lockMode == null) {
			throw new IllegalArgumentException("Attempt to generate refresh event with null lock mode");
		}
		this.lockOptions.setLockMode(lockMode);
	}

	public RefreshEvent(Object object, LockOptions lockOptions, EventSource source) {
		this(object, source);
		if (lockOptions == null) {
			throw new IllegalArgumentException("Attempt to generate refresh event with null lock request");
		}
		this.lockOptions = lockOptions;
	}

	public Object getObject() {
		return object;
	}

	public LockOptions getLockOptions() {
		return lockOptions;
	}

	public LockMode getLockMode() {
		return lockOptions.getLockMode();
	}

	public int getLockTimeout() {
		return this.lockOptions.getTimeOut();
	}

	public boolean getLockScope() {
		return this.lockOptions.getScope();
	}
}

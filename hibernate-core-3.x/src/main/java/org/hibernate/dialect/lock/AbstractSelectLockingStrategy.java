/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.dialect.lock;

import org.hibernate.LockMode;
import org.hibernate.LockOptions;
import org.hibernate.persister.entity.Lockable;

/**
 * Base {@link LockingStrategy} implementation to support implementations
 * based on issuing <tt>SQL</tt> <tt>SELECT</tt> statements
 *
 * @author Steve Ebersole
 */
public abstract class AbstractSelectLockingStrategy implements LockingStrategy {
	private final Lockable lockable;
	private final LockMode lockMode;
	private final String waitForeverSql;

	protected AbstractSelectLockingStrategy(Lockable lockable, LockMode lockMode) {
		this.lockable = lockable;
		this.lockMode = lockMode;
		this.waitForeverSql = generateLockString( LockOptions.WAIT_FOREVER );
	}

	protected Lockable getLockable() {
		return lockable;
	}

	protected LockMode getLockMode() {
		return lockMode;
	}

	protected abstract String generateLockString(int lockTimeout);

	protected String determineSql(int timeout) {
		return timeout == LockOptions.WAIT_FOREVER
				? waitForeverSql
				: timeout == LockOptions.NO_WAIT
						? getNoWaitSql()
						: generateLockString( timeout );
	}

	private String noWaitSql;

	public String getNoWaitSql() {
		if ( noWaitSql == null ) {
			noWaitSql = generateLockString( LockOptions.NO_WAIT );
		}
		return noWaitSql;
	}
}

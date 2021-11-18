/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.dialect.lock;

import java.io.Serializable;

import org.hibernate.*;
import org.hibernate.event.EventSource;
import org.hibernate.action.EntityVerifyVersionProcess;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.engine.EntityEntry;
import org.hibernate.persister.entity.Lockable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * An optimistic locking strategy that verifies that the version hasn't changed (prior to transaction commit).
 * <p/>
 * This strategy is valid for LockMode.OPTIMISTIC
 *
 * @since 3.5
 *
 * @author Scott Marlow
 */
public class OptimisticLockingStrategy implements LockingStrategy {
	private static final Logger log = LoggerFactory.getLogger( OptimisticLockingStrategy.class );

	private final Lockable lockable;
	private final LockMode lockMode;

	/**
	 * Construct locking strategy.
	 *
	 * @param lockable The metadata for the entity to be locked.
	 * @param lockMode Indictates the type of lock to be acquired.
	 */
	public OptimisticLockingStrategy(Lockable lockable, LockMode lockMode) {
		this.lockable = lockable;
		this.lockMode = lockMode;
		if ( lockMode.lessThan( LockMode.OPTIMISTIC ) ) {
			throw new HibernateException( "[" + lockMode + "] not valid for [" + lockable.getEntityName() + "]" );
		}
	}

   /**
	 * @see org.hibernate.dialect.lock.LockingStrategy#lock
	 */
	public void lock(
      Serializable id,
      Object version,
      Object object,
      int timeout, SessionImplementor session) throws StaleObjectStateException, JDBCException {
		if ( !lockable.isVersioned() ) {
			throw new OptimisticLockException( "[" + lockMode + "] not supported for non-versioned entities [" + lockable.getEntityName() + "]" );
		}
		EntityEntry entry = session.getPersistenceContext().getEntry(object);
		EventSource source = (EventSource)session;
		EntityVerifyVersionProcess verifyVersion = new EntityVerifyVersionProcess(object, entry);
		// Register the EntityVerifyVersionProcess action to run just prior to transaction commit.
		source.getActionQueue().registerProcess(verifyVersion);
	}

	protected LockMode getLockMode() {
		return lockMode;
	}
}
/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.dialect.lock;

import org.hibernate.engine.SessionImplementor;
import org.hibernate.StaleObjectStateException;
import org.hibernate.JDBCException;

import java.io.Serializable;

/**
 * A strategy abstraction for how locks are obtained in the underlying database.
 * <p/>
 * All locking provided implementations assume the underlying database supports
 * (and that the connection is in) at least read-committed transaction isolation.
 * The most glaring exclusion to this is HSQLDB which only offers support for
 * READ_UNCOMMITTED isolation.
 *
 * @see org.hibernate.dialect.Dialect#getLockingStrategy
 * @since 3.2
 *
 * @author Steve Ebersole
 */
public interface LockingStrategy {
	/**
	 * Acquire an appropriate type of lock on the underlying data that will
	 * endure until the end of the current transaction.
	 *
	 * @param id The id of the row to be locked
	 * @param version The current version (or null if not versioned)
	 * @param object The object logically being locked (currently not used)
	 * @param timeout timeout in milliseconds, 0 = no wait, -1 = wait indefinitely
	 * @param session The session from which the lock request originated
	 * @throws StaleObjectStateException Indicates an optimistic lock failure
	 * as part of acquiring the requested database lock.
	 * @throws JDBCException Indicates errors from the <tt>JDBC</tt> driver.
	 */
	public void lock(Serializable id, Object version, Object object, int timeout, SessionImplementor session)
	throws StaleObjectStateException, JDBCException;
}

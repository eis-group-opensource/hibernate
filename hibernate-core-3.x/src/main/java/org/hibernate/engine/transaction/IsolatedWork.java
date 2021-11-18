/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.engine.transaction;

import org.hibernate.HibernateException;

import java.sql.Connection;

/**
 * Represents work that needs to be performed in a manner
 * which isolates it from any current application unit of
 * work transaction.
 *
 * @author Steve Ebersole
 */
public interface IsolatedWork {
	/**
	 * Perform the actual work to be done.
	 *
	 * @param connection The JDBC connection to use.
	 * @throws HibernateException
	 */
	public void doWork(Connection connection) throws HibernateException;
}

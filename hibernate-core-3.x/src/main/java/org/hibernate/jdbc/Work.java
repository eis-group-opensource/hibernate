/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import org.hibernate.HibernateException;

/**
 * Contract for performing a discrete piece of JDBC work.
 *
 * @author Steve Ebersole
 */
public interface Work {
	/**
	 * Execute the discrete work encapsulated by this work instance using the supplied connection.
	 *
	 * @param connection The connection on which to perform the work.
	 * @throws SQLException Thrown during execution of the underlying JDBC interaction.
	 * @throws HibernateException Generally indicates a wrapped SQLException.
	 */
	public void execute(Connection connection) throws SQLException;
}

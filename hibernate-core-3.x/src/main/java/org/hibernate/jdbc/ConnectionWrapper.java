/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.jdbc;

import java.sql.Connection;

/**
 * Interface implemented by JDBC connection wrappers in order to give
 * access to the underlying wrapped connection.
 *
 * @author Steve Ebersole
 */
public interface ConnectionWrapper {
	/**
	 * Get a reference to the wrapped connection.
	 *
	 * @return The wrapped connection.
	 */
	public Connection getWrappedConnection();
}

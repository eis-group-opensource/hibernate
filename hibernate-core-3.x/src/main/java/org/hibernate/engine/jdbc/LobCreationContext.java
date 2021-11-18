/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.engine.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import org.hibernate.JDBCException;
import org.hibernate.exception.SQLExceptionConverter;

/**
 * Provides callback access into the context in which the LOB is to be created.  Mainly this is useful
 * for gaining access to the JDBC {@link Connection} for use in JDBC 4 environments.
 *
 * @author Steve Ebersole
 */
public interface LobCreationContext {
	/**
	 * The callback contract for making use of the JDBC {@link Connection}.
	 */
	public static interface Callback {
		/**
		 * Perform whatever actions are necessary using the provided JDBC {@link Connection}.
		 *
		 * @param connection The JDBC {@link Connection}.
		 * @return The created LOB.
		 * @throws SQLException
		 */
		public Object executeOnConnection(Connection connection) throws SQLException;
	}

	/**
	 * Execute the given callback, making sure it has access to a viable JDBC {@link Connection}.
	 *
	 * @param callback The callback to execute .
	 * @return The LOB created by the callback.
	 */
	public Object execute(Callback callback);
}

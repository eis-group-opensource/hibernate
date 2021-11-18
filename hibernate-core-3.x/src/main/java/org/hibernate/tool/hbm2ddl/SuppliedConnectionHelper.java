/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.tool.hbm2ddl;

import org.hibernate.util.JDBCExceptionReporter;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * A {@link ConnectionHelper} implementation based on an explicitly supplied
 * connection.
 *
 * @author Steve Ebersole
 */
class SuppliedConnectionHelper implements ConnectionHelper {
	private Connection connection;
	private boolean toggleAutoCommit;

	public SuppliedConnectionHelper(Connection connection) {
		this.connection = connection;
	}

	public void prepare(boolean needsAutoCommit) throws SQLException {
		toggleAutoCommit = needsAutoCommit && !connection.getAutoCommit();
		if ( toggleAutoCommit ) {
			try {
				connection.commit();
			}
			catch( Throwable ignore ) {
				// might happen with a managed connection
			}
			connection.setAutoCommit( true );
		}
	}

	public Connection getConnection() {
		return connection;
	}

	public void release() throws SQLException {
		JDBCExceptionReporter.logAndClearWarnings( connection );
		if ( toggleAutoCommit ) {
			connection.setAutoCommit( false );
		}
		connection = null;
	}
}

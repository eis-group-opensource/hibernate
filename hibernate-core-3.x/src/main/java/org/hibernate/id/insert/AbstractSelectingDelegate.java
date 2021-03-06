/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.id.insert;

import org.hibernate.exception.JDBCExceptionHelper;
import org.hibernate.pretty.MessageHelper;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.id.PostInsertIdentityPersister;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.io.Serializable;

/**
 * Abstract InsertGeneratedIdentifierDelegate implementation where the
 * underlying strategy requires an subsequent select after the insert
 * to determine the generated identifier.
 *
 * @author Steve Ebersole
 */
public abstract class AbstractSelectingDelegate implements InsertGeneratedIdentifierDelegate {
	private final PostInsertIdentityPersister persister;

	protected AbstractSelectingDelegate(PostInsertIdentityPersister persister) {
		this.persister = persister;
	}

	public final Serializable performInsert(String insertSQL, SessionImplementor session, Binder binder) {
		try {
			// prepare and execute the insert
			PreparedStatement insert = session.getBatcher().prepareStatement( insertSQL, false );
			try {
				binder.bindValues( insert );
				insert.executeUpdate();
			}
			finally {
				session.getBatcher().closeStatement( insert );
			}
		}
		catch ( SQLException sqle ) {
			throw JDBCExceptionHelper.convert(
					session.getFactory().getSQLExceptionConverter(),
			        sqle,
			        "could not insert: " + MessageHelper.infoString( persister ),
			        insertSQL
				);
		}

		final String selectSQL = getSelectSQL();

		try {
			//fetch the generated id in a separate query
			PreparedStatement idSelect = session.getBatcher().prepareStatement( selectSQL );
			try {
				bindParameters( session, idSelect, binder.getEntity() );
				ResultSet rs = idSelect.executeQuery();
				try {
					return getResult( session, rs, binder.getEntity() );
				}
				finally {
					rs.close();
				}
			}
			finally {
				session.getBatcher().closeStatement( idSelect );
			}

		}
		catch ( SQLException sqle ) {
			throw JDBCExceptionHelper.convert(
					session.getFactory().getSQLExceptionConverter(),
			        sqle,
			        "could not retrieve generated id after insert: " + MessageHelper.infoString( persister ),
			        insertSQL
			);
		}
	}

	/**
	 * Get the SQL statement to be used to retrieve generated key values.
	 *
	 * @return The SQL command string
	 */
	protected abstract String getSelectSQL();

	/**
	 * Bind any required parameter values into the SQL command {@link #getSelectSQL}.
	 *
	 * @param session The session
	 * @param ps The prepared {@link #getSelectSQL SQL} command
	 * @param entity The entity being saved.
	 * @throws SQLException
	 */
	protected void bindParameters(
			SessionImplementor session,
	        PreparedStatement ps,
	        Object entity) throws SQLException {
	}

	/**
	 * Extract the generated key value from the given result set.
	 *
	 * @param session The session
	 * @param rs The result set containing the generated primay key values.
	 * @param entity The entity being saved.
	 * @return The generated identifier
	 * @throws SQLException
	 */
	protected abstract Serializable getResult(
			SessionImplementor session,
	        ResultSet rs,
	        Object entity) throws SQLException;

}

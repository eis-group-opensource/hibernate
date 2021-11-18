/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.param;

import org.hibernate.engine.QueryParameters;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.type.Type;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Maintains information relating to parameters which need to get bound into a
 * JDBC {@link PreparedStatement}.
 *
 * @author Steve Ebersole
 */
public interface ParameterSpecification {
	/**
	 * Bind the appropriate value into the given statement at the specified position.
	 *
	 * @param statement The statement into which the value should be bound.
	 * @param qp The defined values for the current query execution.
	 * @param session The session against which the current execution is occuring.
	 * @param position The position from which to start binding value(s).
	 *
	 * @return The number of sql bind positions "eaten" by this bind operation.
	 * @throws java.sql.SQLException Indicates problems performing the JDBC biind operation.
	 */
	public int bind(PreparedStatement statement, QueryParameters qp, SessionImplementor session, int position) throws SQLException;

	/**
	 * Get the type which we are expeting for a bind into this parameter based
	 * on translated contextual information.
	 *
	 * @return The expected type.
	 */
	public Type getExpectedType();

	/**
	 * Injects the expected type.  Called during translation.
	 *
	 * @param expectedType The type to expect.
	 */
	public void setExpectedType(Type expectedType);

	/**
	 * Render this parameter into displayable info (for logging, etc).
	 *
	 * @return The displayable info.
	 */
	public String renderDisplayInfo();
}

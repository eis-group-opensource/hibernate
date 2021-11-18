/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.engine.jdbc;

import java.sql.ResultSet;

/**
 * Isolates Hibernate interactions with JDBC in terms of variations between JDBC 3 (JDK 1.4 and 1.5)
 * and JDBC 4 (JDK 1.6).
 *
 * @author Steve Ebersole
 */
public interface JdbcSupport {
	/**
	 * Creates an instance of a {@link LobCreator} that does not use the underlying JDBC {@link java.sql.Connection}
	 * to create LOBs.
	 * <p/>
	 * This method is here solely to support the older, now-deprecated method of creating LOBs via
	 * the various {@link org.hibernate.Hibernate#createBlob} and {@link org.hibernate.Hibernate#createClob} methods on
	 * {@link org.hibernate.Hibernate}.
	 *
	 * @return The LOB creator.
	 * @deprecated Use {@link #getLobCreator(LobCreationContext)} instead.
	 */
	public LobCreator getLobCreator();

	/**
	 * Create an instance of a {@link LobCreator} appropriate for the current envionment, mainly meant to account for
	 * variance between JDBC 4 (<= JDK 1.6) and JDBC3 (>= JDK 1.5).
	 *
	 * @param lobCreationContext The context in which the LOB is being created
	 * @return The LOB creator.
	 */
	public LobCreator getLobCreator(LobCreationContext lobCreationContext);

	/**
	 * Wrap the given {@link ResultSet} in one that caches the column-name -> column-index resolution.
	 *
	 * @param resultSet The {@link ResultSet} to wrap.
	 * @param columnNameCache The resolution cache.
	 * @return The wrapper.
	 */
	public ResultSet wrap(ResultSet resultSet, ColumnNameCache columnNameCache);
}

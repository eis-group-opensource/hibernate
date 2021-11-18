/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.engine.jdbc;

import java.sql.ResultSet;

/**
 * TODO : javadoc
 *
 * @author Steve Ebersole
 */
public class JdbcSupportImpl implements JdbcSupport {
	private final boolean useContextualLobCreation;

	public JdbcSupportImpl(boolean useContextualLobCreation) {
		this.useContextualLobCreation = useContextualLobCreation;
	}

	/**
	 * {@inheritDoc}
	 */
	public LobCreator getLobCreator() {
		return NonContextualLobCreator.INSTANCE;
	}

	public LobCreator getLobCreator(LobCreationContext lobCreationContext) {
		if ( useContextualLobCreation ) {
			return new ContextualLobCreator( lobCreationContext );
		}
		else {
			return NonContextualLobCreator.INSTANCE;
		}
	}

	public ResultSet wrap(ResultSet resultSet, ColumnNameCache columnNameCache) {
		return ResultSetWrapperProxy.generateProxy( resultSet, columnNameCache );
	}
}

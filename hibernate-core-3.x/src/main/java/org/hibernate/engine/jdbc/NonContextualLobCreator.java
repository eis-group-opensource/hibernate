/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.engine.jdbc;

import java.sql.Blob;
import java.sql.Clob;
import java.io.InputStream;
import java.io.Reader;

/**
 * {@link LobCreator} implementation using non-contextual or local creation, meaning that we generate the LOB
 * references ourselves as opposed to delegating to the JDBC {@link java.sql.Connection}.
 *
 * @author Steve Ebersole
 * @author Gail Badner
 */
public class NonContextualLobCreator extends AbstractLobCreator implements LobCreator {
	public static final NonContextualLobCreator INSTANCE = new NonContextualLobCreator();

	private NonContextualLobCreator() {
	}

	/**
	 * {@inheritDoc}
	 */
	public Blob createBlob(byte[] bytes) {
		return BlobProxy.generateProxy( bytes );
	}

	/**
	 * {@inheritDoc}
	 */
	public Blob createBlob(InputStream stream, long length) {
		return BlobProxy.generateProxy( stream, length );
	}

	/**
	 * {@inheritDoc}
	 */
	public Clob createClob(String string) {
		return ClobProxy.generateProxy( string );
	}

	/**
	 * {@inheritDoc}
	 */
	public Clob createClob(Reader reader, long length) {
		return ClobProxy.generateProxy( reader, length );
	}

	/**
	 * {@inheritDoc}
	 */
	public Clob createNClob(String string) {
		return NClobProxy.generateProxy( string );
	}

	/**
	 * {@inheritDoc}
	 */
	public Clob createNClob(Reader reader, long length) {
		return NClobProxy.generateProxy( reader, length );
	}
}

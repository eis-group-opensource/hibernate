/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.engine.jdbc;

import java.sql.Blob;
import java.sql.Clob;

/**
 * Convenient base class for proxy-based LobCreator for handling wrapping.
 *
 * @author Steve Ebersole
 */
public abstract class AbstractLobCreator implements LobCreator {
	/**
	 * {@inheritDoc}
	 */
	public Blob wrap(Blob blob) {
		return SerializableBlobProxy.generateProxy( blob );
	}

	/**
	 * {@inheritDoc}
	 */
	public Clob wrap(Clob clob) {
		if ( SerializableNClobProxy.isNClob( clob ) ) {
			return SerializableNClobProxy.generateProxy( clob );
		}
		else {
			return SerializableClobProxy.generateProxy( clob );
		}
	}
}

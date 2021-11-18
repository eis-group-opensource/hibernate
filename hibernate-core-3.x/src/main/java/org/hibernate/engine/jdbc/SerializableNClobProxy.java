/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.engine.jdbc;

import java.sql.Clob;
import java.lang.reflect.Proxy;

/**
 * Manages aspects of proxying java.sql.NClobs to add serializability.
 *
 * @author Steve Ebersole
 */
public class SerializableNClobProxy extends SerializableClobProxy {
	private static final Class NCLOB_CLASS = loadNClobClassIfAvailable();

	private static Class loadNClobClassIfAvailable() {
		try {
			return getProxyClassLoader().loadClass( "java.sql.NClob" );
		}
		catch ( ClassNotFoundException e ) {
			return null;
		}
	}

	private static final Class[] PROXY_INTERFACES = new Class[] { determineNClobInterface(), WrappedClob.class };

	private static Class determineNClobInterface() {
		// java.sql.NClob is a simple marker interface extending java.sql.Clob.  So if java.sql.NClob is not available
		// on the classloader, just use java.sql.Clob
		return NCLOB_CLASS == null ? Clob.class : NCLOB_CLASS;
	}

	public static boolean isNClob(Clob clob) {
		return NCLOB_CLASS != null && NCLOB_CLASS.isInstance( clob );
	}

	/**
	 * Builds a serializable {@link java.sql.Clob} wrapper around the given {@link java.sql.Clob}.
	 *
	 * @param clob The {@link java.sql.Clob} to be wrapped.
	 *
	 * @see #generateProxy(java.sql.Clob)
	 */
	protected SerializableNClobProxy(Clob clob) {
		super( clob );
	}

	/**
	 * Generates a SerializableClobProxy proxy wrapping the provided Clob object.
	 *
	 * @param clob The Clob to wrap.
	 * @return The generated proxy.
	 */
	public static Clob generateProxy(Clob clob) {
		return ( Clob ) Proxy.newProxyInstance(
				getProxyClassLoader(),
				PROXY_INTERFACES,
				new SerializableNClobProxy( clob )
		);
	}

	/**
	 * Determines the appropriate class loader to which the generated proxy
	 * should be scoped.
	 *
	 * @return The class loader appropriate for proxy construction.
	 */
	public static ClassLoader getProxyClassLoader() {
		return SerializableClobProxy.getProxyClassLoader();
	}
}

/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.engine.jdbc;

import java.sql.Clob;
import java.io.Reader;
import java.lang.reflect.Proxy;

/**
 * Manages aspects of proxying java.sql.NClobs for non-contextual creation, including proxy creation and
 * handling proxy invocations.
 * <p/>
 * Generated proxies are typed as {@link java.sql.Clob} (java.sql.NClob extends {@link java.sql.Clob}) and in JDK 1.6 environments, they
 * are also typed to java.sql.NClob
 *
 * @author Steve Ebersole
 */
public class NClobProxy extends ClobProxy {
	public static final Class[] PROXY_INTERFACES = new Class[] { determineNClobInterface(), NClobImplementer.class };

	private static Class determineNClobInterface() {
		// java.sql.NClob is a simple marker interface extending java.sql.Clob.  So if java.sql.NClob is not available
		// on the classloader, just use java.sql.Clob
		try {
			return getProxyClassLoader().loadClass( "java.sql.NClob" );
		}
		catch ( ClassNotFoundException e ) {
			return Clob.class;
		}
	}

	protected NClobProxy(String string) {
		super( string );
	}

	protected NClobProxy(Reader reader, long length) {
		super( reader, length );
	}

	/**
	 * Generates a {@link java.sql.Clob} proxy using the string data.
	 *
	 * @param string The data to be wrapped as a {@link java.sql.Clob}.
	 *
	 * @return The generated proxy.
	 */
	public static Clob generateProxy(String string) {
		return ( Clob ) Proxy.newProxyInstance(
				getProxyClassLoader(),
				PROXY_INTERFACES,
				new ClobProxy( string )
		);
	}

	/**
	 * Generates a {@link Clob} proxy using a character reader of given length.
	 *
	 * @param reader The character reader
	 * @param length The length of the character reader
	 *
	 * @return The generated proxy.
	 */
	public static Clob generateProxy(Reader reader, long length) {
		return ( Clob ) Proxy.newProxyInstance(
				getProxyClassLoader(),
				PROXY_INTERFACES,
				new ClobProxy( reader, length )
		);
	}

	/**
	 * Determines the appropriate class loader to which the generated proxy
	 * should be scoped.
	 *
	 * @return The class loader appropriate for proxy construction.
	 */
	protected static ClassLoader getProxyClassLoader() {
		ClassLoader cl = Thread.currentThread().getContextClassLoader();
		if ( cl == null ) {
			cl = NClobImplementer.class.getClassLoader();
		}
		return cl;
	}
}

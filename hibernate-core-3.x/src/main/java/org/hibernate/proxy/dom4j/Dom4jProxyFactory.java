/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.proxy.dom4j;

import org.hibernate.HibernateException;
import org.hibernate.proxy.ProxyFactory;
import org.hibernate.proxy.HibernateProxy;
import org.hibernate.type.AbstractComponentType;
import org.hibernate.engine.SessionImplementor;

import java.util.Set;
import java.lang.reflect.Method;
import java.io.Serializable;

/**
 * Builds proxies for "dom4j" entity representations.
 *
 * @author Steve Ebersole
 */
public class Dom4jProxyFactory implements ProxyFactory {

	private String entityName;

	/**
	 * Called immediately after instantiation
	 */
	public void postInstantiate(
	        String entityName,
	        Class persistentClass,
	        Set interfaces,
	        Method getIdentifierMethod,
	        Method setIdentifierMethod,
	        AbstractComponentType componentIdType) throws HibernateException {
		this.entityName = entityName;
	}

	/**
	 * Create a new proxy
	 */
	public HibernateProxy getProxy(Serializable id, SessionImplementor session) throws HibernateException {
		return new Dom4jProxy( new Dom4jLazyInitializer( entityName, id, session ) );
	}
}

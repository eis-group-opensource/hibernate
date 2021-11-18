/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.proxy.pojo.cglib;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.proxy.ProxyFactory;
import org.hibernate.proxy.HibernateProxy;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.type.AbstractComponentType;

/**
 * @author Gavin King
 *
 * @deprecated Per HHH-5451 support for cglib as a bytecode provider has been deprecated.
 */
public class CGLIBProxyFactory implements ProxyFactory {

	protected static final Class[] NO_CLASSES = new Class[0];

	private Class persistentClass;
	private String entityName;
	private Class[] interfaces;
	private Method getIdentifierMethod;
	private Method setIdentifierMethod;
	private AbstractComponentType componentIdType;
	private Class factory;

	public void postInstantiate(
		final String entityName,
		final Class persistentClass,
		final Set interfaces,
		final Method getIdentifierMethod,
		final Method setIdentifierMethod,
		AbstractComponentType componentIdType)
	throws HibernateException {
		this.entityName = entityName;
		this.persistentClass = persistentClass;
		this.interfaces = (Class[]) interfaces.toArray(NO_CLASSES);
		this.getIdentifierMethod = getIdentifierMethod;
		this.setIdentifierMethod = setIdentifierMethod;
		this.componentIdType = componentIdType;
		factory = CGLIBLazyInitializer.getProxyFactory(persistentClass, this.interfaces);
	}

	public HibernateProxy getProxy(Serializable id, SessionImplementor session)
		throws HibernateException {

		return CGLIBLazyInitializer.getProxy(
				factory, 
				entityName, 
				persistentClass, 
				interfaces, 
				getIdentifierMethod, 
				setIdentifierMethod,
				componentIdType,
				id, 
				session
			);
	}

}

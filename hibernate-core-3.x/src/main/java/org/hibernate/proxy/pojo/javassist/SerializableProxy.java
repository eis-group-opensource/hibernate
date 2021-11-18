/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.proxy.pojo.javassist;

import java.io.Serializable;
import java.lang.reflect.Method;

import org.hibernate.HibernateException;
import org.hibernate.proxy.AbstractSerializableProxy;
import org.hibernate.proxy.HibernateProxy;
import org.hibernate.type.AbstractComponentType;

/**
 * Serializable placeholder for Javassist proxies
 */
public final class SerializableProxy extends AbstractSerializableProxy {

	private Class persistentClass;
	private Class[] interfaces;
	private Class getIdentifierMethodClass;
	private Class setIdentifierMethodClass;
	private String getIdentifierMethodName;
	private String setIdentifierMethodName;
	private Class[] setIdentifierMethodParams;
	private AbstractComponentType componentIdType;

	public SerializableProxy() {}

	public SerializableProxy(
		final String entityName,
	    final Class persistentClass,
	    final Class[] interfaces,
	    final Serializable id,
	    final Boolean readOnly,
	    final Method getIdentifierMethod,
	    final Method setIdentifierMethod,
	    AbstractComponentType componentIdType
	) {
		super( entityName, id, readOnly );
		this.persistentClass = persistentClass;
		this.interfaces = interfaces;
		if (getIdentifierMethod!=null) {
			getIdentifierMethodClass = getIdentifierMethod.getDeclaringClass();
			getIdentifierMethodName = getIdentifierMethod.getName();
		}
		if (setIdentifierMethod!=null) {
			setIdentifierMethodClass = setIdentifierMethod.getDeclaringClass();
			setIdentifierMethodName = setIdentifierMethod.getName();
			setIdentifierMethodParams = setIdentifierMethod.getParameterTypes();
		}
		this.componentIdType = componentIdType;
	}

	private Object readResolve() {
		try {
			HibernateProxy proxy = JavassistLazyInitializer.getProxy(
				getEntityName(),
				persistentClass,
				interfaces,
				getIdentifierMethodName==null ?
					null :
					getIdentifierMethodClass.getDeclaredMethod(getIdentifierMethodName, null),
				setIdentifierMethodName==null ?
					null :
					setIdentifierMethodClass.getDeclaredMethod(setIdentifierMethodName, setIdentifierMethodParams),
					componentIdType,
				getId(),
				null
			);
			setReadOnlyBeforeAttachedToSession( ( JavassistLazyInitializer ) proxy.getHibernateLazyInitializer() );
			return proxy;
		}
		catch (NoSuchMethodException nsme) {
			throw new HibernateException("could not create proxy for entity: " + getEntityName(), nsme);
		}
	}
}

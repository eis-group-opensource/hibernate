/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate;

import org.hibernate.util.StringHelper;

/**
 * A problem occurred accessing a property of an instance of a
 * persistent class by reflection, or via CGLIB. There are a
 * number of possible underlying causes, including
 * <ul>
 * <li>failure of a security check
 * <li>an exception occurring inside the getter or setter method
 * <li>a nullable database column was mapped to a primitive-type property
 * <li>the Hibernate type was not castable to the property type (or vice-versa)
 * </ul>
 * @author Gavin King
 */
public class PropertyAccessException extends HibernateException {

	private final Class persistentClass;
	private final String propertyName;
	private final boolean wasSetter;

	public PropertyAccessException(Throwable root, String s, boolean wasSetter, Class persistentClass, String propertyName) {
		super(s, root);
		this.persistentClass = persistentClass;
		this.wasSetter = wasSetter;
		this.propertyName = propertyName;
	}

	public Class getPersistentClass() {
		return persistentClass;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public String getMessage() {
		return super.getMessage() +
		( wasSetter ? " setter of " : " getter of ") +
		StringHelper.qualify( persistentClass.getName(), propertyName );
	}
}







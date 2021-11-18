/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate;

import org.hibernate.util.StringHelper;

/**
 * Thrown when the (illegal) value of a property can not be persisted.
 * There are two main causes:
 * <ul>
 * <li>a property declared <tt>not-null="true"</tt> is null
 * <li>an association references an unsaved transient instance
 * </ul>
 * @author Gavin King
 */
public class PropertyValueException extends HibernateException {

	private final String entityName;
	private final String propertyName;

	public PropertyValueException(String s, String entityName, String propertyName) {
		super(s);
		this.entityName = entityName;
		this.propertyName = propertyName;
	}

	public String getEntityName() {
		return entityName;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public String getMessage() {
		return super.getMessage() + ": " +
			StringHelper.qualify(entityName, propertyName);
	}

	/**
	 * Return a well formed property path.
	 * Basicaly, it will return parent.child
	 *
	 * @param parent parent in path
	 * @param child child in path
	 * @return parent-child path
	 */
	public static String buildPropertyPath(String parent, String child) {
		return new StringBuffer(parent).append('.').append(child).toString();
	}
}







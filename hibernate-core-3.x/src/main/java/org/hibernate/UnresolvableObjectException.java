/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate;

import java.io.Serializable;

import org.hibernate.pretty.MessageHelper;

/**
 * Thrown when Hibernate could not resolve an object by id, especially when
 * loading an association.
 *
 * @author Gavin King
 */
public class UnresolvableObjectException extends HibernateException {

	private final Serializable identifier;
	private final String entityName;

	public UnresolvableObjectException(Serializable identifier, String clazz) {
		this("No row with the given identifier exists", identifier, clazz);
	}
	UnresolvableObjectException(String message, Serializable identifier, String clazz) {
		super(message);
		this.identifier = identifier;
		this.entityName = clazz;
	}
	public Serializable getIdentifier() {
		return identifier;
	}

	public String getMessage() {
		return super.getMessage() + ": " +
			MessageHelper.infoString(entityName, identifier);
	}

	public String getEntityName() {
		return entityName;
	}

	public static void throwIfNull(Object o, Serializable id, String clazz)
	throws UnresolvableObjectException {
		if (o==null) throw new UnresolvableObjectException(id, clazz);
	}

}








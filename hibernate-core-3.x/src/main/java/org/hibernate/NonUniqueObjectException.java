/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate;

import java.io.Serializable;

import org.hibernate.pretty.MessageHelper;

/**
 * This exception is thrown when an operation would
 * break session-scoped identity. This occurs if the
 * user tries to associate two different instances of
 * the same Java class with a particular identifier,
 * in the scope of a single <tt>Session</tt>.
 *
 * @author Gavin King
 */
public class NonUniqueObjectException extends HibernateException {
	private final Serializable identifier;
	private final String entityName;

	public NonUniqueObjectException(String message, Serializable id, String clazz) {
		super(message);
		this.entityName = clazz;
		this.identifier = id;
	}

	public NonUniqueObjectException(Serializable id, String clazz) {
		this("a different object with the same identifier value was already associated with the session", id, clazz);
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

}

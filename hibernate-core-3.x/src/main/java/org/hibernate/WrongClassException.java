/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate;

import java.io.Serializable;

/**
 * Thrown when <tt>Session.load()</tt> selects a row with
 * the given primary key (identifier value) but the row's
 * discriminator value specifies a subclass that is not
 * assignable to the class requested by the user.
 *
 * @author Gavin King
 */
public class WrongClassException extends HibernateException {

	private final Serializable identifier;
	private final String entityName;

	public WrongClassException(String msg, Serializable identifier, String clazz) {
		super(msg);
		this.identifier = identifier;
		this.entityName = clazz;
	}
	public Serializable getIdentifier() {
		return identifier;
	}

	public String getMessage() {
		return "Object with id: " +
			identifier +
			" was not of the specified subclass: " +
			entityName +
			" (" + super.getMessage() + ")" ;
	}

	public String getEntityName() {
		return entityName;
	}

}








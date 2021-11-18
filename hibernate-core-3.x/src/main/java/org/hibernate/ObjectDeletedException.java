/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate;

import java.io.Serializable;

/**
 * Thrown when the user tries to do something illegal with a deleted
 * object.
 *
 * @author Gavin King
 */
public class ObjectDeletedException extends UnresolvableObjectException {

	public ObjectDeletedException(String message, Serializable identifier, String clazz) {
		super(message, identifier, clazz);
	}

}








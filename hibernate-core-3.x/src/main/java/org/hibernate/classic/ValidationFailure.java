/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.classic;

import org.hibernate.HibernateException;

/**
 * Thrown from <tt>Validatable.validate()</tt> when an invariant
 * was violated. Some applications might subclass this exception
 * in order to provide more information about the violation.
 *
 * @author Gavin King
 */
public class ValidationFailure extends HibernateException {

	public ValidationFailure(String message) {
		super(message);
	}

	public ValidationFailure(String message, Exception e) {
		super(message, e);
	}

	public ValidationFailure(Exception e) {
		super("A validation failure occurred", e);
	}

}

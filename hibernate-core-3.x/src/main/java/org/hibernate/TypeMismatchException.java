/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate;

/**
 * Used when a user provided type does not match the expected one
 *
 * @author Emmanuel Bernard
 */
public class TypeMismatchException extends HibernateException {
	public TypeMismatchException(Throwable root) {
		super( root );
	}

	public TypeMismatchException(String s) {
		super( s );
	}

	public TypeMismatchException(String string, Throwable root) {
		super( string, root );
	}
}

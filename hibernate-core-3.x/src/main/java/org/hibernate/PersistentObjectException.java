/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate;

/**
 * Thrown when the user passes a persistent instance to a <tt>Session</tt>
 * method that expects a transient instance.
 *
 * @author Gavin King
 */
public class PersistentObjectException extends HibernateException {
	
	public PersistentObjectException(String s) {
		super(s);
	}
}

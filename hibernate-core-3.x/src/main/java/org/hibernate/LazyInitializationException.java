/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate;

import org.slf4j.LoggerFactory;

/**
 * Indicates access to unfetched data outside of a session context.
 * For example, when an uninitialized proxy or collection is accessed 
 * after the session was closed.
 *
 * @see Hibernate#initialize(java.lang.Object)
 * @see Hibernate#isInitialized(java.lang.Object)
 * @author Gavin King
 */
public class LazyInitializationException extends HibernateException {

	public LazyInitializationException(String msg) {
		super(msg);
		LoggerFactory.getLogger( LazyInitializationException.class ).error( msg, this );
	}

}







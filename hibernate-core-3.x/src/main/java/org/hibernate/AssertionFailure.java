/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Indicates failure of an assertion: a possible bug in Hibernate.
 *
 * @author Gavin King
 */
public class AssertionFailure extends RuntimeException {

	private static final Logger log = LoggerFactory.getLogger( AssertionFailure.class );

	private static final String MESSAGE = "an assertion failure occured" +
			" (this may indicate a bug in Hibernate, but is more likely due" +
			" to unsafe use of the session)";

	public AssertionFailure(String s) {
		super( s );
		log.error( MESSAGE, this );
	}

	public AssertionFailure(String s, Throwable t) {
		super( s, t );
		log.error( MESSAGE, t );
	}

}

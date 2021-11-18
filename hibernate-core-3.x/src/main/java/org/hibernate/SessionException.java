/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate;

/**
 * Thrown when the user calls a method of a {@link Session} that is in an
 * inappropropriate state for the given call (for example, the the session
 * is closed or disconnected).
 *
 * @author Gavin King
 */
public class SessionException extends HibernateException {

	/**
	 * Constructs a new SessionException with the given message.
	 *
	 * @param message The message indicating the specific problem.
	 */
	public SessionException(String message) {
		super( message );
	}

}

/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.classic;

/**
 * Implemented by persistent classes with invariants that must
 * be checked before inserting into or updating the database.
 *
 * @author Gavin King
 */
public interface Validatable {
	/**
	 * Validate the state of the object before persisting it.
	 * If a violation occurs, throw a <tt>ValidationFailure</tt>.
	 * This method must not change the state of the object by
	 * side-effect.
	 * @throws ValidationFailure if an invariant is violated
	 */
	public void validate() throws ValidationFailure;
}

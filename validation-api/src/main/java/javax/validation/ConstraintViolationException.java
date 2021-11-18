/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package javax.validation;

import java.util.Set;

/**
 * Reports the result of constraint violations
 *                                                    `
 * @author Emmanuel Bernard
 */
public class ConstraintViolationException extends ValidationException {
	private final Set<ConstraintViolation<?>> constraintViolations;

	/**
	 * Creates a constraint violation report
	 *
	 * @param message error message
	 * @param constraintViolations <code>Set</code> of <code>ConstraintViolation</code>
	 */
	public ConstraintViolationException(String message,
										Set<ConstraintViolation<?>> constraintViolations) {
		super( message );
		this.constraintViolations = constraintViolations;
	}

	/**
	 * Creates a constraint violation report
	 *
	 * @param constraintViolations <code>Set</code> of <code>ConstraintViolation</code>
	 */
	public ConstraintViolationException(Set<ConstraintViolation<?>> constraintViolations) {
		super();
		this.constraintViolations = constraintViolations;
	}

	/**
	 * Set of constraint violations reported during a validation
	 *
	 * @return <code>Set</code> of <code>ConstraintViolation</code>
	 */
	public Set<ConstraintViolation<?>> getConstraintViolations() {
		return constraintViolations;
	}
}

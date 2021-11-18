/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package javax.validation;

/**
 * Exception raised in the case that the constraint validator resolution
 * cannot determine a suitable validator for a given type.
 *
 * @author Hardy Ferentschik
 */
public class UnexpectedTypeException extends ConstraintDeclarationException {
	public UnexpectedTypeException(String message) {
		super( message );
	}

	public UnexpectedTypeException() {
		super();
	}

	public UnexpectedTypeException(String message, Throwable cause) {
		super( message, cause );
	}

	public UnexpectedTypeException(Throwable cause) {
		super( cause );
	}
}

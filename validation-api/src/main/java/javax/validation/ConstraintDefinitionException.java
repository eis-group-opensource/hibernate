/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package javax.validation;

/**
 * Exception raised if a constraint definition is not legal
 *
 * @author Emmanuel Bernard
 */
public class ConstraintDefinitionException extends ValidationException {
	public ConstraintDefinitionException(String message) {
		super( message );
	}

	public ConstraintDefinitionException() {
		super();
	}

	public ConstraintDefinitionException(String message, Throwable cause) {
		super( message, cause );
	}

	public ConstraintDefinitionException(Throwable cause) {
		super( cause );
	}
}

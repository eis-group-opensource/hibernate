/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.bytecode.buildtime;

/**
 * Indicates problem performing the instrumentation execution.
 *
 * @author Steve Ebersole
 */
public class ExecutionException extends RuntimeException {
	public ExecutionException(String message) {
		super( message );
	}

	public ExecutionException(Throwable cause) {
		super( cause );
	}

	public ExecutionException(String message, Throwable cause) {
		super( message, cause );
	}
}

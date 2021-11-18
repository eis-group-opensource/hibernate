/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.hql.ast;

import antlr.SemanticException;

/**
 * Exception thrown when an invalid path is found in a query.
 *
 * @author josh
 */
public class InvalidPathException extends SemanticException {
	public InvalidPathException(String s) {
		super( s );
	}
}

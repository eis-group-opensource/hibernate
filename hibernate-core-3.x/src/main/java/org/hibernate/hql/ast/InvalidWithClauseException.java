/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.hql.ast;

/**
 * {@inheritDoc}
 *
 * @author Steve Ebersole
 */
public class InvalidWithClauseException extends QuerySyntaxException {
	public InvalidWithClauseException(String message) {
		super( message );
	}

	public InvalidWithClauseException(String message, String queryString) {
		super( message, queryString );
	}
}

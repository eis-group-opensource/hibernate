/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.hql.ast;

import antlr.RecognitionException;
import org.hibernate.QueryException;

/**
 * Exception thrown when there is a syntax error in the HQL.
 *
 * @author josh
 */
public class QuerySyntaxException extends QueryException {

	public QuerySyntaxException(String message) {
		super( message );
	}

	public QuerySyntaxException(String message, String hql) {
		this( message );
		setQueryString( hql );
	}

	public static QuerySyntaxException convert(RecognitionException e) {
		return convert( e, null );
	}

	public static QuerySyntaxException convert(RecognitionException e, String hql) {
		String positionInfo = e.getLine() > 0 && e.getColumn() > 0
				? " near line " + e.getLine() + ", column " + e.getColumn()
				: "";
		return new QuerySyntaxException( e.getMessage() + positionInfo, hql );
	}

}

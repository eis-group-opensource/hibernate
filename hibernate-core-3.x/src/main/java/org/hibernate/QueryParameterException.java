/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate;

/**
 * Parameter invalid or not found in the query
 * 
 * @author Emmanuel Bernard
 */
public class QueryParameterException extends QueryException {

	public QueryParameterException(Exception e) {
		super( e );
	}

	public QueryParameterException(String message) {
		super( message );
	}

	public QueryParameterException(String message, Throwable e) {
		super( message, e );
	}

	public QueryParameterException(String message, String queryString) {
		super( message, queryString );
	}
}

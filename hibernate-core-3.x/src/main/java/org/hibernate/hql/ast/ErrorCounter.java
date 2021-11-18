/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.hql.ast;

import antlr.RecognitionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.hibernate.QueryException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * An error handler that counts parsing errors and warnings.
 */
public class ErrorCounter implements ParseErrorHandler {
	private Logger log = LoggerFactory.getLogger( ErrorCounter.class );
	private Logger hqlLog = LoggerFactory.getLogger( "org.hibernate.hql.PARSER" );

	private List errorList = new ArrayList();
	private List warningList = new ArrayList();
	private List recognitionExceptions = new ArrayList();

	public void reportError(RecognitionException e) {
		reportError( e.toString() );
		recognitionExceptions.add( e );
		if ( log.isDebugEnabled() ) {
			log.debug( e.toString(), e );
		}
	}

	public void reportError(String message) {
		hqlLog.error( message );
		errorList.add( message );
	}

	public int getErrorCount() {
		return errorList.size();
	}

	public void reportWarning(String message) {
		hqlLog.debug( message );
		warningList.add( message );
	}

	private String getErrorString() {
		StringBuffer buf = new StringBuffer();
		for ( Iterator iterator = errorList.iterator(); iterator.hasNext(); ) {
			buf.append( ( String ) iterator.next() );
			if ( iterator.hasNext() ) buf.append( "\n" );

		}
		return buf.toString();
	}

	public void throwQueryException() throws QueryException {
		if ( getErrorCount() > 0 ) {
			if ( recognitionExceptions.size() > 0 ) {
				throw QuerySyntaxException.convert( ( RecognitionException ) recognitionExceptions.get( 0 ) );
			}
			else {
				throw new QueryException( getErrorString() );
			}
		}
		else {
			// all clear
			if ( log.isDebugEnabled() ) {
				log.debug( "throwQueryException() : no errors" );
			}
		}
	}
}

/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.util.xml;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXParseException;

/**
 * Implements an {@link ErrorHandler} that mainly just logs errors/warnings.  However, it does track
 * the intial error it encounters and makes it available via {@link #getError}.
 *
 * @author Steve Ebersole
 */
public class ErrorLogger implements ErrorHandler, Serializable {
	private static final Logger log = LoggerFactory.getLogger( ErrorLogger.class );

	private SAXParseException error; // capture the initial error

	/**
	 * Retrieve the initial error encountered, or null if no error was encountered.
	 *
	 * @return The initial error, or null if none.
	 */
	public SAXParseException getError() {
		return error;
	}

	/**
	 * {@inheritDoc}
	 */
	public void error(SAXParseException error) {
		log.error( "Error parsing XML (" + error.getLineNumber() + ") : " + error.getMessage() );
		if ( this.error == null ) {
			this.error = error;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void fatalError(SAXParseException error) {
		error( error );
	}

	/**
	 * {@inheritDoc}
	 */
	public void warning(SAXParseException warn) {
		log.error( "Warning parsing XML (" + error.getLineNumber() + ") : " + error.getMessage() );
	}

	public void reset() {
		error = null;
	}
}

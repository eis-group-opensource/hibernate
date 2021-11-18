/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.hql.ast;

import org.hibernate.QueryException;


/**
 * Defines the behavior of an error handler for the HQL parsers.
 * User: josh
 * Date: Dec 6, 2003
 * Time: 12:20:43 PM
 */
public interface ParseErrorHandler extends ErrorReporter {

	int getErrorCount();

	// --Commented out by Inspection (12/11/04 10:56 AM): int getWarningCount();

	void throwQueryException() throws QueryException;
}

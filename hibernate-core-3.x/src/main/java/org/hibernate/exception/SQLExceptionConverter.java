/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.exception;

import org.hibernate.JDBCException;

import java.sql.SQLException;

/**
 * Defines a contract for implementations that know how to convert a SQLException
 * into Hibernate's JDBCException hierarchy.  Inspired by Spring's
 * SQLExceptionTranslator.
 * <p/>
 * Implementations <b>must</b> have a constructor which takes a
 * {@link ViolatedConstraintNameExtracter} parameter.
 * <p/>
 * Implementations may implement {@link Configurable} if they need to perform
 * configuration steps prior to first use.
 *
 * @author Steve Ebersole
 * @see SQLExceptionConverterFactory
 */
public interface SQLExceptionConverter {
	/**
	 * Convert the given SQLException into Hibernate's JDBCException hierarchy.
	 *
	 * @param sqlException The SQLException to be converted.
	 * @param message      An optional error message.
	 * @return The resulting JDBCException.
	 * @see ConstraintViolationException, JDBCConnectionException, SQLGrammarException, LockAcquisitionException
	 */
	public JDBCException convert(SQLException sqlException, String message, String sql);
}

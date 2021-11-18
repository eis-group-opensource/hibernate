/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.jdbc;

import org.hibernate.HibernateException;

import java.sql.SQLException;
import java.sql.PreparedStatement;

/**
 * Defines an expected DML operation outcome.
 *
 * @author Steve Ebersole
 */
public interface Expectation {
	/**
	 * Perform verification of the outcome of the RDBMS operation based on
	 * the type of expectation defined.
	 *
	 * @param rowCount The RDBMS reported "number of rows affected".
	 * @param statement The statement representing the operation
	 * @param batchPosition The position in the batch (if batching)
	 * @throws SQLException Exception from the JDBC driver
	 * @throws HibernateException Problem processing the outcome.
	 */
	public void verifyOutcome(int rowCount, PreparedStatement statement, int batchPosition) throws SQLException, HibernateException;

	/**
	 * Perform any special statement preparation.
	 *
	 * @param statement The statement to be prepared
	 * @return The number of bind positions consumed (if any)
	 * @throws SQLException Exception from the JDBC driver
	 * @throws HibernateException Problem performing preparation.
	 */
	public int prepare(PreparedStatement statement) throws SQLException, HibernateException;

	/**
	 * Is it acceptable to combiner this expectation with statement batching?
	 *
	 * @return True if batching can be combined with this expectation; false otherwise.
	 */
	public boolean canBeBatched();
}

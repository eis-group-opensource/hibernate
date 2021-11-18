/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.jdbc;

import org.hibernate.HibernateException;

/**
 * Indicates that more rows were affected then we were expecting to be.
 * Typically indicates presence of duplicate "PK" values in the
 * given table.
 *
 * @author Steve Ebersole
 */
public class TooManyRowsAffectedException extends HibernateException {
	private final int expectedRowCount;
	private final int actualRowCount;

	public TooManyRowsAffectedException(String message, int expectedRowCount, int actualRowCount) {
		super( message );
		this.expectedRowCount = expectedRowCount;
		this.actualRowCount = actualRowCount;
	}

	public int getExpectedRowCount() {
		return expectedRowCount;
	}

	public int getActualRowCount() {
		return actualRowCount;
	}
}

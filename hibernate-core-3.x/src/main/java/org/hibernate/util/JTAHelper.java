/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.util;

import javax.transaction.Status;
import javax.transaction.SystemException;
import javax.transaction.TransactionManager;

import org.hibernate.TransactionException;
import org.hibernate.engine.SessionFactoryImplementor;

/**
 * @author Gavin King
 */
public final class JTAHelper {

	private JTAHelper() {}

	public static boolean isRollback(int status) {
		return status==Status.STATUS_MARKED_ROLLBACK ||
		       status==Status.STATUS_ROLLING_BACK ||
		       status==Status.STATUS_ROLLEDBACK;
	}

	public static boolean isInProgress(int status) {
		return status==Status.STATUS_ACTIVE ||
		       status==Status.STATUS_MARKED_ROLLBACK;
	}

	/**
	 * Return true if a JTA transaction is in progress
	 * and false in *every* other cases (including in a JDBC transaction).
	 */
	public static boolean isTransactionInProgress(SessionFactoryImplementor factory) {
		TransactionManager tm = factory.getTransactionManager();
		try {
			return tm != null && isTransactionInProgress( tm.getTransaction() );
		}
		catch (SystemException se) {
			throw new TransactionException( "could not obtain JTA Transaction", se );
		}
	}

	public static boolean isTransactionInProgress(javax.transaction.Transaction tx) throws SystemException {
		return tx != null && JTAHelper.isInProgress( tx.getStatus() );
	}

	public static boolean isMarkedForRollback(int status) {
		return status == Status.STATUS_MARKED_ROLLBACK;
	}

	public static boolean isMarkedForRollback(javax.transaction.Transaction tx) throws SystemException {
		return isMarkedForRollback( tx.getStatus() );
	}
}

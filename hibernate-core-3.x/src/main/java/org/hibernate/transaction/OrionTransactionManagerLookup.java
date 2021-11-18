/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.transaction;

/**
 * {@link TransactionManagerLookup} strategy for Orion
 *
 * @author Gavin King
 */
public class OrionTransactionManagerLookup extends JNDITransactionManagerLookup {

	/**
	 * {@inheritDoc}
	 */
	protected String getName() {
		return "java:comp/UserTransaction";
	}

	/**
	 * {@inheritDoc}
	 */
	public String getUserTransactionName() {
		return "java:comp/UserTransaction";
	}

}







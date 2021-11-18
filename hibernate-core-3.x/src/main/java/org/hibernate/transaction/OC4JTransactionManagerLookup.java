/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.transaction;

/**
 * {@link TransactionManagerLookup} for the OC4J (Oracle) AS.
 * 
 * @author Stijn Janssens
 */
public class OC4JTransactionManagerLookup extends JNDITransactionManagerLookup {
	protected String getName() {
		return "java:comp/pm/TransactionManager";
	}

	public String getUserTransactionName() {
		return "java:comp/UserTransaction";
	}
}

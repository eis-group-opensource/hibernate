/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.action;

import org.hibernate.engine.SessionImplementor;

/**
 * Contract representing some process that needs to occur during after transaction completion.
 *
 * @author Steve Ebersole
 */
public interface AfterTransactionCompletionProcess {
	/**
	 * Perform whatever processing is encapsulated here after completion of the transaction.
	 *
	 * @param success Did the transaction complete successfully?  True means it did.
	 * @param session The session on which the transaction is completing.
	 */
	public void doAfterTransactionCompletion(boolean success, SessionImplementor session);
}

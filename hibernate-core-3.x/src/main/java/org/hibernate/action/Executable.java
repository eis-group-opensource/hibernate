/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.action;

import org.hibernate.HibernateException;

import java.io.Serializable;

/**
 * An operation which may be scheduled for later execution.
 * Usually, the operation is a database insert/update/delete,
 * together with required second-level cache management.
 * 
 * @author Gavin King
 * @author Steve Ebersole
 */
public interface Executable {
	/**
	 * What spaces (tables) are affected by this action?
	 *
	 * @return The spaces affected by this action.
	 */
	public Serializable[] getPropertySpaces();

	/**
	 * Called before executing any actions.  Gives actions a chance to perform any preparation.
	 *
	 * @throws HibernateException Indicates a problem during preparation.
	 */
	public void beforeExecutions() throws HibernateException;

	/**
	 * Execute this action
	 *
	 * @throws HibernateException Indicates a problem during execution.
	 */
	public void execute() throws HibernateException;

	/**
	 * Get the after-transaction-completion process, if any, for this action.
	 *
	 * @return The after-transaction-completion process, or null if we have no
	 * after-transaction-completion process
	 */
	public AfterTransactionCompletionProcess getAfterTransactionCompletionProcess();

	/**
	 * Get the before-transaction-completion process, if any, for this action.
	 *
	 * @return The before-transaction-completion process, or null if we have no
	 * before-transaction-completion process
	 */
	public BeforeTransactionCompletionProcess getBeforeTransactionCompletionProcess();
}

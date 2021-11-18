/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.hql.ast.tree;

import org.hibernate.hql.ast.HqlSqlWalker;

/**
 * Common interface modeling the different HQL statements (i.e., INSERT, UPDATE, DELETE, SELECT).
 *
 * @author Steve Ebersole
 */
public interface Statement {

	/**
	 * Retreive the "phase 2" walker which generated this statement tree.
	 *
	 * @return The HqlSqlWalker instance which generated this statement tree.
	 */
	public HqlSqlWalker getWalker();

	/**
	 * Return the main token type representing the type of this statement.
	 *
	 * @return The corresponding token type.
	 */
	public int getStatementType();

	/**
	 * Does this statement require the StatementExecutor?
	 * </p>
	 * Essentially, at the JDBC level, does this require an executeUpdate()?
	 *
	 * @return True if this statement should be handed off to the
	 * StatementExecutor to be executed; false otherwise.
	 */
	public boolean needsExecutor();
}

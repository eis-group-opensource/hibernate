/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.hql.ast.tree;

import org.hibernate.QueryException;
import org.hibernate.hql.antlr.HqlSqlTokenTypes;

/**
 * Defines a top-level AST node representing an HQL "insert select" statement.
 *
 * @author Steve Ebersole
 */
public class InsertStatement extends AbstractStatement {

	/**
	 * @see Statement#getStatementType()
	 */
	public int getStatementType() {
		return HqlSqlTokenTypes.INSERT;
	}

	/**
	 * @see Statement#needsExecutor()
	 */
	public boolean needsExecutor() {
		return true;
	}

	/**
	 * Performs detailed semantic validation on this insert statement tree.
	 *
	 * @throws QueryException Indicates validation failure.
	 */
	public void validate() throws QueryException {
		getIntoClause().validateTypes( getSelectClause() );
	}

	/**
	 * Retreive this insert statement's into-clause.
	 *
	 * @return The into-clause
	 */
	public IntoClause getIntoClause() {
		return ( IntoClause ) getFirstChild();
	}

	/**
	 * Retreive this insert statement's select-clause.
	 *
	 * @return The select-clause.
	 */
	public SelectClause getSelectClause() {
		return ( ( QueryNode ) getIntoClause().getNextSibling() ).getSelectClause();
	}

}

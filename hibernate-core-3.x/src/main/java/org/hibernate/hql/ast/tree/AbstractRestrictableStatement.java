/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.hql.ast.tree;

import org.hibernate.hql.antlr.HqlSqlTokenTypes;
import org.hibernate.hql.ast.util.ASTUtil;

import antlr.collections.AST;

import org.slf4j.Logger;

/**
 * Convenience implementation of {@link RestrictableStatement}
 * to centralize common functionality.
 *
 * @author Steve Ebersole
 */
public abstract class AbstractRestrictableStatement extends AbstractStatement implements RestrictableStatement {

	private FromClause fromClause;
	private AST whereClause;

	protected abstract int getWhereClauseParentTokenType();
	protected abstract Logger getLog();

	/**
	 * @see org.hibernate.hql.ast.tree.RestrictableStatement#getFromClause
	 */
	public final FromClause getFromClause() {
		if ( fromClause == null ) {
			fromClause = ( FromClause ) ASTUtil.findTypeInChildren( this, HqlSqlTokenTypes.FROM );
		}
		return fromClause;
	}

	/**
	 * @see RestrictableStatement#hasWhereClause
	 */
	public final boolean hasWhereClause() {
		AST whereClause = locateWhereClause();
		return whereClause != null && whereClause.getNumberOfChildren() > 0;
	}

	/**
	 * @see org.hibernate.hql.ast.tree.RestrictableStatement#getWhereClause
	 */
	public final AST getWhereClause() {
		if ( whereClause == null ) {
			whereClause = locateWhereClause();
			// If there is no WHERE node, make one.
			if ( whereClause == null ) {
				getLog().debug( "getWhereClause() : Creating a new WHERE clause..." );
				whereClause = ASTUtil.create( getWalker().getASTFactory(), HqlSqlTokenTypes.WHERE, "WHERE" );
				// inject the WHERE after the parent
				AST parent = ASTUtil.findTypeInChildren( this, getWhereClauseParentTokenType() );
				whereClause.setNextSibling( parent.getNextSibling() );
				parent.setNextSibling( whereClause );
			}
		}
		return whereClause;
	}

	protected AST locateWhereClause() {
		return ASTUtil.findTypeInChildren( this, HqlSqlTokenTypes.WHERE );
	}
}

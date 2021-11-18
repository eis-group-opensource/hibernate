/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.hql.ast.tree;

import org.hibernate.hql.antlr.HqlSqlTokenTypes;
import org.hibernate.hql.antlr.SqlTokenTypes;
import org.hibernate.hql.ast.util.ASTUtil;

import antlr.collections.AST;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Defines a top-level AST node representing an HQL update statement.
 *
 * @author Steve Ebersole
 */
public class UpdateStatement extends AbstractRestrictableStatement {

	private static final Logger log = LoggerFactory.getLogger( UpdateStatement.class );

	/**
	 * @see org.hibernate.hql.ast.tree.Statement#getStatementType()
	 */
	public int getStatementType() {
		return SqlTokenTypes.UPDATE;
	}

	/**
	 * @see org.hibernate.hql.ast.tree.Statement#needsExecutor()
	 */
	public boolean needsExecutor() {
		return true;
	}

	protected int getWhereClauseParentTokenType() {
		return SqlTokenTypes.SET;
	}

	protected Logger getLog() {
		return log;
	}

	public AST getSetClause() {
		return ASTUtil.findTypeInChildren( this, HqlSqlTokenTypes.SET );
	}
}

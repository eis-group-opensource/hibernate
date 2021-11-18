/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.hql.ast.tree;

import java.util.ArrayList;

import org.hibernate.hql.antlr.SqlTokenTypes;
import org.hibernate.hql.ast.util.ASTPrinter;

import antlr.collections.AST;

/**
 * Common behavior - a node that contains a list of select expressions.
 *
 * @author josh
 */
public abstract class SelectExpressionList extends HqlSqlWalkerNode {
	/**
	 * Returns an array of SelectExpressions gathered from the children of the given parent AST node.
	 *
	 * @return an array of SelectExpressions gathered from the children of the given parent AST node.
	 */
	public SelectExpression[] collectSelectExpressions() {
		// Get the first child to be considered.  Sub-classes may do this differently in order to skip nodes that
		// are not select expressions (e.g. DISTINCT).
		AST firstChild = getFirstSelectExpression();
		AST parent = this;
		ArrayList list = new ArrayList( parent.getNumberOfChildren() );
		for ( AST n = firstChild; n != null; n = n.getNextSibling() ) {
			if ( n instanceof SelectExpression ) {
				list.add( n );
			}
			else {
				throw new IllegalStateException( "Unexpected AST: " + n.getClass().getName() + " " + new ASTPrinter( SqlTokenTypes.class ).showAsString( n, "" ) );
			}
		}
		return ( SelectExpression[] ) list.toArray( new SelectExpression[list.size()] );
	}

	/**
	 * Returns the first select expression node that should be considered when building the array of select
	 * expressions.
	 *
	 * @return the first select expression node that should be considered when building the array of select
	 *         expressions
	 */
	protected abstract AST getFirstSelectExpression();

}

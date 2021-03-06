/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.hql.ast.tree;

import antlr.SemanticException;
import antlr.collections.AST;

/**
 * A select expression that was generated by a FROM element.
 *
 * @author josh
 */
public class SelectExpressionImpl extends FromReferenceNode implements SelectExpression {

	public void resolveIndex(AST parent) throws SemanticException {
		throw new UnsupportedOperationException();
	}

	public void setScalarColumnText(int i) throws SemanticException {
		String text = getFromElement().renderScalarIdentifierSelect( i );
		setText( text );
	}

	public void resolve(boolean generateJoin, boolean implicitJoin, String classAlias, AST parent) throws SemanticException {
		// Generated select expressions are already resolved, nothing to do.
		return;
	}
}

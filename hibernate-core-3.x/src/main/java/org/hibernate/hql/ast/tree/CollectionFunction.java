/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.hql.ast.tree;

import antlr.SemanticException;
import antlr.collections.AST;

/**
 * Represents 'elements()' or 'indices()'.
 *
 * @author josh
 */
public class CollectionFunction extends MethodNode implements DisplayableNode {
	public void resolve(boolean inSelect) throws SemanticException {
		initializeMethodNode( this, inSelect );
		if ( !isCollectionPropertyMethod() ) {
			throw new SemanticException( this.getText() + " is not a collection property name!" );
		}
		AST expr = getFirstChild();
		if ( expr == null ) {
			throw new SemanticException( this.getText() + " requires a path!" );
		}
		resolveCollectionProperty( expr );
	}

	protected void prepareSelectColumns(String[] selectColumns) {
		// we need to strip off the embedded parens so that sql-gen does not double these up
		String subselect = selectColumns[0].trim();
		if ( subselect.startsWith( "(") && subselect.endsWith( ")" ) ) {
			subselect = subselect.substring( 1, subselect.length() -1 );
		}
		selectColumns[0] = subselect;
	}
}

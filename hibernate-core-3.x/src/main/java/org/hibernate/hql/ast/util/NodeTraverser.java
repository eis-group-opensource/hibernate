/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.hql.ast.util;

import antlr.collections.AST;
import java.util.Stack;

/**
 * A visitor for traversing an AST tree.
 * 
 * @author Steve Ebersole
 * @author Philip R. "Pib" Burns.
 * @author Strong Liu
 * 
 */

public class NodeTraverser {
	public static interface VisitationStrategy {
		public void visit( AST node );
	}

	private final VisitationStrategy strategy;

	public NodeTraverser( VisitationStrategy strategy ) {
		this.strategy = strategy;
	}

	/**
	 * Traverse the AST tree depth first.
	 * 
	 * @param ast
	 *            Root node of subtree to traverse.
	 * 
	 *            <p>
	 *            Note that the AST passed in is not visited itself. Visitation
	 *            starts with its children.
	 *            </p>
	 */
	public void traverseDepthFirst( AST ast ) {
		if ( ast == null ) {
			throw new IllegalArgumentException(
					"node to traverse cannot be null!" );
		}
		visitDepthFirst( ast.getFirstChild() );
	}
	
	private void visitDepthFirst(AST ast){
		if(ast==null){
			return;
		}
		Stack stack = new Stack();
		if ( ast != null ) {
			stack.push( ast );
			while (!stack.empty()) {
				ast = (AST) stack.pop();
				strategy.visit( ast );
				if ( ast.getNextSibling() != null ) 
					stack.push( ast.getNextSibling() );
				if ( ast.getFirstChild() != null ) 
					stack.push( ast.getFirstChild() );
			}
		}
	}

	
}

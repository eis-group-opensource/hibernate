/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.hql.ast;

import antlr.ASTFactory;
import org.hibernate.hql.ast.tree.Node;

/**
 * User: Joshua Davis<br>
 * Date: Sep 23, 2005<br>
 * Time: 12:30:01 PM<br>
 */
public class HqlASTFactory extends ASTFactory {

	/**
	 * Returns the class for a given token type (a.k.a. AST node type).
	 *
	 * @param tokenType The token type.
	 * @return Class - The AST node class to instantiate.
	 */
	public Class getASTNodeType(int tokenType) {
		return Node.class;
	}
}

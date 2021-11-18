/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.hql.ast.tree;

import org.hibernate.type.Type;
import antlr.SemanticException;

/**
 * Contract for nodes representing operators (logic or arithmetic).
 *
 * @author Steve Ebersole
 */
public interface OperatorNode {
	/**
	 * Called by the tree walker during hql-sql semantic analysis
	 * after the operator sub-tree is completely built.
	 */
	public abstract void initialize() throws SemanticException;

	/**
	 * Retrieves the data type for the overall operator expression.
	 *
	 * @return The expression's data type.
	 */
	public Type getDataType();
}

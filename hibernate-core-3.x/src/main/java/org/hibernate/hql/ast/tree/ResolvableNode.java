/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.hql.ast.tree;

import antlr.SemanticException;
import antlr.collections.AST;

/**
 * The contract for expression sub-trees that can resolve themselves.
 *
 * @author josh
 */
public interface ResolvableNode {
	/**
	 * Does the work of resolving an identifier or a dot
	 */
	void resolve(boolean generateJoin, boolean implicitJoin, String classAlias, AST parent) throws SemanticException;

	/**
	 * Does the work of resolving an identifier or a dot, but without a parent node
	 */
	void resolve(boolean generateJoin, boolean implicitJoin, String classAlias) throws SemanticException;

	/**
	 * Does the work of resolving an identifier or a dot, but without a parent node or alias
	 */
	void resolve(boolean generateJoin, boolean implicitJoin) throws SemanticException;

	/**
	 * Does the work of resolving inside of the scope of a function call
	 */
	void resolveInFunctionCall(boolean generateJoin, boolean implicitJoin) throws SemanticException;

	/**
	 * Does the work of resolving an an index [].
	 */
	void resolveIndex(AST parent) throws SemanticException;

}

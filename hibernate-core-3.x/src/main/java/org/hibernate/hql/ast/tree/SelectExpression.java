/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.hql.ast.tree;

import org.hibernate.type.Type;

import antlr.SemanticException;

/**
 * Represents an element of a projection list, i.e. a select expression.
 *
 * @author josh
 */
public interface SelectExpression {
	/**
	 * Returns the data type of the select expression.
	 *
	 * @return The data type of the select expression.
	 */
	Type getDataType();

	/**
	 * Appends AST nodes that represent the columns after the current AST node.
	 * (e.g. 'as col0_O_')
	 *
	 * @param i The index of the select expression in the projection list.
	 */
	void setScalarColumnText(int i) throws SemanticException;

	/**
	 * Returns the FROM element that this expression refers to.
	 *
	 * @return The FROM element.
	 */
	FromElement getFromElement();

	/**
	 * Returns true if the element is a constructor (e.g. new Foo).
	 *
	 * @return true if the element is a constructor (e.g. new Foo).
	 */
	boolean isConstructor();

	/**
	 * Returns true if this select expression represents an entity that can be returned.
	 *
	 * @return true if this select expression represents an entity that can be returned.
	 */
	boolean isReturnableEntity() throws SemanticException;

	/**
	 * Sets the text of the node.
	 *
	 * @param text the new node text.
	 */
	void setText(String text);

	boolean isScalar() throws SemanticException;
	
	void setAlias(String alias);
	String getAlias();
}

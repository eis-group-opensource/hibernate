/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.hql.ast.tree;

import org.hibernate.type.Type;

/**
 * A base AST node for the intermediate tree.
 * User: josh
 * Date: Dec 6, 2003
 * Time: 10:29:14 AM
 */
public class SqlNode extends Node {
	/**
	 * The original text for the node, mostly for debugging.
	 */
	private String originalText;
	/**
	 * The data type of this node.  Null for 'no type'.
	 */
	private Type dataType;

	public void setText(String s) {
		super.setText( s );
		if ( s != null && s.length() > 0 && originalText == null ) {
			originalText = s;
		}
	}

	public String getOriginalText() {
		return originalText;
	}

	public Type getDataType() {
		return dataType;
	}

	public void setDataType(Type dataType) {
		this.dataType = dataType;
	}

}

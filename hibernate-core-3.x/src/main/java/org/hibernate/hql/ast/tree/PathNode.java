/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.hql.ast.tree;

/**
 * An AST node with a path property.  This path property will be the fully qualified name.
 *
 * @author josh
 */
public interface PathNode {
	/**
	 * Returns the full path name represented by the node.
	 *
	 * @return the full path name represented by the node.
	 */
	String getPath();
}

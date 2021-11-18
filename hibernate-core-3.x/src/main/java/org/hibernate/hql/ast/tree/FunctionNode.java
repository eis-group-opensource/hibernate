/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.hql.ast.tree;

import org.hibernate.dialect.function.SQLFunction;

/**
 * Identifies a node which models a SQL function.
 *
 * @author Steve Ebersole
 */
public interface FunctionNode {
	public SQLFunction getSQLFunction();
}

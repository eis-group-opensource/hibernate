/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.hql.ast.tree;

import org.hibernate.hql.antlr.HqlSqlTokenTypes;

/**
 * Represents a 'is null' check.
 *
 * @author Steve Ebersole
 */
public class IsNullLogicOperatorNode extends AbstractNullnessCheckNode {
	protected int getExpansionConnectorType() {
		return HqlSqlTokenTypes.AND;
	}

	protected String getExpansionConnectorText() {
		return "AND";
	}
}

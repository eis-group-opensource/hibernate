/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.sql.ordering.antlr;

import antlr.ASTFactory;

/**
 * Acts as a {@link ASTFactory} for injecting our specific AST node classes into the Antlr generated trees.
 *
 * @author Steve Ebersole
 */
public class Factory extends ASTFactory implements OrderByTemplateTokenTypes {
	/**
	 * {@inheritDoc}
	 */
	public Class getASTNodeType(int i) {
		switch ( i ) {
			case ORDER_BY:
				return OrderByFragment.class;
			case SORT_SPEC:
				return SortSpecification.class;
			case ORDER_SPEC:
				return OrderingSpecification.class;
			case COLLATE:
				return CollationSpecification.class;
			case SORT_KEY:
				return SortKey.class;
			default:
				return NodeSupport.class;
		}
	}
}

/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.hql.ast.tree;

import org.hibernate.hql.antlr.HqlSqlTokenTypes;
import org.hibernate.hql.ast.util.ASTUtil;

import antlr.collections.AST;

/**
 * Implementation of OrderByClause.
 *
 * @author Steve Ebersole
 */
public class OrderByClause extends HqlSqlWalkerNode implements HqlSqlTokenTypes {

	public void addOrderFragment(String orderByFragment) {
		AST fragment = ASTUtil.create( getASTFactory(), SQL_TOKEN, orderByFragment );
		if ( getFirstChild() == null ) {
            setFirstChild( fragment );
		}
		else {
			addChild( fragment );
		}
	}

}

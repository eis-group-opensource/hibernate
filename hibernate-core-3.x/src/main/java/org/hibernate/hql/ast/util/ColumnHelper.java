/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.hql.ast.util;

import org.hibernate.hql.NameGenerator;
import org.hibernate.hql.antlr.SqlTokenTypes;
import org.hibernate.hql.ast.tree.HqlSqlWalkerNode;

import antlr.ASTFactory;
import antlr.collections.AST;

/**
 * Provides utility methods for dealing with arrays of SQL column names.
 *
 * @author josh
 */
public final class ColumnHelper {

	/**
	 * @deprecated (tell clover to filter this out)
	 */
	private ColumnHelper() {
	}

	public static void generateSingleScalarColumn(HqlSqlWalkerNode node, int i) {
		ASTFactory factory = node.getASTFactory();
		ASTUtil.createSibling( factory, SqlTokenTypes.SELECT_COLUMNS, " as " + NameGenerator.scalarName( i, 0 ), node );
	}

	/**
	 * Generates the scalar column AST nodes for a given array of SQL columns
	 */
	public static void generateScalarColumns(HqlSqlWalkerNode node, String sqlColumns[], int i) {
		if ( sqlColumns.length == 1 ) {
			generateSingleScalarColumn( node, i );
		}
		else {
			ASTFactory factory = node.getASTFactory();
			AST n = node;
			n.setText( sqlColumns[0] );	// Use the DOT node to emit the first column name.
			// Create the column names, folled by the column aliases.
			for ( int j = 0; j < sqlColumns.length; j++ ) {
				if ( j > 0 ) {
					n = ASTUtil.createSibling( factory, SqlTokenTypes.SQL_TOKEN, sqlColumns[j], n );
				}
				n = ASTUtil.createSibling( factory, SqlTokenTypes.SELECT_COLUMNS, " as " + NameGenerator.scalarName( i, j ), n );
			}
		}
	}
}

/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.hql.ast.tree;

import org.hibernate.Hibernate;
import org.hibernate.hql.antlr.HqlSqlTokenTypes;
import org.hibernate.hql.ast.util.ColumnHelper;
import org.hibernate.type.Type;

import antlr.SemanticException;

/**
 * Represents a literal.
 *
 * @author josh
 */
public class LiteralNode extends AbstractSelectExpression implements HqlSqlTokenTypes {

	public void setScalarColumnText(int i) throws SemanticException {
		ColumnHelper.generateSingleScalarColumn( this, i );
	}

	public Type getDataType() {
		switch ( getType() ) {
			case NUM_INT:
				return Hibernate.INTEGER;
			case NUM_FLOAT:
				return Hibernate.FLOAT;
			case NUM_LONG:
				return Hibernate.LONG;
			case NUM_DOUBLE:
				return Hibernate.DOUBLE;
			case NUM_BIG_INTEGER:
				return Hibernate.BIG_INTEGER;
			case NUM_BIG_DECIMAL:
				return Hibernate.BIG_DECIMAL;
			case QUOTED_STRING:
				return Hibernate.STRING;
			case TRUE:
			case FALSE:
				return Hibernate.BOOLEAN;
			default:
				return null;
		}
	}
}

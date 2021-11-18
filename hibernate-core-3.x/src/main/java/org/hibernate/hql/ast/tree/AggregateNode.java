/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.hql.ast.tree;

import org.hibernate.dialect.function.SQLFunction;
import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.hql.ast.util.ColumnHelper;
import org.hibernate.type.Type;

import antlr.SemanticException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Represents an aggregate function i.e. min, max, sum, avg.
 *
 * @author Joshua Davis
 */
public class AggregateNode extends AbstractSelectExpression implements SelectExpression, FunctionNode {
	private static final Logger log = LoggerFactory.getLogger( AggregateNode.class );

	private SQLFunction sqlFunction;

	public SQLFunction getSQLFunction() {
		return sqlFunction;
	}

	public void resolve() {
		resolveFunction();
	}

	private SQLFunction resolveFunction() {
		if ( sqlFunction == null ) {
			final String name = getText();
			sqlFunction = getSessionFactoryHelper().findSQLFunction( getText() );
			if ( sqlFunction == null ) {
				log.info( "Could not resolve aggregate function {}; using standard definition", name );
				sqlFunction = new StandardSQLFunction( name );
			}
		}
		return sqlFunction;
	}

	public Type getDataType() {
		// Get the function return value type, based on the type of the first argument.
		return getSessionFactoryHelper().findFunctionReturnType( getText(), resolveFunction(), getFirstChild() );
	}

	public void setScalarColumnText(int i) throws SemanticException {
		ColumnHelper.generateSingleScalarColumn( this, i );
	}

	public boolean isScalar() throws SemanticException {
		// functions in a SELECT should always be considered scalar.
		return true;
	}
}

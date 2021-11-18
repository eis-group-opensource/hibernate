/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.hql.ast.tree;

import org.hibernate.type.LiteralType;
import org.hibernate.type.Type;
import org.hibernate.Hibernate;
import org.hibernate.QueryException;
import org.hibernate.engine.SessionFactoryImplementor;

/**
 * Represents a boolean literal within a query.
 *
 * @author Steve Ebersole
 */
public class BooleanLiteralNode extends LiteralNode implements ExpectedTypeAwareNode {
	private Type expectedType;

	public Type getDataType() {
		return expectedType == null ? Hibernate.BOOLEAN : expectedType;
	}

	public Boolean getValue() {
		return getType() == TRUE ? Boolean.TRUE : Boolean.FALSE;
	}

	/**
	 * {@inheritDoc}
	 */
	public void setExpectedType(Type expectedType) {
		this.expectedType = expectedType;
	}

	/**
	 * {@inheritDoc}
	 */
	public Type getExpectedType() {
		return expectedType;
	}

	/**
	 * {@inheritDoc}
	 */
	public String getRenderText(SessionFactoryImplementor sessionFactory) {
		try {
			return typeAsLiteralType().objectToSQLString( getValue(), sessionFactory.getDialect() );
		}
		catch( Throwable t ) {
			throw new QueryException( "Unable to render boolean literal value", t );
		}
	}

	private LiteralType typeAsLiteralType() {
		return (LiteralType) getDataType();
	}
}

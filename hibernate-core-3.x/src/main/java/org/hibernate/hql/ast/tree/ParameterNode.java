/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.hql.ast.tree;

import org.hibernate.param.ParameterSpecification;
import org.hibernate.type.Type;
import org.hibernate.engine.SessionFactoryImplementor;

/**
 * Implementation of ParameterNode.
 *
 * @author Steve Ebersole
 */
public class ParameterNode extends HqlSqlWalkerNode implements DisplayableNode, ExpectedTypeAwareNode {
	private ParameterSpecification parameterSpecification;

	public ParameterSpecification getHqlParameterSpecification() {
		return parameterSpecification;
	}

	public void setHqlParameterSpecification(ParameterSpecification parameterSpecification) {
		this.parameterSpecification = parameterSpecification;
	}

	public String getDisplayText() {
		return "{" + ( parameterSpecification == null ? "???" : parameterSpecification.renderDisplayInfo() ) + "}";
	}

	public void setExpectedType(Type expectedType) {
		getHqlParameterSpecification().setExpectedType( expectedType );
		setDataType( expectedType );
	}

	public Type getExpectedType() {
		return getHqlParameterSpecification() == null ? null : getHqlParameterSpecification().getExpectedType();
	}

	public String getRenderText(SessionFactoryImplementor sessionFactory) {
		int count = 0;
		if ( getExpectedType() != null && ( count = getExpectedType().getColumnSpan( sessionFactory ) ) > 1 ) {
			StringBuffer buffer = new StringBuffer();
			buffer.append( "(?" );
			for ( int i = 1; i < count; i++ ) {
				buffer.append( ", ?" );
			}
			buffer.append( ")" );
			return buffer.toString();
		}
		else {
			return "?";
		}
	}
}

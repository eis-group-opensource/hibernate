/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.param;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;

import org.hibernate.engine.QueryParameters;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.type.Type;

/**
 * A specialized ParameterSpecification impl for dealing with a dynamic filter parameters.
 * 
 * @see org.hibernate.Session#enableFilter(String)
 *
 * @author Steve Ebersole
 */
public class DynamicFilterParameterSpecification implements ParameterSpecification {
	private final String filterName;
	private final String parameterName;
	private final Type definedParameterType;

	/**
	 * Constructs a parameter specification for a particular filter parameter.
	 *
	 * @param filterName The name of the filter
	 * @param parameterName The name of the parameter
	 * @param definedParameterType The paremeter type specified on the filter metadata
	 */
	public DynamicFilterParameterSpecification(
			String filterName,
			String parameterName,
			Type definedParameterType) {
		this.filterName = filterName;
		this.parameterName = parameterName;
		this.definedParameterType = definedParameterType;
	}

	/**
	 * {@inheritDoc}
	 */
	public int bind(
			PreparedStatement statement,
			QueryParameters qp,
			SessionImplementor session,
			int start) throws SQLException {
		final int columnSpan = definedParameterType.getColumnSpan( session.getFactory() );
		String fullParamName = filterName + '.' + parameterName;
		final Object value = session.getLoadQueryInfluencers().getFilterParameterValue(fullParamName);
		final Type type = session.getLoadQueryInfluencers().getFilterParameterType(fullParamName);
		if ( Collection.class.isInstance( value ) ) {
			int positions = 0;
			Iterator itr = ( ( Collection ) value ).iterator();
			while ( itr.hasNext() ) {
				Object next = itr.next();
				qp.bindDynamicParameter(type, next, start + positions);
				definedParameterType.nullSafeSet( statement, next, start + positions, session );
				positions += columnSpan;
			}
			return positions;
		}
		else {
			qp.bindDynamicParameter(type, value, start);
			definedParameterType.nullSafeSet( statement, value, start, session );
			return columnSpan;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public Type getExpectedType() {
		return definedParameterType;
	}

	/**
	 * {@inheritDoc}
	 */
	public void setExpectedType(Type expectedType) {
		// todo : throw exception?  maybe warn if not the same?
	}

	/**
	 * {@inheritDoc}
	 */
	public String renderDisplayInfo() {
		return "dynamic-filter={filterName=" + filterName + ",paramName=" + parameterName + "}";
	}
}

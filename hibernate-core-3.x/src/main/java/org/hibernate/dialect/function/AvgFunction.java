/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.dialect.function;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.MappingException;
import org.hibernate.QueryException;
import org.hibernate.engine.Mapping;
import org.hibernate.engine.SessionFactoryImplementor;
import org.hibernate.type.Type;

/**
 * The basic JPA spec compliant definition po<tt>AVG</tt> aggregation function.
 *
 * @author Steve Ebersole
 */
public class AvgFunction implements SQLFunction {
	public final Type getReturnType(Type columnType, Mapping mapping) throws QueryException {
		int[] sqlTypes;
		try {
			sqlTypes = columnType.sqlTypes( mapping );
		}
		catch ( MappingException me ) {
			throw new QueryException( me );
		}
		if ( sqlTypes.length != 1 ) {
			throw new QueryException( "multiple-column type in avg()" );
		}
		return Hibernate.DOUBLE;
	}

	public final boolean hasArguments() {
		return true;
	}

	public final boolean hasParenthesesIfNoArguments() {
		return true;
	}

	public String render(List args, SessionFactoryImplementor factory) throws QueryException {
		return "avg(" + args.get( 0 ) + ")";
	}

	public final String toString() {
		return "avg";
	}
}

/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.criterion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.QueryException;

/**
 * A count
 * @author Gavin King
 */
public class CountProjection extends AggregateProjection {
	private boolean distinct;

	protected CountProjection(String prop) {
		super("count", prop);
	}

	public String toString() {
		if ( distinct ) {
			return "distinct " + super.toString();
		}
		else {
			return super.toString();
		}
	}

	protected List buildFunctionParameterList(Criteria criteria, CriteriaQuery criteriaQuery) {
		String cols[] = criteriaQuery.getColumns( propertyName, criteria );
		return ( distinct ? buildCountDistinctParameterList( cols ) : Arrays.asList( cols ) );
	}

	private List buildCountDistinctParameterList(String[] cols) {
		List params = new ArrayList( cols.length + 1 );
		params.add( "distinct" );
		params.addAll( Arrays.asList( cols ) );
		return params;
	}

	public CountProjection setDistinct() {
		distinct = true;
		return this;
	}
}

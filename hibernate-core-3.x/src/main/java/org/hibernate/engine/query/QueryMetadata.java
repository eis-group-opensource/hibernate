/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.engine.query;

import org.hibernate.type.Type;

import java.io.Serializable;
import java.util.Set;

/**
 * Defines metadata regarding a translated HQL or native-SQL query.
 *
 * @author Steve Ebersole
 */
public class QueryMetadata implements Serializable {
	private final String sourceQuery;
	private final ParameterMetadata parameterMetadata;
	private final String[] returnAliases;
	private final Type[] returnTypes;
	private final Set querySpaces;

	public QueryMetadata(
			String sourceQuery,
	        ParameterMetadata parameterMetadata,
	        String[] returnAliases,
	        Type[] returnTypes,
	        Set querySpaces) {
		this.sourceQuery = sourceQuery;
		this.parameterMetadata = parameterMetadata;
		this.returnAliases = returnAliases;
		this.returnTypes = returnTypes;
		this.querySpaces = querySpaces;
	}

	/**
	 * Get the source HQL or native-SQL query.
	 *
	 * @return The source query.
	 */
	public String getSourceQuery() {
		return sourceQuery;
	}

	public ParameterMetadata getParameterMetadata() {
		return parameterMetadata;
	}

	/**
	 * Return source query select clause aliases (if any)
	 *
	 * @return an array of aliases as strings.
	 */
	public String[] getReturnAliases() {
		return returnAliases;
	}

	/**
	 * An array of types describing the returns of the source query.
	 *
	 * @return The return type array.
	 */
	public Type[] getReturnTypes() {
		return returnTypes;
	}

	/**
	 * The set of query spaces affected by this source query.
	 *
	 * @return The set of query spaces.
	 */
	public Set getQuerySpaces() {
		return querySpaces;
	}
}

/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.engine;

import java.util.Map;
import java.util.Set;

import org.hibernate.persister.entity.Loadable;
import org.hibernate.persister.entity.PropertyMapping;
import org.hibernate.util.StringHelper;

/**
 * @author Gavin King
 */
public class SubselectFetch {
	private final Set resultingEntityKeys;
	private final String queryString;
	private final String alias;
	private final Loadable loadable;
	private final QueryParameters queryParameters;
	private final Map namedParameterLocMap;
	
	public SubselectFetch(
		//final String queryString, 
		final String alias, 
		final Loadable loadable,
		final QueryParameters queryParameters, 
		final Set resultingEntityKeys,
		final Map namedParameterLocMap
	) {
		this.resultingEntityKeys = resultingEntityKeys;
		this.queryParameters = queryParameters;
		this.namedParameterLocMap = namedParameterLocMap;
		this.loadable = loadable;
		this.alias = alias;
		
		//TODO: ugly here:
		final String queryString = queryParameters.getFilteredSQL();
		int fromIndex = queryString.indexOf(" from ");
		int orderByIndex = queryString.lastIndexOf("order by");
		this.queryString = orderByIndex>0 ? 
				queryString.substring(fromIndex, orderByIndex) : 
				queryString.substring(fromIndex);
			
	}

	public QueryParameters getQueryParameters() {
		return queryParameters;
	}
	
	/**
	 * Get the Set of EntityKeys
	 */
	public Set getResult() {
		return resultingEntityKeys;
	}
	
	public String toSubselectString(String ukname) {
		
		String[] joinColumns = ukname==null ?
			StringHelper.qualify( alias, loadable.getIdentifierColumnNames() ) :
			( (PropertyMapping) loadable ).toColumns(alias, ukname);
		
		return new StringBuffer()
			.append("select ")
			.append( StringHelper.join(", ", joinColumns) )
			.append(queryString)
			.toString();
	}
	
	public String toString() {
		return "SubselectFetch(" + queryString + ')';
	}
	
	public Map getNamedParameterLocMap() {
		return namedParameterLocMap;
	}

}

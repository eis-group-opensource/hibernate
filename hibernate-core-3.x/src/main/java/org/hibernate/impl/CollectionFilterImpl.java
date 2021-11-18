/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.ScrollableResults;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.engine.query.ParameterMetadata;
import org.hibernate.type.Type;

/**
 * implementation of the <tt>Query</tt> interface for collection filters
 * @author Gavin King
 */
public class CollectionFilterImpl extends QueryImpl {

	private Object collection;

	public CollectionFilterImpl(
			String queryString,
	        Object collection,
	        SessionImplementor session,
	        ParameterMetadata parameterMetadata) {
		super( queryString, session, parameterMetadata );
		this.collection = collection;
	}


	/**
	 * @see org.hibernate.Query#iterate()
	 */
	public Iterator iterate() throws HibernateException {
		verifyParameters();
		Map namedParams = getNamedParams();
		return getSession().iterateFilter( 
				collection, 
				expandParameterLists(namedParams),
				getQueryParameters(namedParams) 
		);
	}

	/**
	 * @see org.hibernate.Query#list()
	 */
	public List list() throws HibernateException {
		verifyParameters();
		Map namedParams = getNamedParams();
		return getSession().listFilter( 
				collection, 
				expandParameterLists(namedParams),
				getQueryParameters(namedParams) 
		);
	}

	/**
	 * @see org.hibernate.Query#scroll()
	 */
	public ScrollableResults scroll() throws HibernateException {
		throw new UnsupportedOperationException("Can't scroll filters");
	}

	public Type[] typeArray() {
		List typeList = getTypes();
		int size = typeList.size();
		Type[] result = new Type[size+1];
		for (int i=0; i<size; i++) result[i+1] = (Type) typeList.get(i);
		return result;
	}

	public Object[] valueArray() {
		List valueList = getValues();
		int size = valueList.size();
		Object[] result = new Object[size+1];
		for (int i=0; i<size; i++) result[i+1] = valueList.get(i);
		return result;
	}

}

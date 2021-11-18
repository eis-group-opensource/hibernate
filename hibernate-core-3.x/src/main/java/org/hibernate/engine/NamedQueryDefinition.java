/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.engine;

import java.io.Serializable;
import java.util.Map;

import org.hibernate.CacheMode;
import org.hibernate.FlushMode;


/**
 * Definition of a named query, defined in the mapping metadata.
 *
 * @author Gavin King
 */
public class NamedQueryDefinition implements Serializable {
	private final String query;
	private final boolean cacheable;
	private final String cacheRegion;
	private final Integer timeout;
	private final Integer fetchSize;
	private final FlushMode flushMode;
	private final Map parameterTypes;
	private CacheMode cacheMode;
	private boolean readOnly;
	private String comment;

	// kept for backward compatibility until after the 3.1beta5 release of HA
	public NamedQueryDefinition(
			String query,
			boolean cacheable,
			String cacheRegion,
			Integer timeout,
			Integer fetchSize,
			FlushMode flushMode,
			Map parameterTypes
	) {
		this(
				query,
				cacheable,
				cacheRegion,
				timeout,
				fetchSize,
				flushMode,
				null,
				false,
				null,
				parameterTypes
		);
	}

	public NamedQueryDefinition(
			String query,
			boolean cacheable,
			String cacheRegion,
			Integer timeout,
			Integer fetchSize,
			FlushMode flushMode,
			CacheMode cacheMode,
			boolean readOnly,
			String comment,
			Map parameterTypes
	) {
		this.query = query;
		this.cacheable = cacheable;
		this.cacheRegion = cacheRegion;
		this.timeout = timeout;
		this.fetchSize = fetchSize;
		this.flushMode = flushMode;
		this.parameterTypes = parameterTypes;
		this.cacheMode = cacheMode;
		this.readOnly = readOnly;
		this.comment = comment;
	}

	public String getQueryString() {
		return query;
	}

	public boolean isCacheable() {
		return cacheable;
	}

	public String getCacheRegion() {
		return cacheRegion;
	}

	public Integer getFetchSize() {
		return fetchSize;
	}

	public Integer getTimeout() {
		return timeout;
	}

	public FlushMode getFlushMode() {
		return flushMode;
	}

	public String toString() {
		return getClass().getName() + '(' + query + ')';
	}

	public Map getParameterTypes() {
		return parameterTypes;
	}

	public String getQuery() {
		return query;
	}

	public CacheMode getCacheMode() {
		return cacheMode;
	}

	public boolean isReadOnly() {
		return readOnly;
	}

	public String getComment() {
		return comment;
	}
}

/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents an association fetching strategy. This is used
 * together with the <tt>Criteria</tt> API to specify runtime
 * fetching strategies.<br>
 * <br>
 * For HQL queries, use the <tt>FETCH</tt> keyword instead.
 *
 * @see Criteria#setFetchMode(java.lang.String, FetchMode)
 * @author Gavin King
 */
public final class FetchMode implements Serializable {
	private final String name;
	private static final Map INSTANCES = new HashMap();

	private FetchMode(String name) {
		this.name=name;
	}
	public String toString() {
		return name;
	}
	/**
	 * Default to the setting configured in the mapping file.
	 */
	public static final FetchMode DEFAULT = new FetchMode("DEFAULT");

	/**
	 * Fetch using an outer join. Equivalent to <tt>fetch="join"</tt>.
	 */
	public static final FetchMode JOIN = new FetchMode("JOIN");
	/**
	 * Fetch eagerly, using a separate select. Equivalent to
	 * <tt>fetch="select"</tt>.
	 */
	public static final FetchMode SELECT = new FetchMode("SELECT");

	/**
	 * Fetch lazily. Equivalent to <tt>outer-join="false"</tt>.
	 * @deprecated use <tt>FetchMode.SELECT</tt>
	 */
	public static final FetchMode LAZY = SELECT;
	/**
	 * Fetch eagerly, using an outer join. Equivalent to
	 * <tt>outer-join="true"</tt>.
	 * @deprecated use <tt>FetchMode.JOIN</tt>
	 */
	public static final FetchMode EAGER = JOIN;
	
	static {
		INSTANCES.put( JOIN.name, JOIN );
		INSTANCES.put( SELECT.name, SELECT );
		INSTANCES.put( DEFAULT.name, DEFAULT );
	}

	private Object readResolve() {
		return INSTANCES.get(name);
	}

}






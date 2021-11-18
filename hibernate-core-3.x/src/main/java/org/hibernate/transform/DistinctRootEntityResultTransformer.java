/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.transform;

import java.util.List;
import java.io.Serializable;

/**
 * Much like {@link RootEntityResultTransformer}, but we also distinct
 * the entity in the final result.
 * <p/>
 * Since this transformer is stateless, all instances would be considered equal.
 * So for optimization purposes we limit it to a single, singleton {@link #INSTANCE instance}.
 *
 * @author Gavin King
 * @author Steve Ebersole
 */
public class DistinctRootEntityResultTransformer implements ResultTransformer, Serializable {

	public static final DistinctRootEntityResultTransformer INSTANCE = new DistinctRootEntityResultTransformer();

	/**
	 * Disallow instantiation of DistinctRootEntityResultTransformer.
	 */
	private DistinctRootEntityResultTransformer() {
	}

	/**
	 * Simply delegates to {@link RootEntityResultTransformer#transformTuple}.
	 *
	 * @param tuple The tuple to transform
	 * @param aliases The tuple aliases
	 * @return The transformed tuple row.
	 */
	public Object transformTuple(Object[] tuple, String[] aliases) {
		return RootEntityResultTransformer.INSTANCE.transformTuple( tuple, aliases );
	}

	/**
	 * Simply delegates to {@link DistinctResultTransformer#transformList}.
	 *
	 * @param list The list to transform.
	 * @return The transformed List.
	 */
	public List transformList(List list) {
		return DistinctResultTransformer.INSTANCE.transformList( list );
	}

	/**
	 * Serialization hook for ensuring singleton uniqueing.
	 *
	 * @return The singleton instance : {@link #INSTANCE}
	 */
	private Object readResolve() {
		return INSTANCE;
	}

}

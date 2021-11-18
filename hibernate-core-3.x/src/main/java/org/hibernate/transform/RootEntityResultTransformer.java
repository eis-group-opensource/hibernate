/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.transform;

import java.io.Serializable;

/**
 * {@link ResultTransformer} implementation which limits the result tuple
 * to only the "root entity".
 * <p/>
 * Since this transformer is stateless, all instances would be considered equal.
 * So for optimization purposes we limit it to a single, singleton {@link #INSTANCE instance}.
 *
 * @author Gavin King
 * @author Steve Ebersole
 */
public final class RootEntityResultTransformer extends BasicTransformerAdapter implements Serializable {

	public static final RootEntityResultTransformer INSTANCE = new RootEntityResultTransformer();

	/**
	 * Disallow instantiation of RootEntityResultTransformer.
	 */
	private RootEntityResultTransformer() {
	}

	/**
	 * Return just the root entity from the row tuple.
	 */
	public Object transformTuple(Object[] tuple, String[] aliases) {
		return tuple[ tuple.length-1 ];
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

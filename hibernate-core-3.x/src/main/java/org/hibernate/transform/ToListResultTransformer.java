/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.transform;

import java.util.Arrays;
import java.util.List;
import java.io.Serializable;

/**
 * Tranforms each result row from a tuple into a {@link List}, such that what
 * you end up with is a {@link List} of {@link List Lists}.
 */
public class ToListResultTransformer extends BasicTransformerAdapter implements Serializable {

	public static final ToListResultTransformer INSTANCE = new ToListResultTransformer();

	/**
	 * Disallow instantiation of ToListResultTransformer.
	 */
	private ToListResultTransformer() {
	}
	
	/**
	 * {@inheritDoc}
	 */
	public Object transformTuple(Object[] tuple, String[] aliases) {
		return Arrays.asList( tuple );
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

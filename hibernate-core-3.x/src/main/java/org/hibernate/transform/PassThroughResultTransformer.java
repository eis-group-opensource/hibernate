/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.transform;

import java.io.Serializable;

/**
 * ???
 *
 * @author max
 */
public class PassThroughResultTransformer extends BasicTransformerAdapter implements Serializable {

	public static final PassThroughResultTransformer INSTANCE = new PassThroughResultTransformer();

	/**
	 * Disallow instantiation of PassThroughResultTransformer.
	 */
	private PassThroughResultTransformer() {
	}

	/**
	 * {@inheritDoc}
	 */
	public Object transformTuple(Object[] tuple, String[] aliases) {
		return tuple.length==1 ? tuple[0] : tuple;
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

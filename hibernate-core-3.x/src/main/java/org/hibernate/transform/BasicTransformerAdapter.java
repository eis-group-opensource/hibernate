/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.transform;

import java.util.List;

/**
 * Provides the basic "noop" impls of the {@link ResultTransformer} contract.
 *
 * @author Steve Ebersole
 */
public abstract class BasicTransformerAdapter implements ResultTransformer {
	public Object transformTuple(Object[] tuple, String[] aliases) {
		return tuple;
	}

	public List transformList(List list) {
		return list;
	}
}

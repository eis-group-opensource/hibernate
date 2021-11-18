/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.cache;

import org.hibernate.cfg.Environment;

/**
 * Implementation of NoCachingEnabledException.
 *
 * @author Steve Ebersole
 */
public class NoCachingEnabledException extends CacheException {
	private static final String MSG =
	        "Second-level cache is not enabled for usage [" +
	        Environment.USE_SECOND_LEVEL_CACHE +
	        " | " + Environment.USE_QUERY_CACHE + "]";

	public NoCachingEnabledException() {
		super( MSG );
	}
}

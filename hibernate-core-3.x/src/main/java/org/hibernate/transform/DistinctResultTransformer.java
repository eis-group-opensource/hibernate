/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.transform;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Distinctions the result tuples in the final result based on the defined
 * equality of the tuples.
 * <p/>
 * Since this transformer is stateless, all instances would be considered equal.
 * So for optimization purposes we limit it to a single, singleton {@link #INSTANCE instance}.
 *
 * @author Steve Ebersole
 */
public class DistinctResultTransformer extends BasicTransformerAdapter implements Serializable {

	public static final DistinctResultTransformer INSTANCE = new DistinctResultTransformer();

	private static final Logger log = LoggerFactory.getLogger( DistinctResultTransformer.class );

	/**
	 * Helper class to handle distincting
	 */
	private static final class Identity {
		final Object entity;

		private Identity(Object entity) {
			this.entity = entity;
		}

		/**
		 * {@inheritDoc}
		 */
		public boolean equals(Object other) {
			return Identity.class.isInstance( other )
					&& this.entity == ( ( Identity ) other ).entity;
		}

		/**
		 * {@inheritDoc}
		 */
		public int hashCode() {
			return System.identityHashCode( entity );
		}
	}

	/**
	 * Disallow instantiation of DistinctResultTransformer.
	 */
	private DistinctResultTransformer() {
	}

	/**
	 * Uniquely distinct each tuple row here.
	 */
	public List transformList(List list) {
		List result = new ArrayList( list.size() );
		Set distinct = new HashSet();
		for ( int i = 0; i < list.size(); i++ ) {
			Object entity = list.get( i );
			if ( distinct.add( new Identity( entity ) ) ) {
				result.add( entity );
			}
		}
		if ( log.isDebugEnabled() ) {
			log.debug(
					"transformed: " +
							list.size() + " rows to: " +
							result.size() + " distinct results"
			);
		}
		return result;
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

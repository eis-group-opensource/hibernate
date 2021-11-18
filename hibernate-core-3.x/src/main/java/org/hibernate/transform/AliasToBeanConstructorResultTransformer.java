/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.transform;

import java.lang.reflect.Constructor;
import java.util.List;

import org.hibernate.QueryException;

/**
 * Wraps the tuples in a constructor call.
 *
 * todo : why Alias* in the name???
 */
public class AliasToBeanConstructorResultTransformer implements ResultTransformer {

	private final Constructor constructor;

	/**
	 * Instantiates a AliasToBeanConstructorResultTransformer.
	 *
	 * @param constructor The contructor in which to wrap the tuples.
	 */
	public AliasToBeanConstructorResultTransformer(Constructor constructor) {
		this.constructor = constructor;
	}
	
	/**
	 * Wrap the incoming tuples in a call to our configured constructor.
	 */
	public Object transformTuple(Object[] tuple, String[] aliases) {
		try {
			return constructor.newInstance( tuple );
		}
		catch ( Exception e ) {
			throw new QueryException( 
					"could not instantiate class [" + constructor.getDeclaringClass().getName() + "] from tuple",
					e
			);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public List transformList(List collection) {
		return collection;
	}

	/**
	 * Define our hashCode by our defined constructor's hasCode.
	 *
	 * @return Our defined ctor hashCode
	 */
	public int hashCode() {
		return constructor.hashCode();
	}

	/**
	 * 2 AliasToBeanConstructorResultTransformer are considered equal if they have the same
	 * defined constructor.
	 *
	 * @param other The other instance to check for equality.
	 * @return True if both have the same defined constuctor; false otherwise.
	 */
	public boolean equals(Object other) {
		return other instanceof AliasToBeanConstructorResultTransformer
				&& constructor.equals( ( ( AliasToBeanConstructorResultTransformer ) other ).constructor );
	}
}

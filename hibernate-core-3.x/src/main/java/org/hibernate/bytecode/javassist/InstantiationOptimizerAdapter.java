/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.bytecode.javassist;

import org.hibernate.bytecode.ReflectionOptimizer;
import org.hibernate.InstantiationException;

import java.io.Serializable;

/**
 * The {@link ReflectionOptimizer.InstantiationOptimizer} implementation for Javassist
 * which simply acts as an adpater to the {@link FastClass} class.
 *
 * @author Steve Ebersole
 */
public class InstantiationOptimizerAdapter implements ReflectionOptimizer.InstantiationOptimizer, Serializable {
	private final FastClass fastClass;

	public InstantiationOptimizerAdapter(FastClass fastClass) {
		this.fastClass = fastClass;
	}

	public Object newInstance() {
		try {
			return fastClass.newInstance();
		}
		catch ( Throwable t ) {
			throw new InstantiationException(
					"Could not instantiate entity with Javassist optimizer: ",
			        fastClass.getJavaClass(), t
			);
		}
	}
}

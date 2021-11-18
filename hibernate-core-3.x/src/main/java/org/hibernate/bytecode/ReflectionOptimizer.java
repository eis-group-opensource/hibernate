/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.bytecode;

/**
 * Represents reflection optimization for a particular class.
 *
 * @author Steve Ebersole
 */
public interface ReflectionOptimizer {

	public InstantiationOptimizer getInstantiationOptimizer();
	public AccessOptimizer getAccessOptimizer();

	/**
	 * Represents optimized entity instantiation.
	 */
	public static interface InstantiationOptimizer {
		/**
		 * Perform instantiation of an instance of the underlying class.
		 *
		 * @return The new instance.
		 */
		public Object newInstance();
	}

	/**
	 * Represents optimized entity property access.
	 *
	 * @author Steve Ebersole
	 */
	public interface AccessOptimizer {
		public String[] getPropertyNames();
		public Object[] getPropertyValues(Object object);
		public void setPropertyValues(Object object, Object[] values);
	}
}

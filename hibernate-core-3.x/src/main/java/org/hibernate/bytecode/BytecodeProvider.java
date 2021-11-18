/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.bytecode;

import org.hibernate.bytecode.util.ClassFilter;
import org.hibernate.bytecode.util.FieldFilter;

/**
 * Contract for providers of bytecode services to Hibernate.
 * <p/>
 * Bytecode requirements break down into basically 3 areas<ol>
 * <li>proxy generation (both for runtime-lazy-loading and basic proxy generation)
 * {@link #getProxyFactoryFactory()}
 * <li>bean reflection optimization {@link #getReflectionOptimizer}
 * <li>field-access instrumentation {@link #getTransformer}
 * </ol>
 *
 * @author Steve Ebersole
 */
public interface BytecodeProvider {
	/**
	 * Retrieve the specific factory for this provider capable of
	 * generating run-time proxies for lazy-loading purposes.
	 *
	 * @return The provider specific factory.
	 */
	public ProxyFactoryFactory getProxyFactoryFactory();

	/**
	 * Retrieve the ReflectionOptimizer delegate for this provider
	 * capable of generating reflection optimization components.
	 *
	 * @param clazz The class to be reflected upon.
	 * @param getterNames Names of all property getters to be accessed via reflection.
	 * @param setterNames Names of all property setters to be accessed via reflection.
	 * @param types The types of all properties to be accessed.
	 * @return The reflection optimization delegate.
	 */
	public ReflectionOptimizer getReflectionOptimizer(Class clazz, String[] getterNames, String[] setterNames, Class[] types);

	/**
	 * Generate a ClassTransformer capable of performing bytecode manipulation.
	 *
	 * @param classFilter filter used to limit which classes are to be instrumented
	 * via this ClassTransformer.
	 * @param fieldFilter filter used to limit which fields are to be instrumented
	 * via this ClassTransformer.
	 * @return The appropriate ClassTransformer.
	 */
	public ClassTransformer getTransformer(ClassFilter classFilter, FieldFilter fieldFilter);
}

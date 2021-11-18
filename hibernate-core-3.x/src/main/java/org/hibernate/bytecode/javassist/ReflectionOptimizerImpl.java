/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.bytecode.javassist;

import org.hibernate.bytecode.ReflectionOptimizer;

import java.io.Serializable;

/**
 * ReflectionOptimizer implementation for Javassist.
 *
 * @author Steve Ebersole
 */
public class ReflectionOptimizerImpl implements ReflectionOptimizer, Serializable {

	private final InstantiationOptimizer instantiationOptimizer;
	private final AccessOptimizer accessOptimizer;

	public ReflectionOptimizerImpl(
			InstantiationOptimizer instantiationOptimizer,
	        AccessOptimizer accessOptimizer) {
		this.instantiationOptimizer = instantiationOptimizer;
		this.accessOptimizer = accessOptimizer;
	}

	public InstantiationOptimizer getInstantiationOptimizer() {
		return instantiationOptimizer;
	}

	public AccessOptimizer getAccessOptimizer() {
		return accessOptimizer;
	}

}

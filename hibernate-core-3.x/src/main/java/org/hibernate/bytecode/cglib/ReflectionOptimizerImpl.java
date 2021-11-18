/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.bytecode.cglib;

import org.hibernate.bytecode.ReflectionOptimizer;

import java.io.Serializable;

/**
 * ReflectionOptimizer implementation for CGLIB.
 *
 * @author Steve Ebersole
 *
 * @deprecated Per HHH-5451 support for cglib as a bytecode provider has been deprecated.
 */
public class ReflectionOptimizerImpl implements ReflectionOptimizer, Serializable {
	private transient InstantiationOptimizerAdapter instantiationOptimizer;
	private transient AccessOptimizerAdapter accessOptimizer;

	public ReflectionOptimizerImpl(
			InstantiationOptimizerAdapter instantiationOptimizer,
	        AccessOptimizerAdapter accessOptimizer) {
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

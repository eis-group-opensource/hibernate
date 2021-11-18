/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.id;

import java.util.Map;

/**
 * Identifies {@link IdentifierGenerator generators} which potentially aggregate other
 * {@link PersistentIdentifierGenerator} generators.
 * <p/>
 * Initially this is limited to {@link CompositeNestedGeneratedValueGenerator}
 *
 * @author Steve Ebersole
 */
public interface IdentifierGeneratorAggregator {
	/**
	 * Register any sub generators which implement {@link PersistentIdentifierGenerator} by their
	 * {@link PersistentIdentifierGenerator#generatorKey generatorKey}.
	 *
	 * @param generatorMap The map of generators.
	 */
	public void registerPersistentGenerators(Map generatorMap);
}

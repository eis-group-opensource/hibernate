/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.id.factory;

import java.util.Properties;
import java.io.Serializable;

import org.hibernate.id.IdentifierGenerator;
import org.hibernate.type.Type;
import org.hibernate.dialect.Dialect;

/**
 * Contract for a <tt>factory</tt> of {@link IdentifierGenerator} instances.
 *
 * @author Steve Ebersole
 */
public interface IdentifierGeneratorFactory {

	/**
	 * Allow injection of the dialect to use.
	 *
	 * @param dialect The dialect
	 * @deprecated The intention is that Dialect should be required to be specified up-front and it would then get
	 * ctor injected.
	 */
	public void setDialect(Dialect dialect);

	/**
	 * Given a strategy, retrieve the appropriate identifier generator instance.
	 *
	 * @param strategy The generation strategy.
	 * @param type The mapping type for the identifier values.
	 * @param config Any configuraion properties given in the generator mapping.
	 *
	 * @return The appropriate generator instance.
	 */
	public IdentifierGenerator createIdentifierGenerator(String strategy, Type type, Properties config);

	/**
	 * Retrieve the class that will be used as the {@link IdentifierGenerator} for the given strategy.
	 *
	 * @param strategy The strategy
	 * @return The generator class.
	 */
	public Class getIdentifierGeneratorClass(String strategy);
}

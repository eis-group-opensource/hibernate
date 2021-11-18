/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package javax.validation.bootstrap;

import javax.validation.ValidationProviderResolver;
import javax.validation.Configuration;

/**
 * Defines the state used to bootstrap Bean Validation and
 * creates a provider agnostic <code>Configuration</code>.
 *
 * @author Emmanuel Bernard
 */
public interface GenericBootstrap {
	/**
	 * Defines the provider resolution strategy.
	 * This resolver returns the list of providers evaluated
	 * to build the <code>Configuration</code>
	 * <p/>
	 * If no resolver is defined, the default <code>ValidationProviderResolver</code>
	 * implementation is used.
	 *
	 * @return <code>this</code> following the chaining method pattern
	 */
	GenericBootstrap providerResolver(ValidationProviderResolver resolver);

	/**
	 * Returns a generic <code>Configuration</code> implementation.
	 * At this stage the provider used to build the <code>ValidatorFactory</code>
	 * is not defined.
	 * <p/>
	 * The <code>Configuration</code> implementation is provided by the first provider
	 * returned by the <code>ValidationProviderResolver</code> strategy.
	 *
	 * @return a Configuration implementation compliant with the bootstrap state
	 * @throws javax.validation.ValidationException if the Configuration object cannot be built
	 *                        this is generally due to an issue with the ValidationProviderResolver
	 */
	Configuration<?> configure();
}

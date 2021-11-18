/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package javax.validation.bootstrap;

import javax.validation.ValidationProviderResolver;
import javax.validation.Configuration;
import javax.validation.spi.ValidationProvider;

/**
 * Defines the state used to bootstrap Bean Validation and
 * creates a provider specific <code>Configuration</code>
 * of type<code>T</code>.
 * <p/>
 * The specific <code>Configuration</code> is linked to the provider via the generic
 * parameter of the <code>ValidationProvider</code> implementation.
 * <p/>
 * The requested provider is the first provider instance assignable to
 * the requested provider type (known when <code>ProviderSpecificBootstrap</code> is built).
 * The list of providers evaluated is returned by {@link ValidationProviderResolver}.
 * If no <code>ValidationProviderResolver</code> is defined, the
 * default <code>ValidationProviderResolver</code> strategy is used.
 *
 * @author Emmanuel Bernard
 */
public interface ProviderSpecificBootstrap<T extends Configuration<T>> {

	/**
	 * Optionally defines the provider resolver implementation used.
	 * If not defined, use the default <code>ValidationProviderResolver</code>
	 *
	 * @param resolver <code>ValidationProviderResolver</code> implementation used
	 *
	 * @return <code>this</code> following the chaining method pattern
	 */
	public ProviderSpecificBootstrap<T> providerResolver(ValidationProviderResolver resolver);

	/**
	 * Determines the provider implementation suitable for <code>T</code> and delegates
	 * the creation of this specific <code>Configuration</code> subclass to the provider.
	 *
	 * @return <code>Configuration</code> sub interface implementation
	 * @throws javax.validation.ValidationException if the Configuration object cannot be built
	 *                        this is generally due to an issue with the ValidationProviderResolver
	 */
	public T configure();
}

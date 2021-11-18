/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package javax.validation.spi;

import javax.validation.Configuration;
import javax.validation.ValidatorFactory;

/**
 * Contract between the validation bootstrap mechanism and the provider engine.
 * <p/>
 * Implementations must have a public no-arg constructor. The construction of a provider
 * should be as "lightweight" as possible.
 *
 * <code>T</code> represents the provider specific Configuration subclass
 * which typically host provider's additional configuration methods.
 *
 * @author Emmanuel Bernard
 * @author Hardy Ferentschik
 */
public interface ValidationProvider<T extends Configuration<T>> {

	/**
	 * Returns a <code>Configuration</code> instance implementing <code>T</code>,
	 * the <code>Configuration</code> subinterface.
	 * The returned <code>Configuration</code> instance must use the current provider
	 * (<code>this</code>) to build the <code>ValidatorFactory</code> instance.
	 * <p/>
	 *
	 * @param state bootstrap state
	 *
	 * @return specific Configuration implementation
	 */
	T createSpecializedConfiguration(BootstrapState state);

	/**
	 * Returns a <code>Configuration</code> instance. This instance is not bound to
	 * use the current provider. The choice of provider follows the algorithm described
	 * in {@link javax.validation.Configuration}
	 * <p/>
	 * The <code>ValidationProviderResolver</code> used by <code>Configuration</code>
	 * is provided by <code>state</code>.
	 * If null, the default <code>ValidationProviderResolver</code> is used.
	 *
	 * @param state bootstrap state
	 *
	 * @return Non specialized Configuration implementation
	 */
	Configuration<?> createGenericConfiguration(BootstrapState state);

	/**
	 * Build a <code>ValidatorFactory</code> using the current provider implementation.
	 * The <code>ValidatorFactory</code> is assembled and follows the configuration passed
	 * via <code>ConfigurationState</code>.
	 * <p>
	 * The returned <code>ValidatorFactory</code> is properly initialized and ready for use.
	 * </p>
	 *
	 * @param configurationState the configuration descriptor
	 *
	 * @return the instanciated ValidatorFactory
	 * @throws javax.validation.ValidationException if the ValidatorFactory cannot be built
	 */
	ValidatorFactory buildValidatorFactory(ConfigurationState configurationState);
}

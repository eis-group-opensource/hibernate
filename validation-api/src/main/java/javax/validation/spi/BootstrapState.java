/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package javax.validation.spi;

import javax.validation.ValidationProviderResolver;

/**
 * Defines the state used to bootstrap the <code>Configuration</code>
 *
 * @author Emmanuel Bernard
 * @author Sebastian Thomschke 
 */
public interface BootstrapState {
	/**
	 * User defined <code>ValidationProviderResolver</code> strategy
	 * instance or <code>null</code> if undefined.
	 *
	 * @return ValidationProviderResolver instance or null
	 */
	ValidationProviderResolver getValidationProviderResolver();

	/**
	 * Specification default <code>ValidationProviderResolver</code>
	 * strategy instance.
	 * 
	 * @return default implementation of ValidationProviderResolver
	 */
	ValidationProviderResolver getDefaultValidationProviderResolver();
}

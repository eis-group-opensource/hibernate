/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package javax.validation;

import java.util.List;
import javax.validation.spi.ValidationProvider;

/**
 * Determines the list of Bean Validation providers available in the runtime environment
 * <p/>
 * Bean Validation providers are identified by the presence of
 * META-INF/services/javax.validation.spi.ValidationProvider
 * files following the Service Provider pattern described
 * <a href="http://java.sun.com/j2se/1.5.0/docs/guide/jar/jar.html#Service%20Provider">here</a>
 * <p/>
 * Each META-INF/services/javax.validation.spi.ValidationProvider file contains the list of
 * <code>ValidationProvider</code> implementations each of them representing a provider.
 * <p/>
 * Implementations must be thread-safe.
 *
 * @author Emmanuel Bernard
 */
public interface ValidationProviderResolver {
	/**
	 * Returns a list of ValidationProviders available in the runtime environment.
	 *
	 * @return list of validation providers.
	 */
	List<ValidationProvider<?>> getValidationProviders();
}

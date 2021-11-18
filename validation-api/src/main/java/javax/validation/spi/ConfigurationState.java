/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package javax.validation.spi;

import java.io.InputStream;
import java.util.Map;
import java.util.Set;
import javax.validation.ConstraintValidatorFactory;
import javax.validation.MessageInterpolator;
import javax.validation.TraversableResolver;

/**
 * Contract between a <code>Configuration</code> and a
 * </code>ValidatorProvider</code> to create a <code>ValidatorFactory</code>.
 * The configuration artifacts defined in the XML configuration and provided to the
 * <code>Configuration</code> are merged and passed along via
 * <code>ConfigurationState</code>.
 *
 * @author Emmanuel Bernard
 * @author Hardy Ferentschik
 */
public interface ConfigurationState {

	/**
	 * Returns true if Configuration.ignoreXMLConfiguration() has been called
	 * In this case, the ValidatorFactory must ignore META-INF/validation.xml
	 *
	 * @return {@code true} if META-INF/validation.xml should be ignored
	 */
	boolean isIgnoreXmlConfiguration();

	/**
	 * Returns the message interpolator of this configuration.
	 * Message interpolator is defined in the following decreasing priority:
	 * <ul>
	 * <li>set via the <code>Configuration</code> programmatic API</li>
	 * <li>defined in META-INF/validation.xml provided that ignoreXmlConfiguration
	 * is false. In this case the instance is created via its no-arg constructor.</li>
	 * <li>{@code null} if undefined.</li>
	 * </ul>
	 *
	 * @return message provider instance or null if not defined
	 */
	MessageInterpolator getMessageInterpolator();

	/**
	 * Returns a set of configuration streams.
	 * The streams are defined by:
	 * <ul>
	 * <li>mapping XML streams passed programmatically in <code>Configuration</code></li>
	 * <li>mapping XML stream located in the resources defined in</li>
	 * META-INF/validation.xml (constraint-mapping element)
	 * </ul>
	 * Streams represented in the XML configuration and opened by the
	 * <code>Configuration</code> implementation must be closed by the
	 * <code>Configuration</code> implementation after the <code>ValidatorFactory</code>
	 * creation (or if an exception occurs).
	 *
	 * @return set of input stream
	 */
	Set<InputStream> getMappingStreams();

	/**
	 * Returns the constraint validator factory of this configuration.
	 * The {@code ConstraintValidatorFactory} implementation is defined in the following
	 * decreasing priority:
	 * <ul>
	 * <li>set via the <code>Configuration</code> programmatic API</li>
	 * <li>defined in META-INF/validation.xml provided that ignoredXmlConfiguration
	 * is false. In this case the instance is created via its no-arg constructor.</li>
	 * <li>{@code null} if undefined.</li>
	 * </ul>
	 *
	 * @return factory instance or {@code null} if not defined
	 */
	ConstraintValidatorFactory getConstraintValidatorFactory();

	/**
	 * Returns the traversable resolver for this configuration.
	 * <code>TraversableResolver</code> is defined in the following decreasing priority:
	 * <ul>
	 * <li>set via the <code>Configuration</code> programmatic API</li>
	 * <li>defined in META-INF/validation.xml provided that ignoredXmlConfiguration
	 * is false. In this case the instance is created via its no-arg constructor.</li>
	 * <li>{@code null} if undefined.</li>
	 * </ul>
	 *
	 * @return traversable provider instance or {@code null} if not defined
	 */
	TraversableResolver getTraversableResolver();

	/**
	 * Returns a map of non type-safe custom properties.
	 * Properties defined via:
	 * <ul>
	 * <li>Configuration.addProperty(String, String)</li>
	 * <li>META-INF/validation.xml provided that ignoredXmlConfiguration</li>
	 * is false.
	 * </ul>
	 * If a property is defined both programmatically and in XML,
	 * the value defined programmatically has priority
	 *
	 * @return Map whose key is the property key and the value the property value
	 */
	Map<String, String> getProperties();
}

/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.property;

import org.hibernate.PropertyNotFoundException;

/**
 * Abstracts the notion of a "property". Defines a strategy for accessing the
 * value of an attribute.
 *
 * @author Gavin King
 */
public interface PropertyAccessor {
	/**
	 * Create a "getter" for the named attribute
	 *
	 * @param theClass The class on which the property is defined.
	 * @param propertyName The name of the property.
	 *
	 * @return An appropriate getter.
	 *
	 * @throws PropertyNotFoundException Indicates a problem interpretting the propertyName
	 */
	public Getter getGetter(Class theClass, String propertyName) throws PropertyNotFoundException;

	/**
	 * Create a "setter" for the named attribute
	 *
	 * @param theClass The class on which the property is defined.
	 * @param propertyName The name of the property.
	 *
	 * @return An appropriate setter
	 *
	 * @throws PropertyNotFoundException Indicates a problem interpretting the propertyName
	 */
	public Setter getSetter(Class theClass, String propertyName) throws PropertyNotFoundException;
}

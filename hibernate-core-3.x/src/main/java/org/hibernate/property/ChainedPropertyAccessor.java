/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.property;

import org.hibernate.PropertyNotFoundException;

/**
 * @author max
 *
 */
public class ChainedPropertyAccessor implements PropertyAccessor {

	final PropertyAccessor[] chain;
	
	public ChainedPropertyAccessor(PropertyAccessor[] chain) {
		this.chain = chain;
	}
	
	public Getter getGetter(Class theClass, String propertyName)
			throws PropertyNotFoundException {
		Getter result = null;
		for (int i = 0; i < chain.length; i++) {
			PropertyAccessor candidate = chain[i];
			try {
				result = candidate.getGetter(theClass, propertyName);
				return result;
			} catch (PropertyNotFoundException pnfe) {
				// ignore
			}
		}
		throw new PropertyNotFoundException("Could not find getter for " + propertyName + " on " + theClass);
	}

	public Setter getSetter(Class theClass, String propertyName)
			throws PropertyNotFoundException {
		Setter result = null;
		for (int i = 0; i < chain.length; i++) {
			PropertyAccessor candidate = chain[i];
			try {
				result = candidate.getSetter(theClass, propertyName);
				return result;
			} catch (PropertyNotFoundException pnfe) {
				//
			}
		}
		throw new PropertyNotFoundException("Could not find setter for " + propertyName + " on " + theClass);
	}

}

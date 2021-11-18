/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.tuple.component;

import java.util.Map;

import org.hibernate.mapping.Component;
import org.hibernate.mapping.Property;
import org.hibernate.property.Getter;
import org.hibernate.property.PropertyAccessor;
import org.hibernate.property.PropertyAccessorFactory;
import org.hibernate.property.Setter;
import org.hibernate.tuple.Instantiator;
import org.hibernate.tuple.DynamicMapInstantiator;

/**
 * A {@link ComponentTuplizer} specific to the dynamic-map entity mode.
 *
 * @author Gavin King
 * @author Steve Ebersole
 */
public class DynamicMapComponentTuplizer extends AbstractComponentTuplizer {

	public Class getMappedClass() {
		return Map.class;
	}

	protected Instantiator buildInstantiator(Component component) {
		return new DynamicMapInstantiator();
	}

	public DynamicMapComponentTuplizer(Component component) {
		super(component);
	}

	private PropertyAccessor buildPropertyAccessor(Property property) {
		return PropertyAccessorFactory.getDynamicMapPropertyAccessor();
	}

	protected Getter buildGetter(Component component, Property prop) {
		return buildPropertyAccessor(prop).getGetter( null, prop.getName() );
	}

	protected Setter buildSetter(Component component, Property prop) {
		return buildPropertyAccessor(prop).getSetter( null, prop.getName() );
	}

}

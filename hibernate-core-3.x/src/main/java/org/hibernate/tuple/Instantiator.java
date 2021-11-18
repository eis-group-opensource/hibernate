/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.tuple;


import java.io.Serializable;

/**
 * Contract for implementors responsible for instantiating entity/component instances.
 *
 * @author Steve Ebersole
 */
public interface Instantiator extends Serializable {

	/**
	 * Perform the requested entity instantiation.
	 * <p/>
	 * This form is never called for component instantiation, only entity instantiation.
	 *
	 * @param id The id of the entity to be instantiated.
	 * @return An appropriately instantiated entity.
	 */
	public Object instantiate(Serializable id);

	/**
	 * Perform the requested instantiation.
	 *
	 * @return The instantiated data structure. 
	 */
	public Object instantiate();

	/**
	 * Performs check to see if the given object is an instance of the entity
	 * or component which this Instantiator instantiates.
	 *
	 * @param object The object to be checked.
	 * @return True is the object does represent an instance of the underlying
	 * entity/component.
	 */
	public boolean isInstance(Object object);
}

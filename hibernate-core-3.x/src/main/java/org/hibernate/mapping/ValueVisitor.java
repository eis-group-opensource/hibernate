/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.mapping;

/**
 * @author max
 *
 */
public interface ValueVisitor {

	/**
	 * @param bag
	 */
	Object accept(Bag bag);

	/**
	 * @param bag
	 */
	Object accept(IdentifierBag bag);

	/**
	 * @param list
	 */
	Object accept(List list);
	
	Object accept(PrimitiveArray primitiveArray);
	Object accept(Array list);

	/**
	 * @param map
	 */
	Object accept(Map map);

	/**
	 * @param many
	 */
	Object accept(OneToMany many);

	/**
	 * @param set
	 */
	Object accept(Set set);

	/**
	 * @param any
	 */
	Object accept(Any any);

	/**
	 * @param value
	 */
	Object accept(SimpleValue value);
	Object accept(DependantValue value);
	
	Object accept(Component component);
	
	Object accept(ManyToOne mto);
	Object accept(OneToOne oto);
	

}

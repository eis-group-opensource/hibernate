/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.mapping;

/**
 * @author max
 *
 */
public interface PersistentClassVisitor {

	/**
	 * @param class1
	 * @return
	 */
	Object accept(RootClass class1);

	/**
	 * @param subclass
	 * @return
	 */
	Object accept(UnionSubclass subclass);

	/**
	 * @param subclass
	 * @return
	 */
	Object accept(SingleTableSubclass subclass);

	/**
	 * @param subclass
	 * @return
	 */
	Object accept(JoinedSubclass subclass);

	/**
	 * @param subclass
	 * @return
	 */
	Object accept(Subclass subclass);

	
}

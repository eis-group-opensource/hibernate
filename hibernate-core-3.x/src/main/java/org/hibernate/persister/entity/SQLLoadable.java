/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.persister.entity;

import org.hibernate.type.Type;

/**
 * A class persister that supports queries expressed in the
 * platform native SQL dialect
 *
 * @author Gavin King, Max Andersen
 */
public interface SQLLoadable extends Loadable {

	/**
	 * Return the column alias names used to persist/query the named property of the class or a subclass (optional operation).
	 */
	public String[] getSubclassPropertyColumnAliases(String propertyName, String suffix);

	/**
	 * Return the column names used to persist/query the named property of the class or a subclass (optional operation).
	 */
	public String[] getSubclassPropertyColumnNames(String propertyName);
	
	/**
	 * All columns to select, when loading.
	 */
	public String selectFragment(String alias, String suffix);

	/**
	 * Get the type
	 */
	public Type getType();

	

}

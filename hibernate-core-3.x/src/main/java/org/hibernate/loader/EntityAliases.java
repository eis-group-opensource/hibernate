/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.loader;

import org.hibernate.persister.entity.Loadable;

/**
 * Metadata describing the SQL result set column aliases
 * for a particular entity.
 * 
 * @author Gavin King
 */
public interface EntityAliases {
	/**
	 * The result set column aliases for the primary key columns
	 */
	public String[] getSuffixedKeyAliases();
	/**
	 * The result set column aliases for the discriminator columns
	 */
	public String getSuffixedDiscriminatorAlias();
	/**
	 * The result set column aliases for the version columns
	 */
	public String[] getSuffixedVersionAliases();
	/**
	 * The result set column aliases for the property columns
	 */
	public String[][] getSuffixedPropertyAliases();
	/**
	 * The result set column aliases for the property columns of a subclass
	 */
	public String[][] getSuffixedPropertyAliases(Loadable persister);
	/**
	 * The result set column alias for the Oracle row id
	 */
	public String getRowIdAlias();

}

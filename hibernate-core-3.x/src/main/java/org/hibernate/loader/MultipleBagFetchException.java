/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.loader;

import java.util.List;

import org.hibernate.HibernateException;

/**
 * Exception used to indicate that a query is attempting to simultaneously fetch multiple
 * {@link org.hibernate.type.BagType bags}
*
* @author Steve Ebersole
*/
public class MultipleBagFetchException extends HibernateException {
	private final List bagRoles;

	public MultipleBagFetchException(List bagRoles) {
		super( "cannot simultaneously fetch multiple bags" );
		this.bagRoles = bagRoles;
	}

	/**
	 * Retrieves the collection-roles for the bags encountered.
	 *
	 * @return The bag collection roles.
	 */
	public List getBagRoles() {
		return bagRoles;
	}
}

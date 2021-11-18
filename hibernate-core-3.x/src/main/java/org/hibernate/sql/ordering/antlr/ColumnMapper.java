/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.sql.ordering.antlr;

import org.hibernate.HibernateException;

/**
 * Contract for mapping a (an assumed) property reference to its columns.
 *
 * @author Steve Ebersole
 */
public interface ColumnMapper {
	/**
	 * Resolve the property reference to its underlying columns.
	 *
	 * @param reference The property reference name.
	 *
	 * @return The underlying columns, or null if the property reference is unknown.
	 *
	 * @throws HibernateException Generally indicates that the property reference is unkown; interpretation is the
	 * same as a null return.
	 */
	public String[] map(String reference) throws HibernateException;
}

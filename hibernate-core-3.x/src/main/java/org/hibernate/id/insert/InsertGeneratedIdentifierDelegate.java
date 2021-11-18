/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.id.insert;

import org.hibernate.engine.SessionImplementor;

import java.io.Serializable;

/**
 * Responsible for handling delegation relating to variants in how
 * insert-generated-identifier generator strategies dictate processing:<ul>
 * <li>building the sql insert statement
 * <li>determination of the generated identifier value
 * </ul>
 *
 * @author Steve Ebersole
 */
public interface InsertGeneratedIdentifierDelegate {

	/**
	 * Build a {@link org.hibernate.sql.Insert} specific to the delegate's mode
	 * of handling generated key values.
	 *
	 * @return The insert object.
	 */
	public IdentifierGeneratingInsert prepareIdentifierGeneratingInsert();

	/**
	 * Perform the indicated insert SQL statement and determine the identifier value
	 * generated.
	 *
	 * @param insertSQL The INSERT statement string
	 * @param session The session in which we are operating
	 * @param binder The param binder
	 * @return The generated identifier value.
	 */
	public Serializable performInsert(String insertSQL, SessionImplementor session, Binder binder);

}

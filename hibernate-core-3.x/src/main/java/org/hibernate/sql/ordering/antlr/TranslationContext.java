/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.sql.ordering.antlr;

import org.hibernate.engine.SessionFactoryImplementor;
import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.function.SQLFunctionRegistry;

/**
 * Contract for contextual information required to perform translation.
*
* @author Steve Ebersole
*/
public interface TranslationContext {
	/**
	 * Retrieves the <tt>session factory</tt> for this context.
	 *
	 * @return The <tt>session factory</tt>
	 */
	public SessionFactoryImplementor getSessionFactory();

	/**
	 * Retrieves the <tt>dialect</tt> for this context.
	 *
	 * @return The <tt>dialect</tt>
	 */
	public Dialect getDialect();

	/**
	 * Retrieves the <tt>SQL function registry/tt> for this context.
	 *
	 * @return The SQL function registry.
	 */
	public SQLFunctionRegistry getSqlFunctionRegistry();

	/**
	 * Retrieves the <tt>column mapper</tt> for this context.
	 *
	 * @return The <tt>column mapper</tt>
	 */
	public ColumnMapper getColumnMapper();
}

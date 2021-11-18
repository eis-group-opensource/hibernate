/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.dialect.function;

import java.util.List;

import org.hibernate.QueryException;
import org.hibernate.engine.Mapping;
import org.hibernate.engine.SessionFactoryImplementor;
import org.hibernate.type.Type;

/**
 * Provides support routines for the HQL functions as used
 * in the various SQL Dialects
 *
 * Provides an interface for supporting various HQL functions that are
 * translated to SQL. The Dialect and its sub-classes use this interface to
 * provide details required for processing of the function.
 *
 * @author David Channon
 */
public interface SQLFunction {
	/**
	 * Does this function have any arguments?
	 *
	 * @return True if the function expects to have parameters; false otherwise.
	 */
	public boolean hasArguments();

	/**
	 * If there are no arguments, are parens required?
	 *
	 * @return True if a no-arg call of this function requires parentheses.
	 */
	public boolean hasParenthesesIfNoArguments();

	/**
	 * The return type of the function.  May be either a concrete type which
	 * is preset, or variable depending upon the type of the first function
	 * argument.
	 *
	 * @param columnType the type of the first argument
	 * @param mapping The mapping source.
	 *
	 * @return The type to be expected as a return.
	 *
	 * @throws org.hibernate.QueryException Indicates an issue resolving the return type.
	 *
	 * @deprecated See http://opensource.atlassian.com/projects/hibernate/browse/HHH-5212
	 */
	public Type getReturnType(Type columnType, Mapping mapping) throws QueryException;

	/**
	 * Render the function call as SQL fragment.
	 *
	 * @param args The function arguments
	 * @param factory The SessionFactory
	 *
	 * @return The rendered function call
	 *
	 * @throws org.hibernate.QueryException Indicates a problem rendering the
	 * function call.
	 *
	 * @deprecated See http://opensource.atlassian.com/projects/hibernate/browse/HHH-5212
	 */
	public String render(List args, SessionFactoryImplementor factory) throws QueryException;
}

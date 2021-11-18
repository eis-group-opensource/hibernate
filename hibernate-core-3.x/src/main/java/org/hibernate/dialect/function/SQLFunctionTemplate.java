/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.dialect.function;

import org.hibernate.QueryException;
import org.hibernate.engine.Mapping;
import org.hibernate.engine.SessionFactoryImplementor;
import org.hibernate.type.Type;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents HQL functions that can have different representations in different SQL dialects.
 * E.g. in HQL we can define function <code>concat(?1, ?2)</code> to concatenate two strings 
 * p1 and p2. Target SQL function will be dialect-specific, e.g. <code>(?1 || ?2)</code> for 
 * Oracle, <code>concat(?1, ?2)</code> for MySql, <code>(?1 + ?2)</code> for MS SQL.
 * Each dialect will define a template as a string (exactly like above) marking function 
 * parameters with '?' followed by parameter's index (first index is 1).
 *
 * @author <a href="mailto:alex@jboss.org">Alexey Loubyansky</a>
 * @version <tt>$Revision: 6608 $</tt>
 */
public class SQLFunctionTemplate implements SQLFunction {
	private final Type type;
	private final TemplateRenderer renderer;
	private final boolean hasParenthesesIfNoArgs;

	public SQLFunctionTemplate(Type type, String template) {
		this( type, template, true );
	}

	public SQLFunctionTemplate(Type type, String template, boolean hasParenthesesIfNoArgs) {
		this.type = type;
		this.renderer = new TemplateRenderer( template );
		this.hasParenthesesIfNoArgs = hasParenthesesIfNoArgs;
	}

	/**
	 * {@inheritDoc}
	 */
	public String render(List args, SessionFactoryImplementor factory) {
		return renderer.render( args, factory );
	}

	/**
	 * {@inheritDoc}
	 */
	public Type getReturnType(Type columnType, Mapping mapping) throws QueryException {
		return type;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean hasArguments() {
		return renderer.getAnticipatedNumberOfArguments() > 0;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean hasParenthesesIfNoArguments() {
		return hasParenthesesIfNoArgs;
	}

	/**
	 * {@inheritDoc}
	 */
	public String toString() {
		return renderer.getTemplate();
	}
}

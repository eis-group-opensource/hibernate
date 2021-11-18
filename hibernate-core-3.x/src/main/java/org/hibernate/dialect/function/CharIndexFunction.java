/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.dialect.function;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.QueryException;
import org.hibernate.engine.Mapping;
import org.hibernate.engine.SessionFactoryImplementor;
import org.hibernate.type.Type;

/**
 * Emulation of <tt>locate()</tt> on Sybase
 * @author Nathan Moon
 */
public class CharIndexFunction implements SQLFunction {

	public Type getReturnType(Type columnType, Mapping mapping) throws QueryException {
		return Hibernate.INTEGER;
	}

	public boolean hasArguments() {
		return true;
	}

	public boolean hasParenthesesIfNoArguments() {
		return true;
	}

	public String render(List args, SessionFactoryImplementor factory) throws QueryException {
		boolean threeArgs = args.size() > 2;
		Object pattern = args.get(0);
		Object string = args.get(1);
		Object start = threeArgs ? args.get(2) : null;

		StringBuffer buf = new StringBuffer();
		buf.append("charindex(").append( pattern ).append(", ");
		if (threeArgs) buf.append( "right(");
		buf.append( string );
		if (threeArgs) buf.append( ", char_length(" ).append( string ).append(")-(").append( start ).append("-1))");
		buf.append(')');
		return buf.toString();
	}

}

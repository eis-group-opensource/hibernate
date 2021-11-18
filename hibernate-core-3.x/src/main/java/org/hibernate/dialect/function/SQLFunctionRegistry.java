/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.dialect.function;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.dialect.Dialect;

public class SQLFunctionRegistry {

	private final Dialect dialect;
	private final Map userFunctions;
	
	public SQLFunctionRegistry(Dialect dialect, Map userFunctions) {
		this.dialect = dialect;
		this.userFunctions = new HashMap();
		this.userFunctions.putAll( userFunctions );
	}
	
	public SQLFunction findSQLFunction(String functionName) {
		String name = functionName.toLowerCase();
		SQLFunction userFunction = (SQLFunction) userFunctions.get( name );
		
		return userFunction!=null?userFunction:(SQLFunction) dialect.getFunctions().get(name); // TODO: lowercasing done here. Was done "at random" before; maybe not needed at all ?
	}

	public boolean hasFunction(String functionName) {
		String name = functionName.toLowerCase();
		boolean hasUserFunction = userFunctions.containsKey ( name );
		
		return hasUserFunction || dialect.getFunctions().containsKey ( name ); // TODO: toLowerCase was not done before. Only used in Template.
	}

}

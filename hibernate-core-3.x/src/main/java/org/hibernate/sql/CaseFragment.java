/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.sql;

import java.util.Map;
import java.util.LinkedHashMap;

import org.hibernate.util.StringHelper;

/**
 * Abstract SQL case fragment renderer
 *
 * @author Gavin King, Simon Harris
 */
public abstract class CaseFragment {
	public abstract String toFragmentString();

	protected String returnColumnName;

	protected Map cases = new LinkedHashMap();

	public CaseFragment setReturnColumnName(String returnColumnName) {
		this.returnColumnName = returnColumnName;
		return this;
	}

	public CaseFragment setReturnColumnName(String returnColumnName, String suffix) {
		return setReturnColumnName( new Alias(suffix).toAliasString(returnColumnName) );
	}

	public CaseFragment addWhenColumnNotNull(String alias, String columnName, String value) {
		cases.put( StringHelper.qualify(alias, columnName), value );
		return this;
	}
}

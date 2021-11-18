/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.sql;

import org.hibernate.util.ArrayHelper;

/**
 * @author Gavin King
 */
public class ConditionFragment {
	private String tableAlias;
	private String[] lhs;
	private String[] rhs;
	private String op = "=";

	/**
	 * Sets the op.
	 * @param op The op to set
	 */
	public ConditionFragment setOp(String op) {
		this.op = op;
		return this;
	}

	/**
	 * Sets the tableAlias.
	 * @param tableAlias The tableAlias to set
	 */
	public ConditionFragment setTableAlias(String tableAlias) {
		this.tableAlias = tableAlias;
		return this;
	}

	public ConditionFragment setCondition(String[] lhs, String[] rhs) {
		this.lhs = lhs;
		this.rhs = rhs;
		return this;
	}

	public ConditionFragment setCondition(String[] lhs, String rhs) {
		this.lhs = lhs;
		this.rhs = ArrayHelper.fillArray(rhs, lhs.length);
		return this;
	}

	public String toFragmentString() {
		StringBuffer buf = new StringBuffer( lhs.length * 10 );
		for ( int i=0; i<lhs.length; i++ ) {
			buf.append(tableAlias)
				.append('.')
				.append( lhs[i] )
				.append(op)
				.append( rhs[i] );
			if (i<lhs.length-1) buf.append(" and ");
		}
		return buf.toString();
	}

}

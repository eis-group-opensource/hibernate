/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.loader.custom;

import org.hibernate.type.Type;

/**
 * Represent a scalar (aka simple value) return within a query result.
 *
 * @author Steve Ebersole
 */
public class ScalarReturn implements Return {
	private final Type type;
	private final String columnAlias;

	public ScalarReturn(Type type, String columnAlias) {
		this.type = type;
		this.columnAlias = columnAlias;
	}

	public Type getType() {
		return type;
	}

	public String getColumnAlias() {
		return columnAlias;
	}
}

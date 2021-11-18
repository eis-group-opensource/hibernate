/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.engine.query.sql;

import org.hibernate.type.Type;

/**
 * Describes a scalar return in a native SQL query.
 *
 * @author gloegl
 */
public class NativeSQLQueryScalarReturn implements NativeSQLQueryReturn {
	private final Type type;
	private final String columnAlias;
	private final int hashCode;

	public NativeSQLQueryScalarReturn(String alias, Type type) {
		this.type = type;
		this.columnAlias = alias;
		this.hashCode = determineHashCode();
	}

	public String getColumnAlias() {
		return columnAlias;
	}

	public Type getType() {
		return type;
	}

	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( o == null || getClass() != o.getClass() ) {
			return false;
		}

		NativeSQLQueryScalarReturn that = ( NativeSQLQueryScalarReturn ) o;

		if ( columnAlias != null ? !columnAlias.equals( that.columnAlias ) : that.columnAlias != null ) {
			return false;
		}
		if ( type != null ? !type.equals( that.type ) : that.type != null ) {
			return false;
		}

		return true;
	}

	public int hashCode() {
		return hashCode;
	}

	private int determineHashCode() {
		int result = type != null ? type.hashCode() : 0;
		result = 31 * result + ( getClass().getName().hashCode() );
		result = 31 * result + ( columnAlias != null ? columnAlias.hashCode() : 0 );
		return result;
	}
}

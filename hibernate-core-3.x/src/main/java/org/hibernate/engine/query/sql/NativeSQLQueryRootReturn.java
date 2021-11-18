/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.engine.query.sql;

import java.util.Map;

import org.hibernate.LockMode;

/**
 * Represents a return defined as part of a native sql query which
 * names a "root" entity.  A root entity means it is explicitly a
 * "column" in the result, as opposed to a fetched relationship or role.
 *
 * @author Steve Ebersole
 */
public class NativeSQLQueryRootReturn extends NativeSQLQueryNonScalarReturn {
	private final String returnEntityName;
	private final int hashCode;

	/**
	 * Construct a return representing an entity returned at the root
	 * of the result.
	 *
	 * @param alias The result alias
	 * @param entityName The entity name.
	 * @param lockMode The lock mode to apply
	 */
	public NativeSQLQueryRootReturn(String alias, String entityName, LockMode lockMode) {
		this(alias, entityName, null, lockMode);
	}

	/**
	 *
	 * @param alias The result alias
	 * @param entityName The entity name.
	 * @param propertyResults Any user-supplied column->property mappings
	 * @param lockMode The lock mode to apply
	 */
	public NativeSQLQueryRootReturn(String alias, String entityName, Map propertyResults, LockMode lockMode) {
		super( alias, propertyResults, lockMode );
		this.returnEntityName = entityName;
		this.hashCode = determineHashCode();
	}

	/**
	 * The name of the entity to be returned.
	 *
	 * @return The entity name
	 */
	public String getReturnEntityName() {
		return returnEntityName;
	}

	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( o == null || getClass() != o.getClass() ) {
			return false;
		}
		if ( ! super.equals( o ) ) {
			return false;
		}

		NativeSQLQueryRootReturn that = ( NativeSQLQueryRootReturn ) o;

		if ( returnEntityName != null ? !returnEntityName.equals( that.returnEntityName ) : that.returnEntityName != null ) {
			return false;
		}

		return true;
	}

	public int hashCode() {
		return hashCode;
	}

	private int determineHashCode() {
		int result = super.hashCode();
		result = 31 * result + ( returnEntityName != null ? returnEntityName.hashCode() : 0 );
		return result;
	}
}

/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.engine.query.sql;

import java.util.Map;

import org.hibernate.LockMode;

/**
 * Represents a return defined as part of a native sql query which
 * names a collection role in the form {classname}.{collectionrole}; it
 * is used in defining a custom sql query for loading an entity's
 * collection in non-fetching scenarios (i.e., loading the collection
 * itself as the "root" of the result).
 *
 * @author Steve Ebersole
 */
public class NativeSQLQueryCollectionReturn extends NativeSQLQueryNonScalarReturn {
	private final String ownerEntityName;
	private final String ownerProperty;
	private final int hashCode;

	/**
	 * Construct a native-sql return representing a collection initializer
	 *
	 * @param alias The result alias
	 * @param ownerEntityName The entity-name of the entity owning the collection
	 * to be initialized.
	 * @param ownerProperty The property name (on the owner) which represents
	 * the collection to be initialized.
	 * @param propertyResults Any user-supplied column->property mappings
	 * @param lockMode The lock mode to apply to the collection.
	 */
	public NativeSQLQueryCollectionReturn(
			String alias,
			String ownerEntityName,
			String ownerProperty,
			Map propertyResults,
			LockMode lockMode) {
		super( alias, propertyResults, lockMode );
		this.ownerEntityName = ownerEntityName;
		this.ownerProperty = ownerProperty;
		this.hashCode = determineHashCode();
	}

	/**
	 * Returns the class owning the collection.
	 *
	 * @return The class owning the collection.
	 */
	public String getOwnerEntityName() {
		return ownerEntityName;
	}

	/**
	 * Returns the name of the property representing the collection from the {@link #getOwnerEntityName}.
	 *
	 * @return The name of the property representing the collection on the owner class.
	 */
	public String getOwnerProperty() {
		return ownerProperty;
	}

	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( o == null || getClass() != o.getClass() ) {
			return false;
		}
		if ( !super.equals( o ) ) {
			return false;
		}

		NativeSQLQueryCollectionReturn that = ( NativeSQLQueryCollectionReturn ) o;

		if ( ownerEntityName != null ? !ownerEntityName.equals( that.ownerEntityName ) : that.ownerEntityName != null ) {
			return false;
		}
		if ( ownerProperty != null ? !ownerProperty.equals( that.ownerProperty ) : that.ownerProperty != null ) {
			return false;
		}

		return true;
	}

	public int hashCode() {
		return hashCode;
	}
	
	private int determineHashCode() {
		int result = super.hashCode();
		result = 31 * result + ( ownerEntityName != null ? ownerEntityName.hashCode() : 0 );
		result = 31 * result + ( ownerProperty != null ? ownerProperty.hashCode() : 0 );
		return result;
	}
}

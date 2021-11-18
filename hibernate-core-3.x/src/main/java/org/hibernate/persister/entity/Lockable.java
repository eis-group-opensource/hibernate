/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.persister.entity;

/**
 * Contract for things that can be locked via a {@link org.hibernate.dialect.lock.LockingStrategy}.
 * <p/>
 * Currently only the root table gets locked, except for the case of HQL and Criteria queries
 * against dialects which do not support either (1) FOR UPDATE OF or (2) support hint locking
 * (in which case *all* queried tables would be locked).
 *
 * @author Steve Ebersole
 * @since 3.2
 */
public interface Lockable extends EntityPersister {
	/**
	 * Locks are always applied to the "root table".
	 *
	 * @return The root table name
	 */
	public String getRootTableName();

	/**
	 * Get the SQL alias this persister would use for the root table
	 * given the passed driving alias.
	 *
	 * @param drivingAlias The driving alias; or the alias for the table
	 * mapped by this persister in the hierarchy.
	 * @return The root table alias.
	 */
	public String getRootTableAlias(String drivingAlias);

	/**
	 * Get the names of columns on the root table used to persist the identifier.
	 *
	 * @return The root table identifier column names.
	 */
	public String[] getRootTableIdentifierColumnNames();

	/**
	 * For versioned entities, get the name of the column (again, expected on the
	 * root table) used to store the version values.
	 *
	 * @return The version column name.
	 */
	public String getVersionColumnName();
}

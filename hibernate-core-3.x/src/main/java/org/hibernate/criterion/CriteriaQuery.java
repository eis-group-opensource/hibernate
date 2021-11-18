/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.criterion;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.engine.SessionFactoryImplementor;
import org.hibernate.engine.TypedValue;
import org.hibernate.type.Type;

/**
 * An instance of <tt>CriteriaQuery</tt> is passed to criterion, 
 * order and projection instances when actually compiling and
 * executing the query. This interface is not used by application
 * code.
 * 
 * @author Gavin King
 */
public interface CriteriaQuery {
	public SessionFactoryImplementor getFactory();
	
	/**
	 * Get the names of the columns mapped by a property path,
	 * ignoring projection aliases
	 * @throws org.hibernate.QueryException if the property maps to more than 1 column
	 */
	public String getColumn(Criteria criteria, String propertyPath) 
	throws HibernateException;
	
	/**
	 * Get the names of the columns mapped by a property path,
	 * ignoring projection aliases
	 */
	public String[] getColumns(String propertyPath, Criteria criteria)
	throws HibernateException;

	/**
	 * Get the type of a property path, ignoring projection aliases
	 */
	public Type getType(Criteria criteria, String propertyPath)
	throws HibernateException;

	/**
	 * Get the names of the columns mapped by a property path
	 */
	public String[] getColumnsUsingProjection(Criteria criteria, String propertyPath) 
	throws HibernateException;
	
	/**
	 * Get the type of a property path
	 */
	public Type getTypeUsingProjection(Criteria criteria, String propertyPath)
	throws HibernateException;

	/**
	 * Get the a typed value for the given property value.
	 */
	public TypedValue getTypedValue(Criteria criteria, String propertyPath, Object value) 
	throws HibernateException;
	
	/**
	 * Get the entity name of an entity
	 */
	public String getEntityName(Criteria criteria);
	
	/**
	 * Get the entity name of an entity, taking into account
	 * the qualifier of the property path
	 */
	public String getEntityName(Criteria criteria, String propertyPath);

	/**
	 * Get the root table alias of an entity
	 */
	public String getSQLAlias(Criteria subcriteria);

	/**
	 * Get the root table alias of an entity, taking into account
	 * the qualifier of the property path
	 */
	public String getSQLAlias(Criteria criteria, String propertyPath);
	
	/**
	 * Get the property name, given a possibly qualified property name
	 */
	public String getPropertyName(String propertyName);
	
	/**
	 * Get the identifier column names of this entity
	 */
	public String[] getIdentifierColumns(Criteria subcriteria);
	
	/**
	 * Get the identifier type of this entity
	 */
	public Type getIdentifierType(Criteria subcriteria);
	
	public TypedValue getTypedIdentifierValue(Criteria subcriteria, Object value);
	
	public String generateSQLAlias();
}
/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.loader.entity;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.hibernate.HibernateException;
import org.hibernate.LockOptions;
import org.hibernate.engine.SessionFactoryImplementor;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.engine.LoadQueryInfluencers;
import org.hibernate.loader.OuterJoinLoader;
import org.hibernate.persister.entity.OuterJoinLoadable;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.type.Type;

public abstract class AbstractEntityLoader extends OuterJoinLoader 
		implements UniqueEntityLoader {

	protected static final Logger log = LoggerFactory.getLogger(EntityLoader.class);
	protected final OuterJoinLoadable persister;
	protected final Type uniqueKeyType;
	protected final String entityName;

	public AbstractEntityLoader(
			OuterJoinLoadable persister, 
			Type uniqueKeyType, 
			SessionFactoryImplementor factory, 
			LoadQueryInfluencers loadQueryInfluencers) {
		super( factory, loadQueryInfluencers );
		this.uniqueKeyType = uniqueKeyType;
		this.entityName = persister.getEntityName();
		this.persister = persister;
		
	}

	/**
	 * {@inheritDoc}
	 */
	public Object load(Serializable id, Object optionalObject, SessionImplementor session) {
		// this form is deprecated!
		return load( id, optionalObject, session, LockOptions.NONE );
	}

	/**
	 * {@inheritDoc}
	 */
	public Object load(Serializable id, Object optionalObject, SessionImplementor session, LockOptions lockOptions) {
		return load( session, id, optionalObject, id, lockOptions );
	}

	protected Object load(
			SessionImplementor session,
			Object id,
			Object optionalObject,
			Serializable optionalId,
			LockOptions lockOptions) {
		
		List list = loadEntity(
				session, 
				id, 
				uniqueKeyType, 
				optionalObject, 
				entityName, 
				optionalId, 
				persister,
				lockOptions
			);
		
		if ( list.size()==1 ) {
			return list.get(0);
		}
		else if ( list.size()==0 ) {
			return null;
		}
		else {
			if ( getCollectionOwners()!=null ) {
				return list.get(0);
			}
			else {
				throw new HibernateException(
						"More than one row with the given identifier was found: " +
						id +
						", for class: " +
						persister.getEntityName()
					);
			}
		}
		
	}

	protected Object getResultColumnOrRow(Object[] row, ResultTransformer transformer, ResultSet rs, SessionImplementor session) 
	throws SQLException, HibernateException {
		return row[row.length-1];
	}

	protected boolean isSingleRowLoader() {
		return true;
	}

}

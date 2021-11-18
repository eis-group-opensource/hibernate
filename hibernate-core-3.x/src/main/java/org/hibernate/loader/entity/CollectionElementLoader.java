/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.loader.entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.MappingException;
import org.hibernate.engine.SessionFactoryImplementor;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.engine.LoadQueryInfluencers;
import org.hibernate.loader.JoinWalker;
import org.hibernate.loader.OuterJoinLoader;
import org.hibernate.persister.collection.QueryableCollection;
import org.hibernate.persister.entity.OuterJoinLoadable;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.type.Type;
import org.hibernate.util.ArrayHelper;

/**
 * 
 *
 * @author Gavin King
 */
public class CollectionElementLoader extends OuterJoinLoader {
	
	private static final Logger log = LoggerFactory.getLogger(CollectionElementLoader.class);

	private final OuterJoinLoadable persister;
	private final Type keyType;
	private final Type indexType;
	private final String entityName;

	public CollectionElementLoader(
			QueryableCollection collectionPersister,
			SessionFactoryImplementor factory, 
			LoadQueryInfluencers loadQueryInfluencers) throws MappingException {
		super( factory, loadQueryInfluencers );

		this.keyType = collectionPersister.getKeyType();
		this.indexType = collectionPersister.getIndexType();
		this.persister = (OuterJoinLoadable) collectionPersister.getElementPersister();
		this.entityName = persister.getEntityName();
		
		JoinWalker walker = new EntityJoinWalker(
				persister, 
				ArrayHelper.join( 
						collectionPersister.getKeyColumnNames(), 
						collectionPersister.getIndexColumnNames()
					),
				1, 
				LockMode.NONE, 
				factory, 
				loadQueryInfluencers
			);
		initFromWalker( walker );

		postInstantiate();
		
		log.debug( "Static select for entity " + entityName + ": " + getSQLString() );

	}

	public Object loadElement(SessionImplementor session, Object key, Object index) 
	throws HibernateException {
		
		List list = loadEntity(
				session, 
				key,
				index,
				keyType, 
				indexType,
				persister
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
				throw new HibernateException("More than one row was found");
			}
		}
		
	}

	protected Object getResultColumnOrRow(
		Object[] row,
		ResultTransformer transformer,
		ResultSet rs, SessionImplementor session)
	throws SQLException, HibernateException {
		return row[row.length-1];
	}

	protected boolean isSingleRowLoader() {
		return true;
	}

}
/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.loader.collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.hibernate.MappingException;
import org.hibernate.engine.SessionFactoryImplementor;
import org.hibernate.engine.LoadQueryInfluencers;
import org.hibernate.loader.JoinWalker;
import org.hibernate.persister.collection.QueryableCollection;

/**
 * Loads a collection of values or a many-to-many association.
 * <br>
 * The collection persister must implement <tt>QueryableCOllection<tt>. For
 * other collections, create a customized subclass of <tt>Loader</tt>.
 *
 * @see OneToManyLoader
 * @author Gavin King
 */
public class BasicCollectionLoader extends CollectionLoader {

	private static final Logger log = LoggerFactory.getLogger(BasicCollectionLoader.class);

	public BasicCollectionLoader(
			QueryableCollection collectionPersister, 
			SessionFactoryImplementor session, 
			LoadQueryInfluencers loadQueryInfluencers) throws MappingException {
		this( collectionPersister, 1, session, loadQueryInfluencers );
	}

	public BasicCollectionLoader(
			QueryableCollection collectionPersister, 
			int batchSize, 
			SessionFactoryImplementor factory, 
			LoadQueryInfluencers loadQueryInfluencers) throws MappingException {
		this( collectionPersister, batchSize, null, factory, loadQueryInfluencers );
	}
	
	protected BasicCollectionLoader(
			QueryableCollection collectionPersister, 
			int batchSize, 
			String subquery, 
			SessionFactoryImplementor factory, 
			LoadQueryInfluencers loadQueryInfluencers) throws MappingException {
		super( collectionPersister, factory, loadQueryInfluencers );
		
		JoinWalker walker = new BasicCollectionJoinWalker(
				collectionPersister, 
				batchSize, 
				subquery, 
				factory, 
				loadQueryInfluencers
		);
		initFromWalker( walker );

		postInstantiate();

		log.debug( "Static select for collection " + collectionPersister.getRole() + ": " + getSQLString() );
	}
	
}

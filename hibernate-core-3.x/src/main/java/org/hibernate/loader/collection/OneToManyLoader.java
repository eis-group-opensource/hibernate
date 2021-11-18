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
 * Loads one-to-many associations<br>
 * <br>
 * The collection persister must implement <tt>QueryableCOllection<tt>. For
 * other collections, create a customized subclass of <tt>Loader</tt>.
 *
 * @see BasicCollectionLoader
 * @author Gavin King
 */
public class OneToManyLoader extends CollectionLoader {

	private static final Logger log = LoggerFactory.getLogger(OneToManyLoader.class);

	public OneToManyLoader(
			QueryableCollection oneToManyPersister, 
			SessionFactoryImplementor session, 
			LoadQueryInfluencers loadQueryInfluencers) throws MappingException {
		this( oneToManyPersister, 1, session, loadQueryInfluencers );
	}

	public OneToManyLoader(
			QueryableCollection oneToManyPersister, 
			int batchSize, 
			SessionFactoryImplementor factory, 
			LoadQueryInfluencers loadQueryInfluencers) throws MappingException {
		this( oneToManyPersister, batchSize, null, factory, loadQueryInfluencers );
	}

	public OneToManyLoader(
			QueryableCollection oneToManyPersister, 
			int batchSize, 
			String subquery, 
			SessionFactoryImplementor factory, 
			LoadQueryInfluencers loadQueryInfluencers) throws MappingException {
		super( oneToManyPersister, factory, loadQueryInfluencers );
		
		JoinWalker walker = new OneToManyJoinWalker(
				oneToManyPersister, 
				batchSize, 
				subquery, 
				factory, 
				loadQueryInfluencers
		);
		initFromWalker( walker );

		postInstantiate();
		log.debug( "Static select for one-to-many " + oneToManyPersister.getRole() + ": " + getSQLString() );
	}

}

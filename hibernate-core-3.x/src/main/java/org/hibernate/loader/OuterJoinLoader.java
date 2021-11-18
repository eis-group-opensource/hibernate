/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.loader;

import java.util.Map;

import org.hibernate.LockOptions;
import org.hibernate.LockMode;
import org.hibernate.dialect.Dialect;
import org.hibernate.engine.SessionFactoryImplementor;
import org.hibernate.engine.LoadQueryInfluencers;
import org.hibernate.persister.collection.CollectionPersister;
import org.hibernate.persister.entity.Loadable;
import org.hibernate.type.EntityType;

/**
 * Implements logic for walking a tree of associated classes.
 *
 * Generates an SQL select string containing all properties of those classes.
 * Tables are joined using an ANSI-style left outer join.
 *
 * @author Gavin King
 */
public abstract class OuterJoinLoader extends BasicLoader {

	protected Loadable[] persisters;
	protected CollectionPersister[] collectionPersisters;
	protected int[] collectionOwners;
	protected String[] aliases;
	private LockOptions lockOptions;
	protected LockMode[] lockModeArray;
	protected int[] owners;
	protected EntityType[] ownerAssociationTypes;
	protected String sql;
	protected String[] suffixes;
	protected String[] collectionSuffixes;

    private LoadQueryInfluencers loadQueryInfluencers;

	protected final Dialect getDialect() {
    	return getFactory().getDialect();
    }

	public OuterJoinLoader(
			SessionFactoryImplementor factory,
			LoadQueryInfluencers loadQueryInfluencers) {
		super( factory );
		this.loadQueryInfluencers = loadQueryInfluencers;
	}

	protected String[] getSuffixes() {
		return suffixes;
	}

	protected String[] getCollectionSuffixes() {
		return collectionSuffixes;
	}

	protected final String getSQLString() {
		return sql;
	}

	protected final Loadable[] getEntityPersisters() {
		return persisters;
	}

	protected int[] getOwners() {
		return owners;
	}

	protected EntityType[] getOwnerAssociationTypes() {
		return ownerAssociationTypes;
	}

	protected LockMode[] getLockModes(LockOptions lockOptions) {
		return lockModeArray;
	}

	protected LockOptions getLockOptions() {
		return lockOptions;
	}

	public LoadQueryInfluencers getLoadQueryInfluencers() {
		return loadQueryInfluencers;
	}

	protected final String[] getAliases() {
		return aliases;
	}

	protected final CollectionPersister[] getCollectionPersisters() {
		return collectionPersisters;
	}

	protected final int[] getCollectionOwners() {
		return collectionOwners;
	}

	protected void initFromWalker(JoinWalker walker) {
		persisters = walker.getPersisters();
		collectionPersisters = walker.getCollectionPersisters();
		ownerAssociationTypes = walker.getOwnerAssociationTypes();
		lockOptions = walker.getLockModeOptions();
		lockModeArray = walker.getLockModeArray();
		suffixes = walker.getSuffixes();
		collectionSuffixes = walker.getCollectionSuffixes();
		owners = walker.getOwners();
		collectionOwners = walker.getCollectionOwners();
		sql = walker.getSQLString();
		aliases = walker.getAliases();
	}

}

/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.loader.entity;

import org.hibernate.FetchMode;
import org.hibernate.LockMode;
import org.hibernate.MappingException;
import org.hibernate.LockOptions;
import org.hibernate.engine.CascadeStyle;
import org.hibernate.engine.CascadingAction;
import org.hibernate.engine.SessionFactoryImplementor;
import org.hibernate.engine.LoadQueryInfluencers;
import org.hibernate.loader.AbstractEntityJoinWalker;
import org.hibernate.persister.entity.OuterJoinLoadable;
import org.hibernate.type.AssociationType;
import org.hibernate.util.CollectionHelper;

public class CascadeEntityJoinWalker extends AbstractEntityJoinWalker {
	
	private final CascadingAction cascadeAction;

	public CascadeEntityJoinWalker(OuterJoinLoadable persister, CascadingAction action, SessionFactoryImplementor factory) 
	throws MappingException {
		super( persister, factory, LoadQueryInfluencers.NONE );
		this.cascadeAction = action;
		StringBuffer whereCondition = whereString( getAlias(), persister.getIdentifierColumnNames(), 1 )
				//include the discriminator and class-level where, but not filters
				.append( persister.filterFragment( getAlias(), CollectionHelper.EMPTY_MAP ) );
	
		initAll( whereCondition.toString(), "", LockOptions.READ );
	}

	protected boolean isJoinedFetchEnabled(AssociationType type, FetchMode config, CascadeStyle cascadeStyle) {
		return ( type.isEntityType() || type.isCollectionType() ) &&
				( cascadeStyle==null || cascadeStyle.doCascade(cascadeAction) );
	}

	protected boolean isTooManyCollections() {
		return countCollectionPersisters(associations)>0;
	}

	public String getComment() {
		return "load " + getPersister().getEntityName();
	}
	
}

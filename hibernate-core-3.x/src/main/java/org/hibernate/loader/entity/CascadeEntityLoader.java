/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.loader.entity;

import org.hibernate.MappingException;
import org.hibernate.engine.CascadingAction;
import org.hibernate.engine.SessionFactoryImplementor;
import org.hibernate.engine.LoadQueryInfluencers;
import org.hibernate.loader.JoinWalker;
import org.hibernate.persister.entity.OuterJoinLoadable;

public class CascadeEntityLoader extends AbstractEntityLoader {
	
	public CascadeEntityLoader(
			OuterJoinLoadable persister,
			CascadingAction action,
			SessionFactoryImplementor factory) throws MappingException {
		super(
				persister, 
				persister.getIdentifierType(), 
				factory,
				LoadQueryInfluencers.NONE
		);

		JoinWalker walker = new CascadeEntityJoinWalker(
				persister, 
				action,
				factory
		);
		initFromWalker( walker );

		postInstantiate();
		
		log.debug( "Static select for action " + action + " on entity " + entityName + ": " + getSQLString() );

	}

}

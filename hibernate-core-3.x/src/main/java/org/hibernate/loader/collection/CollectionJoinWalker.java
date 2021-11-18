/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.loader.collection;

import org.hibernate.engine.SessionFactoryImplementor;
import org.hibernate.engine.LoadQueryInfluencers;
import org.hibernate.loader.JoinWalker;
import org.hibernate.util.StringHelper;

/**
 * Superclass of walkers for collection initializers
 * 
 * @see CollectionLoader
 * @see OneToManyJoinWalker
 * @see BasicCollectionJoinWalker
 * @author Gavin King
 */
public abstract class CollectionJoinWalker extends JoinWalker {
	
	public CollectionJoinWalker(SessionFactoryImplementor factory, LoadQueryInfluencers loadQueryInfluencers) {
		super( factory, loadQueryInfluencers );
	}

	protected StringBuffer whereString(String alias, String[] columnNames, String subselect, int batchSize) {
		if (subselect==null) {
			return super.whereString(alias, columnNames, batchSize);
		}
		else {
			StringBuffer buf = new StringBuffer();
			if (columnNames.length>1) buf.append('(');
			buf.append( StringHelper.join(", ", StringHelper.qualify(alias, columnNames) ) );
			if (columnNames.length>1) buf.append(')');
			buf.append(" in ")
				.append('(')
				.append(subselect) 
				.append(')');
			return buf;
		}
	}
}

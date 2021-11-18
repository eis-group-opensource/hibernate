/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.engine.loading;

import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * {@inheritDoc}
 *
 * @author Steve Ebersole
 */
public class EntityLoadContext {
	private static final Logger log = LoggerFactory.getLogger( EntityLoadContext.class );

	private final LoadContexts loadContexts;
	private final ResultSet resultSet;
	private final List hydratingEntities = new ArrayList( 20 ); // todo : need map? the prob is a proper key, right?

	public EntityLoadContext(LoadContexts loadContexts, ResultSet resultSet) {
		this.loadContexts = loadContexts;
		this.resultSet = resultSet;
	}

	void cleanup() {
		if ( !hydratingEntities.isEmpty() ) {
			log.warn( "On EntityLoadContext#clear, hydratingEntities contained [" + hydratingEntities.size() + "] entries" );
		}
		hydratingEntities.clear();
	}


	public String toString() {
		return super.toString() + "<rs=" + resultSet + ">";
	}

}

/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.event.def;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.event.AutoFlushEvent;
import org.hibernate.event.AutoFlushEventListener;
import org.hibernate.event.EventSource;

/**
 * Defines the default flush event listeners used by hibernate for 
 * flushing session state in response to generated auto-flush events.
 *
 * @author Steve Ebersole
 */
public class DefaultAutoFlushEventListener extends AbstractFlushingEventListener implements AutoFlushEventListener {

	private static final Logger log = LoggerFactory.getLogger(DefaultAutoFlushEventListener.class);

    /** Handle the given auto-flush event.
     *
     * @param event The auto-flush event to be handled.
     * @throws HibernateException
     */
	public void onAutoFlush(AutoFlushEvent event) throws HibernateException {

		final EventSource source = event.getSession();
		
		if ( flushMightBeNeeded(source) ) {

			final int oldSize = source.getActionQueue().numberOfCollectionRemovals();

			flushEverythingToExecutions(event);
			
			if ( flushIsReallyNeeded(event, source) ) {

				log.trace("Need to execute flush");

				performExecutions(source);
				postFlush(source);
				// note: performExecutions() clears all collectionXxxxtion 
				// collections (the collection actions) in the session

				if ( source.getFactory().getStatistics().isStatisticsEnabled() ) {
					source.getFactory().getStatisticsImplementor().flush();
				}
				
			}
			else {

				log.trace("Dont need to execute flush");
				source.getActionQueue().clearFromFlushNeededCheck( oldSize );
			}
			
			event.setFlushRequired( flushIsReallyNeeded( event, source ) );

		}

	}

	private boolean flushIsReallyNeeded(AutoFlushEvent event, final EventSource source) {
		return source.getActionQueue()
				.areTablesToBeUpdated( event.getQuerySpaces() ) || 
						source.getFlushMode()==FlushMode.ALWAYS;
	}

	private boolean flushMightBeNeeded(final EventSource source) {
		return !source.getFlushMode().lessThan(FlushMode.AUTO) && 
				source.getDontFlushFromFind() == 0 &&
				( source.getPersistenceContext().getEntityEntries().size() > 0 ||
						source.getPersistenceContext().getCollectionEntries().size() > 0 );
	}

}

/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.event.def;

import org.hibernate.AssertionFailure;
import org.hibernate.LockMode;
import org.hibernate.action.EntityIncrementVersionProcess;
import org.hibernate.action.EntityVerifyVersionProcess;
import org.hibernate.classic.Lifecycle;
import org.hibernate.engine.EntityEntry;
import org.hibernate.event.PostLoadEvent;
import org.hibernate.event.PostLoadEventListener;
import org.hibernate.persister.entity.EntityPersister;

/**
 * We do 2 things here:<ul>
 * <li>Call {@link Lifecycle} interface if necessary</li>
 * <li>Perform needed {@link EntityEntry#getLockMode()} related processing</li>
 * </ul>
 *
 * @author Gavin King
 * @author Steve Ebersole
 */
public class DefaultPostLoadEventListener implements PostLoadEventListener {
	
	public void onPostLoad(PostLoadEvent event) {
		final Object entity = event.getEntity();
		final EntityEntry entry = event.getSession().getPersistenceContext().getEntry( entity );
		if ( entry == null ) {
			throw new AssertionFailure( "possible non-threadsafe access to the session" );
		}

		final LockMode lockMode = entry.getLockMode();
		if ( LockMode.PESSIMISTIC_FORCE_INCREMENT.equals( lockMode ) ) {
			final EntityPersister persister = entry.getPersister();
			Object nextVersion = persister.forceVersionIncrement(
					entry.getId(), entry.getVersion(), event.getSession()
			);
			entry.forceLocked( entity, nextVersion );
		}
		else if ( LockMode.OPTIMISTIC_FORCE_INCREMENT.equals( lockMode ) ) {
			EntityIncrementVersionProcess incrementVersion = new EntityIncrementVersionProcess( entity, entry );
			event.getSession().getActionQueue().registerProcess( incrementVersion );
		}
		else if ( LockMode.OPTIMISTIC.equals( lockMode ) ) {
			EntityVerifyVersionProcess verifyVersion = new EntityVerifyVersionProcess( entity, entry );
			event.getSession().getActionQueue().registerProcess( verifyVersion );
		}

		if ( event.getPersister().implementsLifecycle( event.getSession().getEntityMode() ) ) {
			//log.debug( "calling onLoad()" );
			( ( Lifecycle ) event.getEntity() ).onLoad( event.getSession(), event.getId() );
		}

	}
}

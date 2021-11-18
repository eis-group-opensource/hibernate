/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.event;

import org.hibernate.collection.PersistentCollection;
import org.hibernate.persister.collection.CollectionPersister;

/**
 * An event that occurs after a collection is removed
 *
 * @author Gail Badner
 */
public class PostCollectionRemoveEvent extends AbstractCollectionEvent {

	public PostCollectionRemoveEvent(CollectionPersister collectionPersister,
									 PersistentCollection collection,
									 EventSource source,
									 Object loadedOwner ) {
		super( collectionPersister, collection, source,
				loadedOwner,
				getOwnerIdOrNull( loadedOwner, source ) );
	}
}

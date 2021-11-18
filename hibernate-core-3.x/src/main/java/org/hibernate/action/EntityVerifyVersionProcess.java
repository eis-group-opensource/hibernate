/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.action;

import org.hibernate.OptimisticLockException;
import org.hibernate.engine.EntityEntry;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.persister.entity.EntityPersister;
import org.hibernate.pretty.MessageHelper;

/**
 * Verify/Increment the entity version
 *
 * @author Scott Marlow
 */
public class EntityVerifyVersionProcess implements BeforeTransactionCompletionProcess {
	/** @noinspection FieldCanBeLocal,UnusedDeclaration */
	private final Object object;
	private final EntityEntry entry;

	public EntityVerifyVersionProcess(Object object, EntityEntry entry) {
		this.object = object;
		this.entry = entry;
	}

	/**
	 * Perform whatever processing is encapsulated here before completion of the transaction.
	 *
	 * @param session The session on which the transaction is preparing to complete.
	 */
	public void doBeforeTransactionCompletion(SessionImplementor session) {
		final EntityPersister persister = entry.getPersister();

		Object latestVersion = persister.getCurrentVersion( entry.getId(), session );
		if ( !entry.getVersion().equals( latestVersion ) ) {
			throw new OptimisticLockException(
					"Newer version [" + latestVersion +
							"] of entity [" + MessageHelper.infoString( entry.getEntityName(), entry.getId() ) +
							"] found in database"
			);
		}
	}
}
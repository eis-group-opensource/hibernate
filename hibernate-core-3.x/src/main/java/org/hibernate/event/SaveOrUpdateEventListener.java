/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.event;

import java.io.Serializable;

import org.hibernate.HibernateException;

/**
 * Defines the contract for handling of update events generated from a session.
 *
 * @author Steve Ebersole
 */
public interface SaveOrUpdateEventListener extends Serializable {

    /** 
     * Handle the given update event.
     *
     * @param event The update event to be handled.
     * @throws HibernateException
     */
	public void onSaveOrUpdate(SaveOrUpdateEvent event) throws HibernateException;

}

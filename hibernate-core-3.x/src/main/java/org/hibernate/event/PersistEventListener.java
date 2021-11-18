/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.event;

import java.io.Serializable;
import java.util.Map;

import org.hibernate.HibernateException;

/**
 * Defines the contract for handling of create events generated from a session.
 *
 * @author Gavin King
 */
public interface PersistEventListener extends Serializable {

    /** 
     * Handle the given create event.
     *
     * @param event The create event to be handled.
     * @throws HibernateException
     */
	public void onPersist(PersistEvent event) throws HibernateException;

    /** 
     * Handle the given create event.
     *
     * @param event The create event to be handled.
     * @throws HibernateException
     */
	public void onPersist(PersistEvent event, Map createdAlready) throws HibernateException;

}

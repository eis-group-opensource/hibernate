/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.event;

import org.hibernate.HibernateException;

import java.io.Serializable;

/**
 * Defines the contract for handling of session auto-flush events.
 *
 * @author Steve Ebersole
 */
public interface AutoFlushEventListener extends Serializable {

    /** Handle the given auto-flush event.
     *
     * @param event The auto-flush event to be handled.
     * @throws HibernateException
     */
	public void onAutoFlush(AutoFlushEvent event) throws HibernateException;
}

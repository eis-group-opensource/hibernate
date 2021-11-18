/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.event;

import org.hibernate.HibernateException;

import java.io.Serializable;

/**
 * Defines the contract for handling of replicate events generated from a session.
 *
 * @author Steve Ebersole
 */
public interface ReplicateEventListener extends Serializable {

    /** Handle the given replicate event.
     *
     * @param event The replicate event to be handled.
     * @throws HibernateException
     */
	public void onReplicate(ReplicateEvent event) throws HibernateException;

}

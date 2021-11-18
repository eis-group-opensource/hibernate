/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.event;

import java.io.Serializable;

/**
 * Called before updating a collection
 *
 * @author Gail Badner
 */
public interface PreCollectionUpdateEventListener extends Serializable {
	public void onPreUpdateCollection(PreCollectionUpdateEvent event);
}

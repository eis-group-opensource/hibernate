/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.proxy;

import java.io.Serializable;

/**
 * Delegate to handle the scenario of an entity not found by a specified id.
 *
 * @author Steve Ebersole
 */
public interface EntityNotFoundDelegate {
	public void handleEntityNotFound(String entityName, Serializable id);
	public boolean isEntityNotFoundException(RuntimeException exception);
}

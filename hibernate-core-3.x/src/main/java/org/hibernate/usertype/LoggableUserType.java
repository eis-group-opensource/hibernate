/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.usertype;

import org.hibernate.engine.SessionFactoryImplementor;

/**
 * Marker interface for user types which want to perform custom
 * logging of their corresponding values
 *
 * @author Steve Ebersole
 */
public interface LoggableUserType {
	/**
	 * Generate a loggable string representation of the collection (value).
	 *
	 * @param value The collection to be logged; guarenteed to be non-null and initialized.
	 * @param factory The factory.
	 * @return The loggable string representation.
	 */
	public String toLoggableString(Object value, SessionFactoryImplementor factory);
}

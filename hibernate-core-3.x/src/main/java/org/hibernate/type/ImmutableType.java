/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.type;

import java.util.Map;

import org.hibernate.EntityMode;
import org.hibernate.HibernateException;
import org.hibernate.engine.SessionFactoryImplementor;
import org.hibernate.engine.SessionImplementor;

/**
 * Superclass of nullable immutable types.
 * @author Gavin King
 *
 * @deprecated see http://opensource.atlassian.com/projects/hibernate/browse/HHH-5138
 */
public abstract class ImmutableType extends NullableType {

	public final Object deepCopy(Object value, EntityMode entityMode, SessionFactoryImplementor factory) {
		return value;
	}

	public final boolean isMutable() {
		return false;
	}

	public Object replace(
		Object original,
		Object target,
		SessionImplementor session,
		Object owner, 
		Map copyCache)
	throws HibernateException {
		return original;
	}


}

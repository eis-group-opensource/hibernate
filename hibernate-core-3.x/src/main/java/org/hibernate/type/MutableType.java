/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.type;

import java.util.Map;

import org.hibernate.EntityMode;
import org.hibernate.HibernateException;
import org.hibernate.engine.SessionFactoryImplementor;
import org.hibernate.engine.SessionImplementor;

/**
 * Superclass for mutable nullable types
 * @author Gavin King
 *
 * @deprecated see http://opensource.atlassian.com/projects/hibernate/browse/HHH-5138
 */
public abstract class MutableType extends NullableType {

	public final boolean isMutable() {
		return true;
	}

	protected abstract Object deepCopyNotNull(Object value) throws HibernateException;

	public final Object deepCopy(Object value, EntityMode entityMode, SessionFactoryImplementor factory) 
	throws HibernateException {
		return (value==null) ? null : deepCopyNotNull(value);
	}

	public Object replace(
		Object original,
		Object target,
		SessionImplementor session,
		Object owner, 
		Map copyCache)
	throws HibernateException {
		if ( isEqual( original, target, session.getEntityMode() ) ) return original;
		return deepCopy( original, session.getEntityMode(), session.getFactory() );
	}

}

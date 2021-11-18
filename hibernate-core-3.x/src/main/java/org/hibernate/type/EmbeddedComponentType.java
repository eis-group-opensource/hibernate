/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.type;

import java.lang.reflect.Method;

import org.hibernate.EntityMode;
import org.hibernate.HibernateException;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.tuple.component.ComponentTuplizer;
import org.hibernate.tuple.component.ComponentMetamodel;

/**
 * @author Gavin King
 */
public class EmbeddedComponentType extends ComponentType {

	public boolean isEmbedded() {
		return true;
	}

	public EmbeddedComponentType(ComponentMetamodel metamodel) {
		super( metamodel );
	}

	public boolean isMethodOf(Method method) {
		return ( ( ComponentTuplizer ) tuplizerMapping.getTuplizer(EntityMode.POJO) ).isMethodOf(method);
	}

	public Object instantiate(Object parent, SessionImplementor session)
	throws HibernateException {
		final boolean useParent = parent!=null &&
		                          //TODO: Yuck! This is not quite good enough, it's a quick
		                          //hack around the problem of having a to-one association
		                          //that refers to an embedded component:
		                          super.getReturnedClass().isInstance(parent);

		return useParent ? parent : super.instantiate(parent, session);
	}
}

/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.intercept.javassist;

import org.hibernate.intercept.FieldInterceptor;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.bytecode.javassist.FieldHandled;

import java.util.Set;

/**
 * @author Steve Ebersole
 */
public class JavassistHelper {
	private JavassistHelper() {
	}

	public static FieldInterceptor extractFieldInterceptor(Object entity) {
		return ( FieldInterceptor ) ( ( FieldHandled ) entity ).getFieldHandler();
	}

	public static FieldInterceptor injectFieldInterceptor(
			Object entity,
	        String entityName,
	        Set uninitializedFieldNames,
	        SessionImplementor session) {
		FieldInterceptorImpl fieldInterceptor = new FieldInterceptorImpl( session, uninitializedFieldNames, entityName );
		( ( FieldHandled ) entity ).setFieldHandler( fieldInterceptor );
		return fieldInterceptor;
	}
}

/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.proxy.dom4j;

import org.hibernate.engine.SessionImplementor;
import org.hibernate.proxy.AbstractLazyInitializer;
import org.dom4j.Element;

import java.io.Serializable;

/**
 * Lazy initializer for "dom4j" entity representations.
 *
 * @author Steve Ebersole
 */
public class Dom4jLazyInitializer extends AbstractLazyInitializer implements Serializable {

	Dom4jLazyInitializer(String entityName, Serializable id, SessionImplementor session) {
		super(entityName, id, session);
	}

	public Element getElement() {
		return (Element) getImplementation();
	}

	public Class getPersistentClass() {
		throw new UnsupportedOperationException("dom4j entity representation");
	}

}

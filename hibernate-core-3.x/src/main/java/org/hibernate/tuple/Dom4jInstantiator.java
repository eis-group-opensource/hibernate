/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.tuple;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;

import org.dom4j.Element;
import org.hibernate.util.XMLHelper;
import org.hibernate.mapping.PersistentClass;
import org.hibernate.mapping.Component;

/**
 * Performs "instantiation" based on DOM4J elements.
 */
public class Dom4jInstantiator implements Instantiator {
	private final String nodeName;
	private final HashSet isInstanceNodeNames = new HashSet();

	public Dom4jInstantiator(Component component) {
		this.nodeName = component.getNodeName();
		isInstanceNodeNames.add( nodeName );
	}

	public Dom4jInstantiator(PersistentClass mappingInfo) {
		this.nodeName = mappingInfo.getNodeName();
		isInstanceNodeNames.add( nodeName );

		if ( mappingInfo.hasSubclasses() ) {
			Iterator itr = mappingInfo.getSubclassClosureIterator();
			while ( itr.hasNext() ) {
				final PersistentClass subclassInfo = ( PersistentClass ) itr.next();
				isInstanceNodeNames.add( subclassInfo.getNodeName() );
			}
		}
	}
	
	public Object instantiate(Serializable id) {
		return instantiate();
	}
	
	public Object instantiate() {
		return XMLHelper.generateDom4jElement( nodeName );
	}

	public boolean isInstance(Object object) {
		if ( object instanceof Element ) {
			return isInstanceNodeNames.contains( ( ( Element ) object ).getName() );
		}
		else {
			return false;
		}
	}
}
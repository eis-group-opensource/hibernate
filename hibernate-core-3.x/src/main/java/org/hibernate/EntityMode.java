/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate;

import java.util.Map;
import java.util.HashMap;
import java.io.Serializable;

/**
 * Defines the representation modes available for entities.
 *
 * @author Steve Ebersole
 */
public class EntityMode implements Serializable {

	private static final Map INSTANCES = new HashMap();

	public static final EntityMode POJO = new EntityMode( "pojo" );
	public static final EntityMode DOM4J = new EntityMode( "dom4j" );
	public static final EntityMode MAP = new EntityMode( "dynamic-map" );

	static {
		INSTANCES.put( POJO.name, POJO );
		INSTANCES.put( DOM4J.name, DOM4J );
		INSTANCES.put( MAP.name, MAP );
	}

	private final String name;

	public EntityMode(String name) {
		this.name = name;
	}

	public String toString() {
		return name;
	}

	private Object readResolve() {
		return INSTANCES.get( name );
	}

	public static EntityMode parse(String name) {
		EntityMode rtn = ( EntityMode ) INSTANCES.get( name );
		if ( rtn == null ) {
			// default is POJO
			rtn = POJO;
		}
		return rtn;
	}
}

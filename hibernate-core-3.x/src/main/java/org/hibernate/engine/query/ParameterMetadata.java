/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.engine.query;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.hibernate.QueryParameterException;
import org.hibernate.type.Type;

/**
 * Encapsulates metadata about parameters encountered within a query.
 *
 * @author Steve Ebersole
 */
public class ParameterMetadata implements Serializable {

	private static final OrdinalParameterDescriptor[] EMPTY_ORDINALS = new OrdinalParameterDescriptor[0];

	private final OrdinalParameterDescriptor[] ordinalDescriptors;
	private final Map namedDescriptorMap;

	/**
	 * Instantiates a ParameterMetadata container.
	 *
	 * @param ordinalDescriptors
	 * @param namedDescriptorMap
	 */
	public ParameterMetadata(OrdinalParameterDescriptor[] ordinalDescriptors, Map namedDescriptorMap) {
		if ( ordinalDescriptors == null ) {
			this.ordinalDescriptors = EMPTY_ORDINALS;
		}
		else {
			OrdinalParameterDescriptor[] copy = new OrdinalParameterDescriptor[ ordinalDescriptors.length ];
			System.arraycopy( ordinalDescriptors, 0, copy, 0, ordinalDescriptors.length );
			this.ordinalDescriptors = copy;
		}
		if ( namedDescriptorMap == null ) {
			this.namedDescriptorMap = java.util.Collections.EMPTY_MAP;
		}
		else {
			int size = ( int ) ( ( namedDescriptorMap.size() / .75 ) + 1 );
			Map copy = new HashMap( size );
			copy.putAll( namedDescriptorMap );
			this.namedDescriptorMap = java.util.Collections.unmodifiableMap( copy );
		}
	}

	public int getOrdinalParameterCount() {
		return ordinalDescriptors.length;
	}

	public OrdinalParameterDescriptor getOrdinalParameterDescriptor(int position) {
		if ( position < 1 || position > ordinalDescriptors.length ) {
			String error = "Position beyond number of declared ordinal parameters. " +
					"Remember that ordinal parameters are 1-based! Position: " + position;
			throw new QueryParameterException( error );
		}
		return ordinalDescriptors[position - 1];
	}

	public Type getOrdinalParameterExpectedType(int position) {
		return getOrdinalParameterDescriptor( position ).getExpectedType();
	}

	public int getOrdinalParameterSourceLocation(int position) {
		return getOrdinalParameterDescriptor( position ).getSourceLocation();
	}

	public Set getNamedParameterNames() {
		return namedDescriptorMap.keySet();
	}

	public NamedParameterDescriptor getNamedParameterDescriptor(String name) {
		NamedParameterDescriptor meta = ( NamedParameterDescriptor ) namedDescriptorMap.get( name );
		if ( meta == null ) {
			throw new QueryParameterException( "could not locate named parameter [" + name + "]" );
		}
		return meta;
	}

	public Type getNamedParameterExpectedType(String name) {
		return getNamedParameterDescriptor( name ).getExpectedType();
	}

	public int[] getNamedParameterSourceLocations(String name) {
		return getNamedParameterDescriptor( name ).getSourceLocations();
	}

}

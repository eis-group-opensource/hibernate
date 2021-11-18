/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.mapping;

import java.util.LinkedHashSet;

/**
 * A fetch profile allows a user to dynamically modify the fetching
 * strategy used for particular associations at runtime, whereas that
 * information was historically only statically defined in the metadata.
 *
 * @author Steve Ebersole
 */
public class FetchProfile {
	private final String name;
	private LinkedHashSet fetches = new LinkedHashSet();

	public FetchProfile(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public LinkedHashSet getFetches() {
		return fetches;
	}

	public void addFetch(String entity, String association, String style) {
		fetches.add( new Fetch( entity, association, style ) );
	}

	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( o == null || getClass() != o.getClass() ) {
			return false;
		}

		FetchProfile that = ( FetchProfile ) o;

		return name.equals( that.name );

	}

	public int hashCode() {
		return name.hashCode();
	}


	/**
	 * Defines an individual association fetch within the given profile.
	 */
	public static class Fetch {
		private final String entity;
		private final String association;
		private final String style;

		public Fetch(String entity, String association, String style) {
			this.entity = entity;
			this.association = association;
			this.style = style;
		}

		public String getEntity() {
			return entity;
		}

		public String getAssociation() {
			return association;
		}

		public String getStyle() {
			return style;
		}
	}
}

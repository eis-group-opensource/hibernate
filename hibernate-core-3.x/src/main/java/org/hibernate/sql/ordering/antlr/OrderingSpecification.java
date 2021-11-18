/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.sql.ordering.antlr;

/**
 * Models an ordering specification (<tt>ASCENDING</tt> or <tt>DESCENDING</tt>) within a {@link SortSpecification}.
 *
 * @author Steve Ebersole
 */
public class OrderingSpecification extends NodeSupport {
	public static class Ordering {
		public static final Ordering ASCENDING = new Ordering( "asc" );
		public static final Ordering DESCENDING = new Ordering( "desc" );

		private final String name;

		private Ordering(String name) {
			this.name = name;
		}
	}

	private boolean resolved;
	private Ordering ordering;

	public Ordering getOrdering() {
		if ( !resolved ) {
			ordering = resolve( getText() );
			resolved = true;
		}
		return ordering;
	}

	private static Ordering resolve(String text) {
		if ( Ordering.ASCENDING.name.equals( text ) ) {
			return Ordering.ASCENDING;
		}
		else if ( Ordering.DESCENDING.name.equals( text ) ) {
			return Ordering.DESCENDING;
		}
		else {
			throw new IllegalStateException( "Unknown ordering [" + text + "]" );
		}
	}

	public String getRenderableText() {
		return getOrdering().name;
	}
}

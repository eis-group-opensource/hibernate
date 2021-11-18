/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.engine.profile;

/**
 * Models an individual fetch within a profile.
 *
 * @author Steve Ebersole
 */
public class Fetch {
	private final Association association;
	private final Style style;

	public Fetch(Association association, Style style) {
		this.association = association;
		this.style = style;
	}

	public Association getAssociation() {
		return association;
	}

	public Style getStyle() {
		return style;
	}

	/**
	 * The type or style of fetch.  For the moment we limit this to
	 * join and select, though technically subselect would be valid
	 * here as as well; however, to support subselect here would
	 * require major changes to the subselect loading code (which is
	 * needed for other things as well anyway).
	 */
	public static class Style {
		public static final Style JOIN = new Style( "join" );
		public static final Style SELECT = new Style( "select" );

		private final String name;

		private Style(String name) {
			this.name = name;
		}

		public String toString() {
			return name;
		}

		public static Style parse(String name) {
			if ( SELECT.name.equals( name ) ) {
				return SELECT;
			}
			else {
				// the default...
				return JOIN;
			}
		}
	}

	public String toString() {
		return "Fetch[" + style + "{" + association.getRole() + "}]";
	}
}

/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.jdbc.util;

/**
 * Represents the the understood types or styles of formatting. 
 *
 * @author Steve Ebersole
 */
public class FormatStyle {
	public static final FormatStyle BASIC = new FormatStyle( "basic", new BasicFormatterImpl() );
	public static final FormatStyle DDL = new FormatStyle( "ddl", new DDLFormatterImpl() );
	public static final FormatStyle NONE = new FormatStyle( "none", new NoFormatImpl() );

	private final String name;
	private final Formatter formatter;

	private FormatStyle(String name, Formatter formatter) {
		this.name = name;
		this.formatter = formatter;
	}

	public String getName() {
		return name;
	}

	public Formatter getFormatter() {
		return formatter;
	}

	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( o == null || getClass() != o.getClass() ) {
			return false;
		}

		FormatStyle that = ( FormatStyle ) o;

		return name.equals( that.name );

	}

	public int hashCode() {
		return name.hashCode();
	}

	private static class NoFormatImpl implements Formatter {
		public String format(String source) {
			return source;
		}
	}
}

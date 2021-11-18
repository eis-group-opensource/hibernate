/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.type;

import java.sql.Types;

/**
 * <tt>text</tt>: A type that maps an SQL LONGVARCHAR to a Java String.
 *
 * @author Gavin King, Bertrand Renuart
 */
public class TextType extends AbstractLongStringType {

	public int sqlType() {
		return Types.LONGVARCHAR;
	}

	public String getName() { return "text"; }

}








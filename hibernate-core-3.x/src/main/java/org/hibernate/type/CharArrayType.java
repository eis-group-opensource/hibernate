/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.type;

/**
 * put char[] into VARCHAR
 * @author Emmanuel Bernard
 */
public class CharArrayType extends AbstractCharArrayType {

	protected Object toExternalFormat(char[] chars) {
		return chars;
	}

	protected char[] toInternalFormat(Object chars) {
		return (char[]) chars;
	}

	public Class getReturnedClass() {
		return char[].class;
	}

	public String getName() { return "characters"; }
}

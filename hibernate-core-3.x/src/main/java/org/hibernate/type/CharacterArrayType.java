/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.type;

import org.hibernate.HibernateException;

/**
 * Bridge Character[] and VARCHAR
 * @author Emmanuel Bernard
 */
public class CharacterArrayType extends AbstractCharArrayType {
	protected Object toExternalFormat(char[] chars) {
		if (chars == null) return null;
		Character[] characters = new Character[chars.length];
		for (int i = 0 ; i < chars.length ; i++) {
			characters[i] = new Character( chars[i] );
		}
		return characters;
	}

	protected char[] toInternalFormat(Object value) {
		if (value == null) return null;
		Character[] characters = (Character[]) value;
		char[] chars = new char[characters.length];
		for (int i = 0 ; i < characters.length ; i++) {
			if (characters[i] == null)
				throw new HibernateException("Unable to store an Character[] when one of its element is null");
			chars[i] = characters[i].charValue();
		}
		return chars;
	}

	public Class getReturnedClass() {
		return Character[].class;
	}

	public String getName() { return "wrapper-characters"; }
}

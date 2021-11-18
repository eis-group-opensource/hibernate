/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.type;

import org.hibernate.HibernateException;

/**
 * @author Emmanuel Bernard
 */
public class WrapperBinaryType extends AbstractBynaryType {
	protected Object toExternalFormat(byte[] bytes) {
		if (bytes == null) return null;
		int length = bytes.length;
		Byte[] result = new Byte[length];
		for ( int index = 0; index < length ; index++ ) {
			result[index] = new Byte( bytes[index] );
		}
		return result;
	}

	protected byte[] toInternalFormat(Object val) {
		if (val == null) return null;
		Byte[] bytes = (Byte[]) val;
		int length = bytes.length;
		byte[] result = new byte[length];
		for ( int i = 0; i < length ; i++ ) {
			if (bytes[i] == null)
				throw new HibernateException("Unable to store an Byte[] when one of its element is null");
			result[i] = bytes[i].byteValue();
		}
		return result;
	}

	public Class getReturnedClass() {
		return Byte[].class;
	}

	public String getName() {
		//TODO find a decent name before documenting
		return "wrapper-binary";
	}
}

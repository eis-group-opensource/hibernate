/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.type;

/**
 * An abstract type for mapping long binary SQL types to Java byte[].
 *
 * @author Gail Badner
 *
 * @deprecated see http://opensource.atlassian.com/projects/hibernate/browse/HHH-5138
 */
public abstract class AbstractLongBinaryType extends AbstractBynaryType {

	public Class getReturnedClass() {
		return byte[].class;
	}

	protected Object toExternalFormat(byte[] bytes) {
		return bytes;
	}

	protected byte[] toInternalFormat(Object bytes) {
		return ( byte[] ) bytes;
	}
}
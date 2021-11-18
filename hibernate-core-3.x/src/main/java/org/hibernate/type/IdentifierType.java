/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.type;

/**
 * A <tt>Type</tt> that may be used as an identifier.
 * @author Gavin King
 */
public interface IdentifierType extends Type {

	/**
	 * Convert the value from the mapping file to a Java object.
	 * @param xml the value of <tt>discriminator-value</tt> or <tt>unsaved-value</tt> attribute
	 * @return Object
	 * @throws Exception
	 */
	public Object stringToObject(String xml) throws Exception;

}







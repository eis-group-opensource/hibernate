/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.usertype;

/**
 * A custom type that may function as an identifier or
 * discriminator type, or may be marshalled to and from
 * an XML document
 * 
 * @author Gavin King
 */
public interface EnhancedUserType extends UserType {
	/**
	 * Return an SQL literal representation of the value
	 */
	public String objectToSQLString(Object value);
	
	/**
	 * Return a string representation of this value, as it
	 * should appear in an XML document
	 */
	public String toXMLString(Object value);
	/**
	 * Parse a string representation of this value, as it
	 * appears in an XML document
	 */
	public Object fromXMLString(String xmlValue);
}

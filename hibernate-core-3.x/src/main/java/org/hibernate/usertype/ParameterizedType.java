/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.usertype;

import java.util.Properties;

/**
 * Support for parameterizable types. A UserType or CustomUserType may be
 * made parameterizable by implementing this interface. Parameters for a
 * type may be set by using a nested type element for the property element
 * in the mapping file, or by defining a typedef.
 *
 * @author Michael Gloegl
 */
public interface ParameterizedType {

	/**
	 * Gets called by Hibernate to pass the configured type parameters to
	 * the implementation.
	 */
	public void setParameterValues(Properties parameters);
}
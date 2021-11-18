/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.mapping;

import java.io.Serializable;
import java.util.Properties;

/**
 * Placeholder for typedef information
 */
public class TypeDef implements Serializable {

	private String typeClass;
	private Properties parameters;

	public TypeDef(String typeClass, Properties parameters) {
		this.typeClass = typeClass;
		this.parameters = parameters;
	}

	public Properties getParameters() {
		return parameters;
	}
	public String getTypeClass() {
		return typeClass;
	}

}

/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.engine.query;

import org.hibernate.type.Type;

import java.io.Serializable;

/**
 * @author Steve Ebersole
 */
public class ReturnMetadata implements Serializable {
	private final String[] returnAliases;
	private final Type[] returnTypes;

	public ReturnMetadata(String[] returnAliases, Type[] returnTypes) {
		this.returnAliases = returnAliases;
		this.returnTypes = returnTypes;
	}

	public String[] getReturnAliases() {
		return returnAliases;
	}

	public Type[] getReturnTypes() {
		return returnTypes;
	}
}

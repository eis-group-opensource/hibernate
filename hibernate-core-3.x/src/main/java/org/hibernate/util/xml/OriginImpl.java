/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.util.xml;

import java.io.Serializable;

/**
 * Basic implementation of {@link Origin}
 *
 * @author Steve Ebersole
 */
public class OriginImpl implements Origin, Serializable {
	private final String type;
	private final String name;

	public OriginImpl(String type, String name) {
		this.type = type;
		this.name = name;
	}

	/**
	 * {@inheritDoc}
	 */
	public String getType() {
		return type;
	}

	/**
	 * {@inheritDoc}
	 */
	public String getName() {
		return name;
	}
}

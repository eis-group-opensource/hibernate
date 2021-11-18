/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.engine.query;

import org.hibernate.type.Type;

import java.io.Serializable;

/**
 * @author Steve Ebersole
 */
public class OrdinalParameterDescriptor implements Serializable {
	private final int ordinalPosition;
	private final Type expectedType;
	private final int sourceLocation;

	public OrdinalParameterDescriptor(int ordinalPosition, Type expectedType, int sourceLocation) {
		this.ordinalPosition = ordinalPosition;
		this.expectedType = expectedType;
		this.sourceLocation = sourceLocation;
	}

	public int getOrdinalPosition() {
		return ordinalPosition;
	}

	public Type getExpectedType() {
		return expectedType;
	}

	public int getSourceLocation() {
		return sourceLocation;
	}
}

/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.param;

import org.hibernate.type.Type;

/**
 * Convenience base class for explicitly defined query parameters.
 *
 * @author Steve Ebersole
 */
public abstract class AbstractExplicitParameterSpecification implements ExplicitParameterSpecification {
	private final int sourceLine;
	private final int sourceColumn;
	private Type expectedType;

	/**
	 * Constructs an AbstractExplicitParameterSpecification.
	 *
	 * @param sourceLine See {@link #getSourceLine()}
	 * @param sourceColumn See {@link #getSourceColumn()} 
	 */
	protected AbstractExplicitParameterSpecification(int sourceLine, int sourceColumn) {
		this.sourceLine = sourceLine;
		this.sourceColumn = sourceColumn;
	}

	/**
	 * {@inheritDoc}
	 */
	public int getSourceLine() {
		return sourceLine;
	}

	/**
	 * {@inheritDoc}
	 */
	public int getSourceColumn() {
		return sourceColumn;
	}

	/**
	 * {@inheritDoc}
	 */
	public Type getExpectedType() {
		return expectedType;
	}

	/**
	 * {@inheritDoc}
	 */
	public void setExpectedType(Type expectedType) {
		this.expectedType = expectedType;
	}
}

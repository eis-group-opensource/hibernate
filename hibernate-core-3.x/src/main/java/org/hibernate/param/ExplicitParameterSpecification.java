/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.param;

/**
 * An additional contract for parameters which originate from parameters explicitly encountered in the source statement
 * (HQL or native-SQL).
 *
 * @author Steve Ebersole
 */
public interface ExplicitParameterSpecification extends ParameterSpecification {
	/**
	 * Retrieves the line number on which this parameter occurs in the source query.
	 *
	 * @return The line number.
	 */
	public int getSourceLine();

	/**
	 * Retrieves the column number (within the {@link #getSourceLine()}) where this parameter occurs.
	 *
	 * @return The column number.
	 */
	public int getSourceColumn();
}

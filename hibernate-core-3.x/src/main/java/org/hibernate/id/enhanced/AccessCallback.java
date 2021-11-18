/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.id.enhanced;

import org.hibernate.id.IntegralDataTypeHolder;

/**
 * Contract for providing callback access to a {@link DatabaseStructure},
 * typically from the {@link Optimizer}.
 *
 * @author Steve Ebersole
 */
public interface AccessCallback {
	/**
	 * Retrieve the next value from the underlying source.
	 *
	 * @return The next value.
	 */
	public IntegralDataTypeHolder getNextValue();
}

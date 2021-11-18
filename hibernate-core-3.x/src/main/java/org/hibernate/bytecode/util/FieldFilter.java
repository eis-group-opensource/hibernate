/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.bytecode.util;

/**
 * Used to determine whether a field reference should be instrumented.
 *
 * @author Steve Ebersole
 */
public interface FieldFilter {
	/**
	 * Should this field definition be instrumented?
	 *
	 * @param className The name of the class currently being processed
	 * @param fieldName The name of the field being checked.
	 * @return True if we should instrument this field.
	 */
	public boolean shouldInstrumentField(String className, String fieldName);

	/**
	 * Should we instrument *access to* the given field.  This differs from
	 * {@link #shouldInstrumentField} in that here we are talking about a particular usage of
	 * a field.
	 *
	 * @param transformingClassName The class currently being transformed.
	 * @param fieldOwnerClassName The name of the class owning this field being checked.
	 * @param fieldName The name of the field being checked.
	 * @return True if this access should be transformed.
	 */
	public boolean shouldTransformFieldAccess(String transformingClassName, String fieldOwnerClassName, String fieldName);
}

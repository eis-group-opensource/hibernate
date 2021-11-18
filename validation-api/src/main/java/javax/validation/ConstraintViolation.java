/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package javax.validation;

import javax.validation.metadata.ConstraintDescriptor;

/**
 * Describe a constraint violation. This object exposes the constraint
 * violation context as well as the message describing the violation.
 *
 * @author Emmanuel Bernard
 */
public interface ConstraintViolation<T> {

	/**
	 * @return The interpolated error message for this constraint violation.
	 */
	String getMessage();

	/**
	 * @return The non-interpolated error message for this constraint violation.
	 */
	String getMessageTemplate();

	/**
	 * @return The root bean being validated. Null when returned by
	 *         {@link javax.validation.Validator#validateValue(Class, String, Object, Class[])}
	 */
	T getRootBean();

	/**
	 * @return The class of the root bean being validated
	 */
	Class<T> getRootBeanClass();

	/**
	 * If a bean constraint, the bean instance the constraint is applied on
	 * If a property constraint, the bean instance hosting the property the
	 * constraint is applied on
	 *
	 * @return the leaf bean the constraint is applied on. Null when returned by
	 *         {@link javax.validation.Validator#validateValue(Class, String, Object, Class[])}
	 */
	Object getLeafBean();

	/**
	 * @return the property path to the value from {@code rootBean}.
	 */
	Path getPropertyPath();

	/**
	 * @return the value failing to pass the constraint.
	 */
	Object getInvalidValue();

	/**
	 * Constraint metadata reported to fail.
	 * The returned instance is immutable.
	 *
	 * @return constraint metadata
	 */
	ConstraintDescriptor<?> getConstraintDescriptor();
}

/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package javax.validation;

/**
 * Factory returning initialized <code>Validator</code> instances.
 * Implementations are thread-safe
 * This object is typically cached and reused.
 *
 * @author Emmanuel Bernard
 */
public interface ValidatorFactory {
	/**
	 * Returns an initialized <code>Validator</code> instance using the
	 * factory defaults for message interpolator, traversable resolver
	 * and constraint validator factory.
	 * <p>
	 * Validator instances can be pooled and shared by the implementation.
	 * </p>
	 * @return an initialized <code>Validator</code> instance
	 */
	Validator getValidator();

	/**
	 * Defines a new validator context and return a <code>Validator</code>
	 * compliant this new context.
	 *
	 * @return a <code>ValidatorContext</code>.
	 */
	ValidatorContext usingContext();

	/**
	 * Returns the <code>MessageInterpolator</code> instance configured at
	 * initialization time for the <code>ValidatorFactory<code>.
	 * This is the instance used by #getValidator().
	 *
	 * @return MessageInterpolator instance.
	 */
	MessageInterpolator getMessageInterpolator();

	/**
	 * Returns the <code>TraversableResolver</code> instance configured
	 * at initialization time for the <code>ValidatorFactory<code>.
	 * This is the instance used by #getValidator().
	 *
	 * @return TraversableResolver instance.
	 */
	TraversableResolver getTraversableResolver();

	/**
	 * Returns the <code>ConstraintValidatorFactory</code> instance
	 * configured at initialization time for the
	 * <code>ValidatorFactory<code>.
	 * This is the instance used by #getValidator().
	 *
	 * @return ConstraintValidatorFactory instance.
	 */
	ConstraintValidatorFactory getConstraintValidatorFactory();

	/**
	 * Return an instance of the specified type allowing access to
	 * provider-specific APIs. If the Bean Validation provider
	 * implementation does not support the specified class,
	 * <code>ValidationException,</code> is thrown.
	 *
	 * @param type  the class of the object to be returned.
	 *
	 * @return an instance of the specified class.
	 *
	 * @throws ValidationException if the provider does not
	 *         support the call.
	 */
	public <T> T unwrap(Class<T> type);
}

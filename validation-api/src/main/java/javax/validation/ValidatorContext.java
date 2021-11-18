/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package javax.validation;

/**
 * Represents the context that is used to create <code>Validator</code>
 * instances.
 *
 * A client may use methods of the <code>ValidatorContext</code> returned by
 * <code>ValidatorFactory#usingContext</code> to customize
 * the context used to create <code>Validator</code> instances
 * (for instance establish different message interpolators or
 * traversable resolvers).
 * 
 * @author Emmanuel Bernard
 */
public interface ValidatorContext {
	/**
	 * Defines the message interpolator implementation used by the
	 * <code>Validator</code>.
	 * If not set or if null is passed as a parameter,
	 * the message interpolator of the <code>ValidatorFactory</code>
	 * is used.
	 *
	 * @return self following the chaining method pattern
	 */
	ValidatorContext messageInterpolator(MessageInterpolator messageInterpolator);

	/**
	 * Defines the traversable resolver implementation used by the
	 * <code>Validator</code>.
	 * If not set or if null is passed as a parameter,
	 * the traversable resolver of the <code>ValidatorFactory</code> is used.
	 *
	 * @return self following the chaining method pattern
	 */
	ValidatorContext traversableResolver(TraversableResolver traversableResolver);

	/**
	 * Defines the constraint validator factory implementation used by the
	 * <code>Validator</code>.
	 * If not set or if null is passed as a parameter,
	 * the constraint validator factory of the <code>ValidatorFactory</code> is used.
	 *
	 * @return self following the chaining method pattern
	 */
	ValidatorContext constraintValidatorFactory(ConstraintValidatorFactory factory);

	/**
	 * @return an initialized <code>Validator</code> instance respecting the defined state.
	 * Validator instances can be pooled and shared by the implementation.
	 */
	Validator getValidator();
}

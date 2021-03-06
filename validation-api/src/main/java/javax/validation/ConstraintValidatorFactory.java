/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package javax.validation;

/**
 * Instantiate a <code>ConstraintValidator</code> instance based off its class.
 * The <code>ConstraintValidatorFactory</code> is <b>not</b> responsible
 * for calling {@link ConstraintValidator#initialize(java.lang.annotation.Annotation)}.
 *
 * @author Dhanji R. Prasanna
 * @author Emmanuel Bernard
 * @author Hardy Ferentschik
 */
public interface ConstraintValidatorFactory {

	/**
	 * @param key The class of the constraint validator to instantiate.
	 *
	 * @return A constraint validator instance of the specified class.
	 */
	<T extends ConstraintValidator<?,?>> T getInstance(Class<T> key);
}

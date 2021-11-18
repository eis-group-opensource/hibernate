/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package javax.validation;

import java.lang.annotation.Documented;
import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;
import java.lang.annotation.Annotation;


/**
 * Link between a constraint annotation and its constraint validation implementations.
 * <p/>
 * A given constraint annotation should be annotated by a <code>@Constraint</code>
 * annotation which refers to its list of constraint validation implementations.
 *
 * @author Emmanuel Bernard
 * @author Gavin King
 * @author Hardy Ferentschik
 */
@Documented
@Target({ ANNOTATION_TYPE })
@Retention(RUNTIME)
public @interface Constraint {
	/**
	 * <code>ConstraintValidator</code> classes must reference distinct target types.
	 * If two <code>ConstraintValidator</code> refer to the same type,
	 * an exception will occur.
	 *
	 * @return array of ConstraintValidator classes implementing the constraint
	 */
	public Class<? extends ConstraintValidator<?, ?>>[] validatedBy();
}

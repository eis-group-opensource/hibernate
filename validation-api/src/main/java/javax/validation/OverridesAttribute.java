/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package javax.validation;

import java.lang.annotation.Annotation;
import static java.lang.annotation.ElementType.METHOD;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;
import java.lang.annotation.Documented;

/**
 * Mark an attribute as overriding the attribute of a composing constraint.
 * Both attributes must share the same type.
 *
 * @author Emmanuel Bernard
 */
@Retention(RUNTIME)
@Target({ METHOD })
public @interface OverridesAttribute {
	/**
	 * @return Constraint type the attribute is overriding
	 */
	Class<? extends Annotation> constraint();

	/**
	 * Name of the Constraint attribute overridden.
	 * Defaults to the name of the attribute hosting <code>@OverridesAttribute</code>.
	 *
	 * @return name of constraint attribute overridden.
	 */
	String name();

	/**
	 * The index of the targeted constraint declaration when using
	 * multiple constraints of the same type.
	 * The index represents the index of the constraint in the value() array.
	 *
	 * By default, no index is defined and the single constraint declaration
	 * is targeted
	 *
	 * @return constraint declaration index if multivalued annotation is used
	 */
	int constraintIndex() default -1;

	/**
	 * Defines several @OverridesAttribute annotations on the same element
	 * @see javax.validation.OverridesAttribute
	 */
	@Documented
	@Target({ METHOD })
	@Retention(RUNTIME)
	public @interface List {
		OverridesAttribute[] value();
	}
}

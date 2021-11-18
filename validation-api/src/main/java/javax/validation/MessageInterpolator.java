/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package javax.validation;

import java.util.Locale;
import javax.validation.metadata.ConstraintDescriptor;

/**
 * Interpolate a given constraint violation message.
 * Implementations should be as tolerant as possible on syntax errors.
 *
 * @author Emmanuel Bernard
 * @author Hardy Ferentschik
 */
public interface MessageInterpolator {
	/**
	 * Interpolate the message template based on the contraint validation context.
	 * The locale is defaulted according to the <code>MessageInterpolator</code>
	 * implementation. See the implementation documentation for more detail.
	 *
	 * @param messageTemplate The message to interpolate.
	 * @param context contextual information related to the interpolation
	 *
	 * @return Interpolated error message.
	 */
	String interpolate(String messageTemplate, Context context);

	/**
	 * Interpolate the message template based on the contraint validation context.
	 * The <code>Locale</code> used is provided as a parameter.
	 *
	 * @param messageTemplate The message to interpolate.
	 * @param context contextual information related to the interpolation
	 * @param locale the locale targeted for the message
	 *
	 * @return Interpolated error message.
	 */
	String interpolate(String messageTemplate, Context context,  Locale locale);

	/**
	 * Information related to the interpolation context
	 */
	interface Context {
		/**
		 * @return ConstraintDescriptor corresponding to the constraint being validated
		 */
		ConstraintDescriptor<?> getConstraintDescriptor();

		/**
		 * @return value being validated
		 */
		Object getValidatedValue();
	}
}

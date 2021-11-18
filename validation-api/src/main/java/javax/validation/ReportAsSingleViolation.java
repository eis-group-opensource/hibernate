/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package javax.validation;

import java.lang.annotation.Target;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static java.lang.annotation.ElementType.ANNOTATION_TYPE;

/**
 * A constraint annotation hosting this annotation
 * will return the composed annotation error report if any of the composing annotations
 * fail. The error reports of each individual composing constraint is ignored.
 *
 * @author Emmanuel Bernard
 */
@Target({ ANNOTATION_TYPE })
@Retention(RUNTIME)
public @interface ReportAsSingleViolation {
}

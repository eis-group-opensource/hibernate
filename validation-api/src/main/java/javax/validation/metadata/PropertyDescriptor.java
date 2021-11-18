/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package javax.validation.metadata;

/**
 * Describes a Java Bean property hosting validation constraints.
 *
 * Constraints placed on the attribute and the getter of a given property
 * are all referenced.
 *
 * @author Emmanuel Bernard
 */
public interface PropertyDescriptor extends ElementDescriptor {
	/**
	 * Is the property marked by the <code>@Valid</code> annotation.
	 * @return <code>true</code> if the annotation is present, <code>false</code> otherwise.
	 */
	boolean isCascaded();

	/**
	 * Name of the property acording to the Java Bean specification.
	 * @return property name.
	 */
	String getPropertyName();
}

/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package javax.validation;

import java.lang.annotation.ElementType;

/**
 * Contract determining if a property can be accessed by the Bean Validation provider.
 * This contract is called for each property that is being either validated or cascaded.
 *
 * A traversable resolver implementation must be thread-safe.
 *
 * @author Emmanuel Bernard
 */
public interface TraversableResolver {
	/**
	 * Determine if the Bean Validation provider is allowed to reach the property state
	 *
	 * @param traversableObject object hosting <code>traversableProperty</code> or null
	 *                          if <code>validateValue</code> is called
	 * @param traversableProperty the traversable property.
	 * @param rootBeanType type of the root object passed to the Validator.
	 * @param pathToTraversableObject path from the root object to
	 *        <code>traversableObject</code>
	 *        (using the path specification defined by Bean Validator).
	 * @param elementType either <code>FIELD</code> or <code>METHOD</code>.
	 *
	 * @return <code>true</code> if the Bean Validation provider is allowed to
	 *         reach the property state, <code>false</code> otherwise.
	 */
	boolean isReachable(Object traversableObject,
						Path.Node traversableProperty,
						Class<?> rootBeanType,
						Path pathToTraversableObject,
						ElementType elementType);

	/**
	 * Determine if the Bean Validation provider is allowed to cascade validation on
	 * the bean instance returned by the property value
	 * marked as <code>@Valid</code>.
	 * Note that this method is called only if <code>isReachable</code> returns true
	 * for the same set of arguments and if the property is marked as <code>@Valid</code>
	 *
	 * @param traversableObject object hosting <code>traversableProperty</code> or null
	 *                          if <code>validateValue</code> is called
	 * @param traversableProperty the traversable property.
	 * @param rootBeanType type of the root object passed to the Validator.
	 * @param pathToTraversableObject path from the root object to
	 *        <code>traversableObject</code>
	 *        (using the path specification defined by Bean Validator).
	 * @param elementType either <code>FIELD</code> or <code>METHOD</code>.
	 *
	 * @return <code>true</code> if the Bean Validation provider is allowed to
	 *         cascade validation, <code>false</code> otherwise.
	 */
	boolean isCascadable(Object traversableObject,
						 Path.Node traversableProperty,
						 Class<?> rootBeanType,
						 Path pathToTraversableObject,
						 ElementType elementType);
}

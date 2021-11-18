/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package javax.validation.metadata;

/**
 * Scope looked at when discovering constraints
 *
 * @author Emmanuel Bernard
 */
public enum Scope {
	/**
	 * Look for constraints declared on the current class element
	 * and ignore inheritance and elements with the same name in
	 * the class hierarchy.
	 */
	LOCAL_ELEMENT,

	/**
	 * Look for constraints declared on all elements of the class hierarchy
	 * with the same name.
	 */
	HIERARCHY
}

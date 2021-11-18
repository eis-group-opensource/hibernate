/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.bytecode.javassist;

/**
 * Interface introduced to the enhanced class in order to be able to
 * inject a {@link FieldHandler} to define the interception behavior.
 *
 * @author Muga Nishizawa
 */
public interface FieldHandled {
	/**
	 * Inject the field interception handler to be used.
	 *
	 * @param handler The field interception handler.
	 */
	public void setFieldHandler(FieldHandler handler);

	/**
	 * Access to the current field interception handler.
	 *
	 * @return The current field interception handler.
	 */
	public FieldHandler getFieldHandler();
}

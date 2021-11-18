/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.bytecode.javassist;

/**
 * Contract for deciding whether fields should be read and/or write intercepted.
 *
 * @author Muga Nishizawa
 * @author Steve Ebersole
 */
public interface FieldFilter {
	/**
	 * Should the given field be read intercepted?
	 *
	 * @param desc
	 * @param name
	 * @return true if the given field should be read intercepted; otherwise
	 * false.
	 */
	boolean handleRead(String desc, String name);

	/**
	 * Should the given field be write intercepted?
	 *
	 * @param desc
	 * @param name
	 * @return true if the given field should be write intercepted; otherwise
	 * false.
	 */
	boolean handleWrite(String desc, String name);

	boolean handleReadAccess(String fieldOwnerClassName, String fieldName);

	boolean handleWriteAccess(String fieldOwnerClassName, String fieldName);
}

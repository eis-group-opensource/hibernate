/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.bytecode.util;

/**
 * Contract describing the information Hibernate needs in terms of instrumenting
 * a class, either via ant task or dynamic classloader.
 *
 * @author Steve Ebersole
 */
public interface ClassDescriptor {
	/**
	 * The name of the class.
	 *
	 * @return The class name.
	 */
	public String getName();

	/**
	 * Determine if the class is already instrumented.
	 *
	 * @return True if already instrumented; false otherwise.
	 */
	public boolean isInstrumented();

	/**
	 * The bytes making up the class' bytecode.
	 *
	 * @return The bytecode bytes.
	 */
	public byte[] getBytes();
}

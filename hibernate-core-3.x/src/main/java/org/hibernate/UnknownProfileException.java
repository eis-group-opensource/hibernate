/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate;

/**
 * Used to indicate a request against an unknown profile name.
 *
 * @author Steve Ebersole
 */
public class UnknownProfileException extends HibernateException {
	private final String name;

	public UnknownProfileException(String name) {
		super( "Unknow fetch profile [" + name + "]" );
		this.name = name;
	}

	/**
	 * The unknown fetch profile name.
	 *
	 * @return The unknown fetch profile name.
	 */
	public String getName() {
		return name;
	}
}

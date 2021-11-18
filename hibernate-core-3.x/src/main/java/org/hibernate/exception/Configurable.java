/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.exception;

import org.hibernate.HibernateException;

import java.util.Properties;

/**
 * The Configurable interface defines the contract for SQLExceptionConverter impls that
 * want to be configured prior to usage given the currently defined Hibernate properties.
 *
 * @author Steve Ebersole
 */
public interface Configurable {
	// todo: this might really even be moved into the cfg package and used as the basis for all things which are configurable.

	/**
	 * Configure the component, using the given settings and properties.
	 *
	 * @param properties All defined startup properties.
	 * @throws HibernateException Indicates a configuration exception.
	 */
	public void configure(Properties properties) throws HibernateException;
}

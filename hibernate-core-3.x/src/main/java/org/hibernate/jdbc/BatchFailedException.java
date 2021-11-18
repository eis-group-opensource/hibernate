/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.jdbc;

import org.hibernate.HibernateException;

/**
 * Indicates a failed batch entry (-3 return).
 *
 * @author Steve Ebersole
 */
public class BatchFailedException extends HibernateException {
	public BatchFailedException(String s) {
		super( s );
	}

	public BatchFailedException(String string, Throwable root) {
		super( string, root );
	}
}

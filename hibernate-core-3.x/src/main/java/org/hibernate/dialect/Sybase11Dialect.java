/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.dialect;

import org.hibernate.sql.JoinFragment;
import org.hibernate.sql.Sybase11JoinFragment;

/**
 * A SQL dialect suitable for use with Sybase 11.9.2 (specifically: avoids ANSI JOIN syntax)
 * @author Colm O' Flaherty
 */
public class Sybase11Dialect extends AbstractTransactSQLDialect  {
	public Sybase11Dialect() {
		super();
	}

	public JoinFragment createOuterJoinFragment() {
		return new Sybase11JoinFragment();
	}

	public String getCrossJoinSeparator() {
		return ", ";
	}
}

/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.id.insert;

import org.hibernate.sql.Insert;
import org.hibernate.dialect.Dialect;

/**
 * Nothing more than a distinguishing subclass of Insert used to indicate
 * intent.  Some subclasses of this also provided some additional
 * functionality or semantic to the genernated SQL statement string.
 *
 * @author Steve Ebersole
 */
public class IdentifierGeneratingInsert extends Insert {
	public IdentifierGeneratingInsert(Dialect dialect) {
		super( dialect );
	}
}

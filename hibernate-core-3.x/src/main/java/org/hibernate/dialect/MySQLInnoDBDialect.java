/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.dialect;

/**
 * @author Gavin King
 */
public class MySQLInnoDBDialect extends MySQLDialect {

	public boolean supportsCascadeDelete() {
		return true;
	}
	
	public String getTableTypeString() {
		return " type=InnoDB";
	}

	public boolean hasSelfReferentialForeignKeyBug() {
		return true;
	}
	
}
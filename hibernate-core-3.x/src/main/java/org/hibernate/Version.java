/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate;

/**
 * Information about the Hibernate version.
 *
 * @author Steve Ebersole
 */
public class Version {
	public static String getVersionString() {
		return "[WORKING]";
	}

	public static void main(String[] args) {
		System.out.println( "Hibernate version " + getVersionString() );
	}
}

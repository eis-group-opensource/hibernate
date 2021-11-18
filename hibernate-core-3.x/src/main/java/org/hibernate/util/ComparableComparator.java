/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.util;

import java.util.Comparator;

/**
 * Delegates to Comparable
 * @author Gavin King
 */
public class ComparableComparator implements Comparator {

	public int compare(Object x, Object y) {
		return ( (Comparable) x ).compareTo(y);
	}
	
	public static final Comparator INSTANCE = new ComparableComparator();

}

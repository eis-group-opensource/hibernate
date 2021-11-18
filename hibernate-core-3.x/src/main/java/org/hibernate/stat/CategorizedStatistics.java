/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.stat;

import java.io.Serializable;

/**
 * Statistics for a particular "category" (a named entity,
 * collection role, second level cache region or query).
 * 
 * @author Gavin King
 */
public class CategorizedStatistics implements Serializable {
	
	private final String categoryName;

	CategorizedStatistics(String categoryName) {
		this.categoryName = categoryName;
	}
	
	public String getCategoryName() {
		return categoryName;
	}
}

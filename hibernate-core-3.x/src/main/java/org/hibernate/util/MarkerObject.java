/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.util;

import java.io.Serializable;

/**
 * @author Gavin King
 */
public class MarkerObject implements Serializable {
	private String name;
	
	public MarkerObject(String name) {
		this.name=name;
	}
	public String toString() {
		return name;
	}
}

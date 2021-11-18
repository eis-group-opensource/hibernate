/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.secure;

import java.security.Permission;

/**
 * @author Gavin King
 */
public class HibernatePermission extends Permission {
	
	public static final String INSERT = "insert";
	public static final String UPDATE = "update";
	public static final String DELETE = "delete";
	public static final String READ = "read";
	public static final String ANY = "*";
	
	private final String actions;

	public HibernatePermission(String entityName, String actions) {
		super(entityName);
		this.actions = actions;
	}

	public boolean implies(Permission permission) {
		//TODO!
		return ( "*".equals( getName() ) || getName().equals( permission.getName() ) ) &&
			( "*".equals(actions) || actions.indexOf( permission.getActions() ) >= 0 );
	}

	public boolean equals(Object obj) {
		if ( !(obj instanceof HibernatePermission) ) return false;
		HibernatePermission permission = (HibernatePermission) obj;
		return permission.getName().equals( getName() ) && 
			permission.getActions().equals(actions);
	}

	public int hashCode() {
		return getName().hashCode() * 37 + actions.hashCode();
	}

	public String getActions() {
		return actions;
	}
	
	public String toString() {
		return "HibernatePermission(" + getName() + ':' + actions + ')';
	}

}

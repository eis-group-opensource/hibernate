/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.type;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.util.ReflectHelper;

/**
 * <tt>class</tt>: A type that maps an SQL VARCHAR to a Java Class.
 * @author Gavin King
 */
public class ClassType extends ImmutableType {

	public Object get(ResultSet rs, String name) throws HibernateException, SQLException {
		String str = (String) Hibernate.STRING.get(rs, name);
		if (str == null) {
			return null;
		}
		else {
			try {
				return ReflectHelper.classForName(str);
			}
			catch (ClassNotFoundException cnfe) {
				throw new HibernateException("Class not found: " + str);
			}
		}
	}

	public void set(PreparedStatement st, Object value, int index) throws HibernateException, SQLException {
		//TODO: would be nice to handle proxy classes elegantly!
		Hibernate.STRING.set(st, ( (Class) value ).getName(), index);
	}

	public int sqlType() {
		return Hibernate.STRING.sqlType();
	}

	public String toString(Object value) throws HibernateException {
		return ( (Class) value ).getName();
	}

	public Class getReturnedClass() {
		return Class.class;
	}

	public String getName() {
		return "class";
	}

	public Object fromStringValue(String xml) throws HibernateException {
		try {
			return ReflectHelper.classForName(xml);
		}
		catch (ClassNotFoundException cnfe) {
			throw new HibernateException("could not parse xml", cnfe);
		}
	}

}







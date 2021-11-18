/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.type;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.hibernate.HibernateException;
import org.hibernate.dialect.Dialect;


/**
 * Superclass for types that map Java boolean to SQL CHAR(1).
 * @author Gavin King
 *
 * @deprecated see http://opensource.atlassian.com/projects/hibernate/browse/HHH-5138
 */
public abstract class CharBooleanType extends BooleanType {

	protected abstract String getTrueString();
	protected abstract String getFalseString();

	public Object get(ResultSet rs, String name) throws SQLException {
		String code = rs.getString(name);
		if ( code==null || code.length()==0 ) {
			return null;
		}
		else {
			return getTrueString().equalsIgnoreCase( code.trim() ) ? 
					Boolean.TRUE : Boolean.FALSE;
		}
	}

	public void set(PreparedStatement st, Object value, int index)
	throws SQLException {
		st.setString( index, toCharacter(value) );

	}

	public int sqlType() {
		return Types.CHAR;
	}

	private String toCharacter(Object value) {
		return ( (Boolean) value ).booleanValue() ? getTrueString() : getFalseString();
	}

	public String objectToSQLString(Object value, Dialect dialect) throws Exception {
		return "'" + toCharacter(value) + "'";
	}

	public Object stringToObject(String xml) throws Exception {
		if ( getTrueString().equalsIgnoreCase(xml) ) {
			return Boolean.TRUE;
		}
		else if ( getFalseString().equalsIgnoreCase(xml) ) {
			return Boolean.FALSE;
		}
		else {
			throw new HibernateException("Could not interpret: " + xml);
		}
	}

}








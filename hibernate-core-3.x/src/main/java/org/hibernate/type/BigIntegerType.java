/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.type;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.hibernate.EntityMode;
import org.hibernate.HibernateException;
import org.hibernate.dialect.Dialect;

/**
 * <tt>big_integer</tt>: A type that maps an SQL NUMERIC to a
 * <tt>java.math.BigInteger</tt>
 * @see java.math.BigInteger
 * @author Gavin King
 */
public class BigIntegerType extends ImmutableType implements DiscriminatorType {

	public String objectToSQLString(Object value, Dialect dialect) throws Exception {
		return value.toString();
	}

	public Object stringToObject(String xml) throws Exception {
		return new BigInteger(xml);
	}

	public Object get(ResultSet rs, String name)
	throws HibernateException, SQLException {
		//return rs.getBigDecimal(name).toBigIntegerExact(); this 1.5 only. 
		BigDecimal bigDecimal = rs.getBigDecimal(name);
		return bigDecimal==null ? null : 
			bigDecimal.setScale(0, BigDecimal.ROUND_UNNECESSARY).unscaledValue();
	}

	public void set(PreparedStatement st, Object value, int index)
	throws HibernateException, SQLException {
		st.setBigDecimal( index, new BigDecimal( (BigInteger) value ) );
	}

	public int sqlType() {
		return Types.NUMERIC;
	}

	public String toString(Object value) throws HibernateException {
		return value.toString();
	}

	public Class getReturnedClass() {
		return BigInteger.class;
	}

	public boolean isEqual(Object x, Object y) {
		return x==y || ( x!=null && y!=null && ( (BigInteger) x ).compareTo( (BigInteger) y )==0 );
	}

	public int getHashCode(Object x, EntityMode entityMode) {
		return ( (BigInteger) x ).intValue();
	}

	public String getName() {
		return "big_integer";
	}

	public Object fromStringValue(String xml) {
		return new BigInteger(xml);
	}


}







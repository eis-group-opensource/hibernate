/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.type;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Types;

import org.hibernate.dialect.Dialect;

/**
 * Maps {@link Types#INTEGER interger} database values to boolean java values.  Zero is considered false;
 * <tt>NULL</tt> maps to {@link #getDefaultValue()}; any other value is considered true.
 *
 * @author Steve Ebersole
 * @see #getName()
 */
public class NumericBooleanType extends BooleanType {

	/**
	 * {@inheritDoc}
	 * <p/>
	 * This type's name is <tt>numeric_boolean</tt>
	 */
	public String getName() {
		return "numeric_boolean";
	}

	/**
	 * {@inheritDoc}
	 */
	public Object get(ResultSet rs, String name) throws SQLException {
		int value = rs.getInt( name );
		if ( rs.wasNull() ) {
			return getDefaultValue();
		}
		else if ( value == 0 ) {
			return Boolean.FALSE;
		}
		else {
			return Boolean.TRUE;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void set(PreparedStatement st, Object value, int index) throws SQLException {
		if ( value == null ) {
			st.setNull( index, Types.INTEGER );
		}
		else {
			boolean bool = ( ( Boolean ) value ).booleanValue();
			st.setInt( index, bool ? 1 : 0 );
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public String objectToSQLString(Object value, Dialect dialect) throws Exception {
		return ( ( Boolean ) value ).booleanValue() ? "1" : "0";
	}

	/**
	 * {@inheritDoc}
	 */
	public int sqlType() {
		return Types.INTEGER;
	}
}

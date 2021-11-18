/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.type;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hibernate.HibernateException;

/**
 * An abstract type for mapping long string SQL types to a Java String.
 * @author Gavin King, Bertrand Renuart (from TextType)
 *
 * @deprecated see http://opensource.atlassian.com/projects/hibernate/browse/HHH-5138
 */
public abstract class AbstractLongStringType extends ImmutableType {

	public void set(PreparedStatement st, Object value, int index) throws HibernateException, SQLException {
		String str = (String) value;
		st.setCharacterStream( index, new StringReader(str), str.length() );
	}

	public Object get(ResultSet rs, String name) throws HibernateException, SQLException {

			// Retrieve the value of the designated column in the current row of this
			// ResultSet object as a java.io.Reader object
			Reader charReader = rs.getCharacterStream(name);

			// if the corresponding SQL value is NULL, the reader we got is NULL as well
			if (charReader==null) return null;

			// Fetch Reader content up to the end - and put characters in a StringBuffer
			StringBuffer sb = new StringBuffer();
			try {
				char[] buffer = new char[2048];
				while (true) {
					int amountRead = charReader.read(buffer, 0, buffer.length);
					if ( amountRead == -1 ) break;
					sb.append(buffer, 0, amountRead);
				}
			}
			catch (IOException ioe) {
				throw new HibernateException( "IOException occurred reading text", ioe );
			}
			finally {
				try {
					charReader.close();
				}
				catch (IOException e) {
					throw new HibernateException( "IOException occurred closing stream", e );
				}
			}

			// Return StringBuffer content as a large String
			return sb.toString();
	}

	public Class getReturnedClass() {
		return String.class;
	}

	public String toString(Object val) {
		return (String) val;
	}
	public Object fromStringValue(String xml) {
		return xml;
	}

}
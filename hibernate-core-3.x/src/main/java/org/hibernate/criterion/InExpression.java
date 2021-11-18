/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.criterion;

import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.EntityMode;
import org.hibernate.HibernateException;

import org.hibernate.engine.TypedValue;

import org.hibernate.type.AbstractComponentType;
import org.hibernate.type.Type;
import org.hibernate.util.StringHelper;

/**
 * Constrains the property to a specified list of values
 * @author Gavin King
 */
public class InExpression implements Criterion {

	private final String propertyName;
	private final Object[] values;

	protected InExpression(String propertyName, Object[] values) {
		this.propertyName = propertyName;
		this.values = values;
	}

    public String toSqlString( Criteria criteria, CriteriaQuery criteriaQuery )
            throws HibernateException {
        String[] columns = criteriaQuery.getColumnsUsingProjection(
                criteria, propertyName );
        if ( criteriaQuery.getFactory().getDialect()
                .supportsRowValueConstructorSyntaxInInList() || columns.length<=1) {

            String singleValueParam = StringHelper.repeat( "?, ",
                    columns.length - 1 )
                    + "?";
            if ( columns.length > 1 )
                singleValueParam = '(' + singleValueParam + ')';
            String params = values.length > 0 ? StringHelper.repeat(
                    singleValueParam + ", ", values.length - 1 )
                    + singleValueParam : "";
            String cols = StringHelper.join( ", ", columns );
            if ( columns.length > 1 )
                cols = '(' + cols + ')';
            return cols + " in (" + params + ')';
        } else {
           String cols = " ( " + StringHelper.join( " = ? and ", columns ) + "= ? ) ";
             cols = values.length > 0 ? StringHelper.repeat( cols
                    + "or ", values.length - 1 )
                    + cols : "";
            cols = " ( " + cols + " ) ";
            return cols;
        }
    }

	public TypedValue[] getTypedValues(Criteria criteria, CriteriaQuery criteriaQuery) 
	throws HibernateException {
		ArrayList list = new ArrayList();
		Type type = criteriaQuery.getTypeUsingProjection(criteria, propertyName);
		if ( type.isComponentType() ) {
			AbstractComponentType actype = (AbstractComponentType) type;
			Type[] types = actype.getSubtypes();
			for ( int j=0; j<values.length; j++ ) {
				for ( int i=0; i<types.length; i++ ) {
					Object subval = values[j]==null ? 
						null : 
						actype.getPropertyValues( values[j], EntityMode.POJO )[i];
					list.add( new TypedValue( types[i], subval, EntityMode.POJO ) );
				}
			}
		}
		else {
			for ( int j=0; j<values.length; j++ ) {
				list.add( new TypedValue( type, values[j], EntityMode.POJO ) );
			}
		}
		return (TypedValue[]) list.toArray( new TypedValue[ list.size() ] );
	}

	public String toString() {
		return propertyName + " in (" + StringHelper.toString(values) + ')';
	}

}

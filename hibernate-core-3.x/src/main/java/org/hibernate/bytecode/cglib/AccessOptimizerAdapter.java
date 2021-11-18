/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.bytecode.cglib;

import org.hibernate.bytecode.ReflectionOptimizer;
import org.hibernate.PropertyAccessException;
import net.sf.cglib.beans.BulkBean;
import net.sf.cglib.beans.BulkBeanException;

import java.io.Serializable;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * The {@link ReflectionOptimizer.AccessOptimizer} implementation for CGLIB
 * which simply acts as an adpater to the {@link BulkBean} class.
 *
 * @author Steve Ebersole
 *
 * @deprecated Per HHH-5451 support for cglib as a bytecode provider has been deprecated.
 */
public class AccessOptimizerAdapter implements ReflectionOptimizer.AccessOptimizer, Serializable {

	public static final String PROPERTY_GET_EXCEPTION =
			"exception getting property value with CGLIB (set hibernate.bytecode.use_reflection_optimizer=false for more info)";

	public static final String PROPERTY_SET_EXCEPTION =
			"exception setting property value with CGLIB (set hibernate.bytecode.use_reflection_optimizer=false for more info)";

	private Class mappedClass;
	private BulkBean bulkBean;

	public AccessOptimizerAdapter(BulkBean bulkBean, Class mappedClass) {
		this.bulkBean = bulkBean;
		this.mappedClass = mappedClass;
	}

	public String[] getPropertyNames() {
		return bulkBean.getGetters();
	}

	public Object[] getPropertyValues(Object object) {
		try {
			return bulkBean.getPropertyValues( object );
		}
		catch ( Throwable t ) {
			throw new PropertyAccessException(
					t,
			        PROPERTY_GET_EXCEPTION,
			        false,
			        mappedClass,
			        getterName( t, bulkBean )
			);
		}
	}

	public void setPropertyValues(Object object, Object[] values) {
		try {
			bulkBean.setPropertyValues( object, values );
		}
		catch ( Throwable t ) {
			throw new PropertyAccessException(
					t,
			        PROPERTY_SET_EXCEPTION,
			        true,
			        mappedClass,
			        setterName( t, bulkBean )
			);
		}
	}

	private static String setterName(Throwable t, BulkBean optimizer) {
		if ( t instanceof BulkBeanException ) {
			return optimizer.getSetters()[( ( BulkBeanException ) t ).getIndex()];
		}
		else {
			return "?";
		}
	}

	private static String getterName(Throwable t, BulkBean optimizer) {
		if ( t instanceof BulkBeanException ) {
			return optimizer.getGetters()[( ( BulkBeanException ) t ).getIndex()];
		}
		else {
			return "?";
		}
	}

	private void writeObject(ObjectOutputStream out) throws IOException {
		out.writeObject( mappedClass );
		out.writeObject( bulkBean.getGetters() );
		out.writeObject( bulkBean.getSetters() );
		out.writeObject( bulkBean.getPropertyTypes() );
	}

	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		Class beanClass = ( Class ) in.readObject();
		String[] getters = ( String[] ) in.readObject();
		String[] setters = ( String[] ) in.readObject();
		Class[] types = ( Class[] ) in.readObject();
		bulkBean = BulkBean.create( beanClass, getters, setters, types );
	}
}

/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.bytecode.javassist;

import java.io.Serializable;


/**
 * A JavaBean accessor.
 * <p/>
 * <p>This object provides methods that set/get multiple properties
 * of a JavaBean at once.  This class and its support classes have been
 * developed for the comaptibility with cglib
 * (<tt>http://cglib.sourceforge.net/</tt>).
 *
 * @author Muga Nishizawa
 * @author modified by Shigeru Chiba
 */
public abstract class BulkAccessor implements Serializable {
	protected Class target;
	protected String[] getters, setters;
	protected Class[] types;

	protected BulkAccessor() {
	}

	/**
	 * Obtains the values of properties of a given bean.
	 *
	 * @param bean   JavaBean.
	 * @param values the obtained values are stored in this array.
	 */
	public abstract void getPropertyValues(Object bean, Object[] values);

	/**
	 * Sets properties of a given bean to specified values.
	 *
	 * @param bean   JavaBean.
	 * @param values the values assinged to properties.
	 */
	public abstract void setPropertyValues(Object bean, Object[] values);

	/**
	 * Returns the values of properties of a given bean.
	 *
	 * @param bean JavaBean.
	 */
	public Object[] getPropertyValues(Object bean) {
		Object[] values = new Object[getters.length];
		getPropertyValues( bean, values );
		return values;
	}

	/**
	 * Returns the types of properties.
	 */
	public Class[] getPropertyTypes() {
		return ( Class[] ) types.clone();
	}

	/**
	 * Returns the setter names of properties.
	 */
	public String[] getGetters() {
		return ( String[] ) getters.clone();
	}

	/**
	 * Returns the getter names of the properties.
	 */
	public String[] getSetters() {
		return ( String[] ) setters.clone();
	}

	/**
	 * Creates a new instance of <code>BulkAccessor</code>.
	 * The created instance provides methods for setting/getting
	 * specified properties at once.
	 *
	 * @param beanClass the class of the JavaBeans accessed
	 *                  through the created object.
	 * @param getters   the names of setter methods for specified properties.
	 * @param setters   the names of getter methods for specified properties.
	 * @param types     the types of specified properties.
	 */
	public static BulkAccessor create(
			Class beanClass,
	        String[] getters,
	        String[] setters,
	        Class[] types) {
		BulkAccessorFactory factory = new BulkAccessorFactory( beanClass, getters, setters, types );
		return factory.create();
	}
}
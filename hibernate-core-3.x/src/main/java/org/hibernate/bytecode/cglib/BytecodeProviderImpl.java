/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.bytecode.cglib;

import java.lang.reflect.Modifier;

import net.sf.cglib.beans.BulkBean;
import net.sf.cglib.beans.BulkBeanException;
import net.sf.cglib.reflect.FastClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.hibernate.bytecode.BytecodeProvider;
import org.hibernate.bytecode.ProxyFactoryFactory;
import org.hibernate.bytecode.ReflectionOptimizer;
import org.hibernate.bytecode.util.FieldFilter;
import org.hibernate.util.StringHelper;

/**
 * Bytecode provider implementation for CGLIB.
 *
 * @author Steve Ebersole
 *
 * @deprecated Per HHH-5451 support for cglib as a bytecode provider has been deprecated.
 */
public class BytecodeProviderImpl implements BytecodeProvider {
	private static final Logger log = LoggerFactory.getLogger( BytecodeProviderImpl.class );

	public BytecodeProviderImpl() {
		log.warn( "Per HHH-5451 support for cglib as a bytecode provider has been deprecated." );
	}

	public ProxyFactoryFactory getProxyFactoryFactory() {
		return new ProxyFactoryFactoryImpl();
	}

	public ReflectionOptimizer getReflectionOptimizer(
			Class clazz,
	        String[] getterNames,
	        String[] setterNames,
	        Class[] types) {
		FastClass fastClass;
		BulkBean bulkBean;
		try {
			fastClass = FastClass.create( clazz );
			bulkBean = BulkBean.create( clazz, getterNames, setterNames, types );
			if ( !clazz.isInterface() && !Modifier.isAbstract( clazz.getModifiers() ) ) {
				if ( fastClass == null ) {
					bulkBean = null;
				}
				else {
					//test out the optimizer:
					Object instance = fastClass.newInstance();
					bulkBean.setPropertyValues( instance, bulkBean.getPropertyValues( instance ) );
				}
			}
		}
		catch( Throwable t ) {
			fastClass = null;
			bulkBean = null;
			String message = "reflection optimizer disabled for: " +
			                 clazz.getName() +
			                 " [" +
			                 StringHelper.unqualify( t.getClass().getName() ) +
			                 ": " +
			                 t.getMessage();

			if (t instanceof BulkBeanException ) {
				int index = ( (BulkBeanException) t ).getIndex();
				if (index >= 0) {
					message += " (property " + setterNames[index] + ")";
				}
			}

			log.debug( message );
		}

		if ( fastClass != null && bulkBean != null ) {
			return new ReflectionOptimizerImpl(
					new InstantiationOptimizerAdapter( fastClass ),
			        new AccessOptimizerAdapter( bulkBean, clazz )
			);
		}
		else {
			return null;
		}
	}

	public org.hibernate.bytecode.ClassTransformer getTransformer(org.hibernate.bytecode.util.ClassFilter classFilter, FieldFilter fieldFilter) {
		return new CglibClassTransformer( classFilter, fieldFilter );
	}

}

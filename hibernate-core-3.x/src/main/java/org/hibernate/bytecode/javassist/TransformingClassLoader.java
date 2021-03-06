/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.bytecode.javassist;

import javassist.ClassPool;
import javassist.NotFoundException;
import javassist.CtClass;
import javassist.CannotCompileException;
import org.hibernate.HibernateException;

import java.io.IOException;

/**
 * @author Steve Ebersole
 */
public class TransformingClassLoader extends ClassLoader {
	private ClassLoader parent;
	private ClassPool classPool;

	/*package*/ TransformingClassLoader(ClassLoader parent, String[] classpath) {
		this.parent = parent;
		classPool = new ClassPool( true );
		for ( int i = 0; i < classpath.length; i++ ) {
			try {
				classPool.appendClassPath( classpath[i] );
			}
			catch ( NotFoundException e ) {
				throw new HibernateException(
						"Unable to resolve requested classpath for transformation [" +
						classpath[i] + "] : " + e.getMessage()
				);
			}
		}
	}

	protected Class findClass(String name) throws ClassNotFoundException {
        try {
            CtClass cc = classPool.get( name );
	        // todo : modify the class definition if not already transformed...
            byte[] b = cc.toBytecode();
            return defineClass( name, b, 0, b.length );
        }
        catch ( NotFoundException e ) {
            throw new ClassNotFoundException();
        }
        catch ( IOException e ) {
            throw new ClassNotFoundException();
        }
        catch ( CannotCompileException e ) {
            throw new ClassNotFoundException();
        }
    }

	public void release() {
		classPool = null;
		parent = null;
	}
}

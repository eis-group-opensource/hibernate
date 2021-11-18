/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.bytecode.buildtime;

import java.util.Set;
import java.io.IOException;
import java.io.DataInputStream;
import java.io.ByteArrayInputStream;

import javassist.bytecode.ClassFile;

import org.hibernate.bytecode.util.ClassDescriptor;
import org.hibernate.bytecode.util.BasicClassFilter;
import org.hibernate.bytecode.ClassTransformer;
import org.hibernate.bytecode.javassist.BytecodeProviderImpl;
import org.hibernate.bytecode.javassist.FieldHandled;

/**
 * Strategy for performing build-time instrumentation of persistent classes in order to enable
 * field-level interception using Javassist.
 *
 * @author Steve Ebersole
 * @author Muga Nishizawa
 */
public class JavassistInstrumenter extends AbstractInstrumenter {

	private static final BasicClassFilter CLASS_FILTER = new BasicClassFilter();

	private final BytecodeProviderImpl provider = new BytecodeProviderImpl();

	public JavassistInstrumenter(Logger logger, Options options) {
		super( logger, options );
	}

	protected ClassDescriptor getClassDescriptor(byte[] bytecode) throws IOException {
		return new CustomClassDescriptor( bytecode );
	}

	protected ClassTransformer getClassTransformer(ClassDescriptor descriptor, Set classNames) {
		if ( descriptor.isInstrumented() ) {
			logger.debug( "class [" + descriptor.getName() + "] already instrumented" );
			return null;
		}
		else {
			return provider.getTransformer( CLASS_FILTER, new CustomFieldFilter( descriptor, classNames ) );
		}
	}

	private static class CustomClassDescriptor implements ClassDescriptor {
		private final byte[] bytes;
		private final ClassFile classFile;

		public CustomClassDescriptor(byte[] bytes) throws IOException {
			this.bytes = bytes;
			this.classFile = new ClassFile( new DataInputStream( new ByteArrayInputStream( bytes ) ) );
		}

		public String getName() {
			return classFile.getName();
		}

		public boolean isInstrumented() {
			String[] intfs = classFile.getInterfaces();
			for ( int i = 0; i < intfs.length; i++ ) {
				if ( FieldHandled.class.getName().equals( intfs[i] ) ) {
					return true;
				}
			}
			return false;
		}

		public byte[] getBytes() {
			return bytes;
		}
	}

}

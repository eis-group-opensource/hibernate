/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.bytecode.buildtime;

import java.util.Set;
import java.io.ByteArrayInputStream;

import org.hibernate.bytecode.util.ClassDescriptor;
import org.hibernate.bytecode.util.BasicClassFilter;
import org.hibernate.bytecode.ClassTransformer;
import org.hibernate.bytecode.cglib.BytecodeProviderImpl;
import org.objectweb.asm.ClassReader;
import net.sf.cglib.core.ClassNameReader;
import net.sf.cglib.transform.impl.InterceptFieldEnabled;

/**
 * Strategy for performing build-time instrumentation of persistent classes in order to enable
 * field-level interception using CGLIB.
 *
 * @author Steve Ebersole
 * @author Gavin King
 */
public class CGLIBInstrumenter extends AbstractInstrumenter {
	private static final BasicClassFilter CLASS_FILTER = new BasicClassFilter();

	private final BytecodeProviderImpl provider = new BytecodeProviderImpl();

	public CGLIBInstrumenter(Logger logger, Options options) {
		super( logger, options );
	}

	protected ClassDescriptor getClassDescriptor(byte[] byecode) throws Exception {
		return new CustomClassDescriptor( byecode );
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
		private final byte[] bytecode;
		private final String name;
		private final boolean isInstrumented;

		public CustomClassDescriptor(byte[] bytecode) throws Exception {
			this.bytecode = bytecode;
			ClassReader reader = new ClassReader( new ByteArrayInputStream( bytecode ) );
			String[] names = ClassNameReader.getClassInfo( reader );
			this.name = names[0];
			boolean instrumented = false;
			for ( int i = 1; i < names.length; i++ ) {
				if ( InterceptFieldEnabled.class.getName().equals( names[i] ) ) {
					instrumented = true;
					break;
				}
			}
			this.isInstrumented = instrumented;
		}

		public String getName() {
			return name;
		}

		public boolean isInstrumented() {
			return isInstrumented;
		}

		public byte[] getBytes() {
			return bytecode;
		}
	}

}

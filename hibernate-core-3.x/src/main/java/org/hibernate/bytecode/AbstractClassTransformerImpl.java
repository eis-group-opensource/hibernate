/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.bytecode;

import org.hibernate.bytecode.util.ClassFilter;
import org.hibernate.bytecode.util.FieldFilter;

import java.security.ProtectionDomain;

/**
 * Basic implementation of the {@link ClassTransformer} contract.
 *
 * @author Emmanuel Bernard
 * @author Steve Ebersole
 */
public abstract class AbstractClassTransformerImpl implements ClassTransformer {

	protected final ClassFilter classFilter;
	protected final FieldFilter fieldFilter;

	protected AbstractClassTransformerImpl(ClassFilter classFilter, FieldFilter fieldFilter) {
		this.classFilter = classFilter;
		this.fieldFilter = fieldFilter;
	}

	public byte[] transform(
			ClassLoader loader,
			String className,
			Class classBeingRedefined,
			ProtectionDomain protectionDomain,
			byte[] classfileBuffer) {
		// to be safe...
		className = className.replace( '/', '.' );
		if ( classFilter.shouldInstrumentClass( className ) ) {
			return doTransform( loader, className, classBeingRedefined, protectionDomain, classfileBuffer );
		}
		else {
			return classfileBuffer;
		}
	}

	protected abstract byte[] doTransform(
			ClassLoader loader,
			String className,
			Class classBeingRedefined,
			ProtectionDomain protectionDomain,
			byte[] classfileBuffer);
}

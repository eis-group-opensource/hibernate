/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.bytecode.buildtime;

import java.util.Set;

/**
 * TODO : javadoc
 *
 * @author Steve Ebersole
 */
public interface Instrumenter {
	public void execute(Set files);

	public static interface Options {
		public boolean performExtendedInstrumentation();
	}
}
/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.dialect.function;

import org.hibernate.engine.SessionFactoryImplementor;

/**
 * Defines support for rendering according to ANSI SQL <tt>TRIM<//tt> function specification.
 *
 * @author Steve Ebersole
 */
public class AnsiTrimFunction extends TrimFunctionTemplate {
	protected String render(Options options, String trimSource, SessionFactoryImplementor factory) {
		return new StringBuffer()
				.append( "trim(" )
				.append( options.getTrimSpecification().getName() )
				.append( ' ' )
				.append( options.getTrimCharacter() )
				.append( " from " )
				.append( trimSource )
				.append( ')' )
				.toString();
	}
}

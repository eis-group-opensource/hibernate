/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.tool.instrument.cglib;

import org.hibernate.bytecode.buildtime.CGLIBInstrumenter;
import org.hibernate.bytecode.buildtime.Instrumenter;
import org.hibernate.bytecode.buildtime.Logger;
import org.hibernate.tool.instrument.BasicInstrumentationTask;

/**
 * An Ant task for instrumenting persistent classes in order to enable
 * field-level interception using CGLIB.
 * <p/>
 * In order to use this task, typically you would define a a taskdef
 * similar to:<pre>
 * <taskdef name="instrument" classname="org.hibernate.tool.instrument.cglib.InstrumentTask">
 *     <classpath refid="lib.class.path"/>
 * </taskdef>
 * </pre>
 * where <tt>lib.class.path</tt> is an ANT path reference containing all the
 * required Hibernate and CGLIB libraries.
 * <p/>
 * And then use it like:<pre>
 * <instrument>
 *     <fileset dir="${testclasses.dir}/org/hibernate/test">
 *         <include name="yadda/yadda/**"/>
 *         ...
 *     </fileset>
 * </instrument>
 * </pre>
 * where the nested ANT fileset includes the class you would like to have
 * instrumented.
 * <p/>
 * Optionally you can chose to enable "Extended Instrumentation" if desired
 * by specifying the extended attribute on the task:<pre>
 * <instrument extended="true">
 *     ...
 * </instrument>
 * </pre>
 * See the Hibernate manual regarding this option.
 *
 * @author Gavin King
 * @author Steve Ebersole
 * 
 * @deprecated Per HHH-5451 support for cglib as a bytecode provider has been deprecated; use
 * {@link org.hibernate.tool.instrument.javassist.InstrumentTask} instead
 */
public class InstrumentTask extends BasicInstrumentationTask {
	public InstrumentTask() {
		System.err.println( "Per HHH-5451 support for cglib as a bytecode provider has been deprecated." );
	}

	protected Instrumenter buildInstrumenter(Logger logger, Instrumenter.Options options) {
		return new CGLIBInstrumenter( logger, options );
	}
}

/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.tool.instrument.javassist;

import org.hibernate.bytecode.buildtime.Instrumenter;
import org.hibernate.bytecode.buildtime.JavassistInstrumenter;
import org.hibernate.bytecode.buildtime.Logger;
import org.hibernate.tool.instrument.BasicInstrumentationTask;

/**
 * An Ant task for instrumenting persistent classes in order to enable
 * field-level interception using Javassist.
 * <p/>
 * In order to use this task, typically you would define a a taskdef
 * similiar to:<pre>
 * <taskdef name="instrument" classname="org.hibernate.tool.instrument.javassist.InstrumentTask">
 *     <classpath refid="lib.class.path"/>
 * </taskdef>
 * </pre>
 * where <tt>lib.class.path</tt> is an ANT path reference containing all the
 * required Hibernate and Javassist libraries.
 * <p/>
 * And then use it like:<pre>
 * <instrument verbose="true">
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
 * by specifying the extended attriubute on the task:<pre>
 * <instrument verbose="true" extended="true">
 *     ...
 * </instrument>
 * </pre>
 * See the Hibernate manual regarding this option.
 *
 * @author Muga Nishizawa
 * @author Steve Ebersole
 */
public class InstrumentTask extends BasicInstrumentationTask {
	protected Instrumenter buildInstrumenter(Logger logger, Instrumenter.Options options) {
		return new JavassistInstrumenter( logger, options );
	}
}

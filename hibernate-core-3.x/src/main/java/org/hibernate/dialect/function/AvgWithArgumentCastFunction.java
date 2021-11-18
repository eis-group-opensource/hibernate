/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.dialect.function;

import java.util.List;

import org.hibernate.QueryException;
import org.hibernate.engine.SessionFactoryImplementor;

/**
 * Some databases strictly return the type of the of the aggregation value for <tt>AVG</tt> which is
 * problematic in the case of averaging integers because the decimals will be dropped.  The usual workaround
 * is to cast the integer argument as some form of double/decimal.
 * <p/>
 * A downside to this approach is that we always wrap the avg() argument in a cast even though we may not need or want
 * to.  A more full-featured solution would be defining {@link SQLFunction} such that we render based on the first
 * argument; essentially have {@link SQLFunction} describe the basic metadata about the function and merge the
 * {@link SQLFunction#getReturnType} and {@link SQLFunction#render} methods into a
 *
 * @author Steve Ebersole
 */
public class AvgWithArgumentCastFunction extends AvgFunction {
	private final TemplateRenderer renderer;

	public AvgWithArgumentCastFunction(String castType) {
		renderer = new TemplateRenderer( "avg(cast(?1 as " + castType + "))" );
	}

	public String render(List args, SessionFactoryImplementor factory) throws QueryException {
		return renderer.render( args, factory );
	}
}

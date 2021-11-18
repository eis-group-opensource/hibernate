/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.bytecode.buildtime;

/**
 * Provides an abstraction for how instrumentation does logging because it is usually run in environments (Ant/Maven)
 * with their own logging infrastructure.  This abstraction allows proper bridging.
 *
 * @author Steve Ebersole
 */
public interface Logger {
	public void trace(String message);

	public void debug(String message);

	public void info(String message);

	public void warn(String message);

	public void error(String message);
}

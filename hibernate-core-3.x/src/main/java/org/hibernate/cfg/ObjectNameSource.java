/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.cfg;

/**
 * Source for database object names (identifiers).
 *
 * @author Steve Ebersole
 */
public interface ObjectNameSource {
	/**
	 * Retrieve the name explicitly provided by the user.
	 *
	 * @return The explicit name.
	 */
	public String getExplicitName();

	/**
	 * Retrieve the logical name for this object.  Usually this is the name under which
	 * the "thing" is registered with the {@link Mappings}.
	 * 
	 * @return The logical name.
	 */
	public String getLogicalName();
}

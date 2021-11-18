/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.util.xml;

import java.io.Serializable;

/**
 * Describes the origin of an xml document
 *
 * @author Steve Ebersole
 */
public interface Origin extends Serializable {
	/**
	 * Retrieve the type of origin.  This is not a discrete set, but might be somethign like
	 * {@code file} for file protocol URLs, or {@code resource} for classpath resource lookups.
	 *
	 * @return The origin type.
	 */
	public String getType();

	/**
	 * The name of the document origin.  Interpretation is relative to the type, but might be the
	 * resource name or file URL.
	 *
	 * @return The name.
	 */
	public String getName();
}

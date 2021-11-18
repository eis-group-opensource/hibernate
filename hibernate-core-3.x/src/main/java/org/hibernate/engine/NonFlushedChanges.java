/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.engine;

/**
 * This interface defines the public API for changes to an EventSource that have not
 * been flushed to the database.
 *
 * @author Gail Badner
 */

import java.io.Serializable;
import org.hibernate.event.EventSource;

public interface NonFlushedChanges extends Serializable {

	/**
	 * Extracts the non-flushed Changes from an EventSource into this NonFlushedChanges object.
	 * <p>
	 * @param source
	 */
	void extractFromSession(EventSource source);

	/**
	 * Remove the non-flushed changes from this NonFlushedChanges object.
	 */
	void clear();
}
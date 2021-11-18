/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.stat;

import java.io.Serializable;

/**
 * Collection related statistics
 *
 * @author Gavin King
 * @author Alex Snaps
 */
public interface CollectionStatistics extends Serializable {

	long getLoadCount();

	long getFetchCount();

	long getRecreateCount();

	long getRemoveCount();

	long getUpdateCount();
}

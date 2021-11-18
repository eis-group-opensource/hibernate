/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.stat;

/**
 * Collection related statistics
 *
 * @author Gavin King
 */
public class CollectionStatisticsImpl extends CategorizedStatistics implements CollectionStatistics {

	CollectionStatisticsImpl(String role) {
		super(role);
	}

	long loadCount;
	long fetchCount;
	long updateCount;
	long removeCount;
	long recreateCount;

	public long getLoadCount() {
		return loadCount;
	}

	public long getFetchCount() {
		return fetchCount;
	}

	public long getRecreateCount() {
		return recreateCount;
	}

	public long getRemoveCount() {
		return removeCount;
	}

	public long getUpdateCount() {
		return updateCount;
	}

	public String toString() {
		return new StringBuilder()
				.append("CollectionStatistics")
				.append("[loadCount=").append(this.loadCount)
				.append(",fetchCount=").append(this.fetchCount)
				.append(",recreateCount=").append(this.recreateCount)
				.append(",removeCount=").append(this.removeCount)
				.append(",updateCount=").append(this.updateCount)
				.append(']')
				.toString();
	}
}

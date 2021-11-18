/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.stat;


/**
 * Entity related statistics
 *
 * @author Gavin King
 */
public class EntityStatisticsImpl extends CategorizedStatistics implements EntityStatistics {

	EntityStatisticsImpl(String name) {
		super(name);
	}

	long loadCount;
	long updateCount;
	long insertCount;
	long deleteCount;
	long fetchCount;
	long optimisticFailureCount;

	public long getDeleteCount() {
		return deleteCount;
	}

	public long getInsertCount() {
		return insertCount;
	}

	public long getLoadCount() {
		return loadCount;
	}

	public long getUpdateCount() {
		return updateCount;
	}

	public long getFetchCount() {
		return fetchCount;
	}

	public long getOptimisticFailureCount() {
		return optimisticFailureCount;
	}

	public String toString() {
		return new StringBuilder()
				.append("EntityStatistics")
				.append("[loadCount=").append(this.loadCount)
				.append(",updateCount=").append(this.updateCount)
				.append(",insertCount=").append(this.insertCount)
				.append(",deleteCount=").append(this.deleteCount)
				.append(",fetchCount=").append(this.fetchCount)
				.append(",optimisticLockFailureCount=").append(this.optimisticFailureCount)
				.append(']')
				.toString();
	}

}

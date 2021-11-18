/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.stat;

/**
 * Query statistics (HQL and SQL)
 * <p/>
 * Note that for a cached query, the cache miss is equals to the db count
 *
 * @author Gavin King
 */
public class QueryStatisticsImpl extends CategorizedStatistics implements QueryStatistics {

	/*package*/
	long cacheHitCount;
	/*package*/
	long cacheMissCount;
	/*package*/
	long cachePutCount;
	private long executionCount;
	private long executionRowCount;
	private long executionAvgTime;
	private long executionMaxTime;
	private long executionMinTime = Long.MAX_VALUE;

	QueryStatisticsImpl(String query) {
		super(query);
	}

	/**
	 * queries executed to the DB
	 */
	public long getExecutionCount() {
		return executionCount;
	}

	/**
	 * Queries retrieved successfully from the cache
	 */
	public long getCacheHitCount() {
		return cacheHitCount;
	}

	public long getCachePutCount() {
		return cachePutCount;
	}

	public long getCacheMissCount() {
		return cacheMissCount;
	}

	/**
	 * Number of lines returned by all the executions of this query (from DB)
	 * For now, {@link org.hibernate.Query#iterate()}
	 * and {@link org.hibernate.Query#scroll()()} do not fill this statistic
	 *
	 * @return The number of rows cumulatively returned by the given query; iterate
	 *         and scroll queries do not effect this total as their number of returned rows
	 *         is not known at execution time.
	 */
	public long getExecutionRowCount() {
		return executionRowCount;
	}

	/**
	 * average time in ms taken by the excution of this query onto the DB
	 */
	public long getExecutionAvgTime() {
		return executionAvgTime;
	}

	/**
	 * max time in ms taken by the excution of this query onto the DB
	 */
	public long getExecutionMaxTime() {
		return executionMaxTime;
	}

	/**
	 * min time in ms taken by the excution of this query onto the DB
	 */
	public long getExecutionMinTime() {
		return executionMinTime;
	}

	/**
	 * add statistics report of a DB query
	 *
	 * @param rows rows count returned
	 * @param time time taken
	 */
	void executed(long rows, long time) {
		if (time < executionMinTime) executionMinTime = time;
		if (time > executionMaxTime) executionMaxTime = time;
		executionAvgTime = (executionAvgTime * executionCount + time) / (executionCount + 1);
		executionCount++;
		executionRowCount += rows;
	}

	public String toString() {
		return new StringBuilder()
				.append("QueryStatistics")
				.append("[cacheHitCount=").append(this.cacheHitCount)
				.append(",cacheMissCount=").append(this.cacheMissCount)
				.append(",cachePutCount=").append(this.cachePutCount)
				.append(",executionCount=").append(this.executionCount)
				.append(",executionRowCount=").append(this.executionRowCount)
				.append(",executionAvgTime=").append(this.executionAvgTime)
				.append(",executionMaxTime=").append(this.executionMaxTime)
				.append(",executionMinTime=").append(this.executionMinTime)
				.append(']')
				.toString();
	}

}

/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.stat;

/**
 * Statistics SPI for the Hibernate core
 * 
 * @author Emmanuel Bernard
 */
public interface StatisticsImplementor {
	public void openSession();
	public void closeSession();
	public void flush();
	public void connect();
	public void loadEntity(String entityName);
	public void fetchEntity(String entityName);
	public void updateEntity(String entityName);
	public void insertEntity(String entityName);
	public void deleteEntity(String entityName);
	public void loadCollection(String role);
	public void fetchCollection(String role);
	public void updateCollection(String role);
	public void recreateCollection(String role);
	public void removeCollection(String role);
	public void secondLevelCachePut(String regionName);
	public void secondLevelCacheHit(String regionName);
	public void secondLevelCacheMiss(String regionName);
	public void queryExecuted(String hql, int rows, long time);
	public void queryCacheHit(String hql, String regionName);
	public void queryCacheMiss(String hql, String regionName);
	public void queryCachePut(String hql, String regionName);
	public void endTransaction(boolean success);
	public void closeStatement();
	public void prepareStatement();
	public void optimisticFailure(String entityName);
}
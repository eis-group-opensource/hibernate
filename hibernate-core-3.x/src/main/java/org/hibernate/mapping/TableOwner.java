/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.mapping;

/**
 * Interface allowing to differenciate SubClasses
 * from Classes, JoinedSubClasses and UnionSubClasses
 * The first one has not its own table while the others have
 * 
 * @author Emmanuel Bernard
 */
public interface TableOwner {
	void setTable(Table table);
}

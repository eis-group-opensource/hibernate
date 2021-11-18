/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.hql.ast.tree;

import org.hibernate.type.Type;
import org.hibernate.persister.collection.QueryableCollection;

/**
 * TODO : javadoc
 *
 * @author Steve Ebersole
 */
public class MapKeyNode extends AbstractMapComponentNode {
	protected String expressionDescription() {
		return "key(*)";
	}

	protected String[] resolveColumns(QueryableCollection collectionPersister) {
		return collectionPersister.getIndexColumnNames();
	}

	protected Type resolveType(QueryableCollection collectionPersister) {
		return collectionPersister.getIndexType();
	}
}

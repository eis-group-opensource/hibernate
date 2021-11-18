/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.hql.classic;

import org.hibernate.QueryException;
import org.hibernate.persister.collection.CollectionPropertyNames;
import org.hibernate.type.Type;

public class FromPathExpressionParser extends PathExpressionParser {

	public void end(QueryTranslatorImpl q) throws QueryException {
		if ( !isCollectionValued() ) {
			Type type = getPropertyType();
			if ( type.isEntityType() ) {
				// "finish off" the join
				token( ".", q );
				token( null, q );
			}
			else if ( type.isCollectionType() ) {
				// default to element set if no elements() specified
				token( ".", q );
				token( CollectionPropertyNames.COLLECTION_ELEMENTS, q );
			}
		}
		super.end( q );
	}

	protected void setExpectingCollectionIndex() throws QueryException {
		throw new QueryException( "illegal syntax near collection-valued path expression in from: "  + getCollectionName() );
	}


}

/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.id.insert;

import org.hibernate.dialect.Dialect;

/**
 * Specialized IdentifierGeneratingInsert which appends the database
 * specific clause which signifies to return generated IDENTITY values
 * to the end of the insert statement.
 * 
 * @author Steve Ebersole
 */
public class InsertSelectIdentityInsert extends IdentifierGeneratingInsert {
	public InsertSelectIdentityInsert(Dialect dialect) {
		super( dialect );
	}

	public String toStatementString() {
		return getDialect().appendIdentitySelectToInsert( super.toStatementString() );
	}
}

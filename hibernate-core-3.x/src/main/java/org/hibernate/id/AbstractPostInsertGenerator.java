/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.id;

import java.io.Serializable;

import org.hibernate.engine.SessionImplementor;

/**
 * Basic implementation of the {@link PostInsertIdentifierGenerator}
 * contract.
 *
 * @author Gavin King
 */
public abstract class AbstractPostInsertGenerator implements PostInsertIdentifierGenerator {
	/**
	 * {@inheritDoc} 
	 */
	public Serializable generate(SessionImplementor s, Object obj) {
		return IdentifierGeneratorHelper.POST_INSERT_INDICATOR;
	}
}

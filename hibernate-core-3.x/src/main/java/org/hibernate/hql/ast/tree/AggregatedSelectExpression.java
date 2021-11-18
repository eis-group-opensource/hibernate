/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.hql.ast.tree;

import java.util.List;

import org.hibernate.transform.ResultTransformer;

/**
 * Contract for a select expression which aggregates other select expressions together into a single return
 *
 * @author Steve Ebersole
 */
public interface AggregatedSelectExpression extends SelectExpression {
	/**
	 * Retrieves a list of the selection {@link org.hibernate.type.Type types} being aggregated
	 *
	 * @return The list of types.
	 */
	public List getAggregatedSelectionTypeList();

	/**
	 * Retrieve the aliases for the columns aggregated here.
	 *
	 * @return The column aliases.
	 */
	public String[] getAggregatedAliases();

	/**
	 * Retrieve the {@link ResultTransformer} responsible for building aggregated select expression results into their
	 * aggregated form.
	 *
	 * @return The appropriate transformer
	 */
	public ResultTransformer getResultTransformer();
}

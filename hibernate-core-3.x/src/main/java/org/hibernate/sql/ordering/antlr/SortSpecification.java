/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.sql.ordering.antlr;

import antlr.collections.AST;

/**
 * Models each sorting exprersion.
 *
 * @author Steve Ebersole
 */
public class SortSpecification extends NodeSupport {
	/**
	 * Locate the specified {@link SortKey}.
	 *
	 * @return The sort key.
	 */
	public SortKey getSortKey() {
		return ( SortKey ) getFirstChild();
	}

	/**
	 * Locate the specified <tt>collation specification</tt>, if one.
	 *
	 * @return The <tt>collation specification</tt>, or null if none was specified.
	 */
	public CollationSpecification getCollation() {
		AST possible = getSortKey().getNextSibling();
		return  possible != null && OrderByTemplateTokenTypes.COLLATE == possible.getType()
				? ( CollationSpecification ) possible
				: null;
	}

	/**
	 * Locate the specified <tt>ordering specification</tt>, if one.
	 *
	 * @return The <tt>ordering specification</tt>, or null if none was specified.
	 */
	public OrderingSpecification getOrdering() {
		// IMPL NOTE : the ordering-spec would be either the 2nd or 3rd child (of the overall sort-spec), if it existed,
		// 		depending on whether a collation-spec was specified.

		AST possible = getSortKey().getNextSibling();
		if ( possible == null ) {
			// There was no sort-spec parts specified other then the sort-key so there can be no ordering-spec...
			return null;
		}

		if ( OrderByTemplateTokenTypes.COLLATE == possible.getType() ) {
			// the 2nd child was a collation-spec, so we need to check the 3rd child instead.
			possible = possible.getNextSibling();
		}

		return possible != null && OrderByTemplateTokenTypes.ORDER_SPEC == possible.getType()
				?  ( OrderingSpecification ) possible
				:  null;
	}
}

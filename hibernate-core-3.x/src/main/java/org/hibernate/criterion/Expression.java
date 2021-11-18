/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.criterion;

import org.hibernate.type.Type;
import org.hibernate.util.ArrayHelper;

/**
 * @deprecated Use <tt>Restrictions</tt>.
 * @see Restrictions
 * @author Gavin King
 */
public final class Expression extends Restrictions {

	private Expression() {
		//cannot be instantiated
	}

	/**
	 * Apply a constraint expressed in SQL, with the given JDBC
	 * parameters. Any occurrences of <tt>{alias}</tt> will be
	 * replaced by the table alias.
	 *
	 * @deprecated use {@link org.hibernate.criterion.Restrictions#sqlRestriction(String, Object[], Type[])}
	 * @param sql
	 * @param values
	 * @param types
	 * @return Criterion
	 */
	public static Criterion sql(String sql, Object[] values, Type[] types) {
		return new SQLCriterion(sql, values, types);
	}
	/**
	 * Apply a constraint expressed in SQL, with the given JDBC
	 * parameter. Any occurrences of <tt>{alias}</tt> will be replaced
	 * by the table alias.
	 *
	 * @deprecated use {@link org.hibernate.criterion.Restrictions#sqlRestriction(String, Object, Type)}
	 * @param sql
	 * @param value
	 * @param type
	 * @return Criterion
	 */
	public static Criterion sql(String sql, Object value, Type type) {
		return new SQLCriterion(sql, new Object[] { value }, new Type[] { type } );
	}
	/**
	 * Apply a constraint expressed in SQL. Any occurrences of <tt>{alias}</tt>
	 * will be replaced by the table alias.
	 *
	 * @deprecated use {@link org.hibernate.criterion.Restrictions#sqlRestriction(String)}
	 * @param sql
	 * @return Criterion
	 */
	public static Criterion sql(String sql) {
		return new SQLCriterion(sql, ArrayHelper.EMPTY_OBJECT_ARRAY, ArrayHelper.EMPTY_TYPE_ARRAY);
	}

}

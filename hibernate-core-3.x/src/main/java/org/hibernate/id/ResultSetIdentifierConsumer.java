/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.id;

import java.sql.ResultSet;
import java.io.Serializable;

/**
 * An optional contract for {@link org.hibernate.type.Type} or
 * {@link org.hibernate.usertype.UserType} implementations to handle generated
 * id values any way they see fit as opposed to being limited to the discrete set of
 * numeric types handled by {@link IdentifierGeneratorHelper}
 *
 * @author Steve Ebersole
 */
public interface ResultSetIdentifierConsumer {
	/**
	 * Given a result set, consume/extract the necessary values and construct an
	 * appropriate identifier value.
	 *
	 * @param resultSet The result set containing the value(s) to be used in building
	 * the identifier value.
	 * @return The identifier value.
	 */
	public Serializable consumeIdentifier(ResultSet resultSet);
}

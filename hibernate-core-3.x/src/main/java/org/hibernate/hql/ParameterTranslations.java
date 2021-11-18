/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.hql;

import org.hibernate.type.Type;
import java.util.Set;

/**
 * Defines available information about the parameters encountered during
 * query translation.
 *
 * @author Steve Ebersole
 */
public interface ParameterTranslations {

	public boolean supportsOrdinalParameterMetadata();

	public int getOrdinalParameterCount();

	public int getOrdinalParameterSqlLocation(int ordinalPosition);

	public Type getOrdinalParameterExpectedType(int ordinalPosition);

	public Set getNamedParameterNames();

	public int[] getNamedParameterSqlLocations(String name);

	public Type getNamedParameterExpectedType(String name);
}

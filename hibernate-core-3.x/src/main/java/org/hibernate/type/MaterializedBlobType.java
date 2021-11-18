/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.type;

import java.sql.Types;

/**
 * <tt>materialized_blob</tt>: A type that maps an SQL BLOB to Java byte[].
 *
 * @author Gail Badner
 */
public class MaterializedBlobType extends AbstractLongBinaryType {

	public int sqlType() {
		return Types.BLOB;
	}

	public String getName() {
		return "materialized_blob";
	}
}

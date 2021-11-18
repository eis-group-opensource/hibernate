/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.id.insert;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Steve Ebersole
 */
public interface Binder {
	public void bindValues(PreparedStatement ps) throws SQLException;
	public Object getEntity();
}

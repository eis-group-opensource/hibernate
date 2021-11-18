/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate;

import java.sql.SQLException;

/**
 *
 * Thrown when a pessimistic locking conflict occurs.
 *
 * @author Scott Marlow
 */
public class PessimisticLockException extends JDBCException {

	Object entity;


	public PessimisticLockException(String s, JDBCException je, Object entity) {
			super(s, je.getSQLException());
			this.entity = entity;
		}

   public PessimisticLockException(String s, SQLException se, Object entity) {
         super(s, se);
         this.entity = entity;
      }

	public PessimisticLockException(String s, SQLException se, String sql) {
			super(s, se, sql);
			this.entity = null;
		}

	public Object getEntity() {
		return entity;
	}

}
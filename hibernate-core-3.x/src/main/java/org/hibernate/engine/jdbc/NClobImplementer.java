/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.engine.jdbc;

/**
 * Marker interface for non-contextually created java.sql.NClob instances..
 * <p/>
 * java.sql.NClob is a new type introduced in JDK 1.6 (JDBC 4)
 *
 * @author Steve Ebersole
 */
public interface NClobImplementer extends ClobImplementer {
}

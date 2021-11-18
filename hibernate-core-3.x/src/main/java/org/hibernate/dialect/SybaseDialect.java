/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.dialect;

/**
 * This dialect is being deprecated; it had been used both as the base class
 * for TransactSQL-based dialects as well as the physical dialect for handling
 * Sybase.  Those functions have now been split.
 * {@link AbstractTransactSQLDialect} should be used as the base class for 
 * TransactSQL-based dialects.
 *
 * @author Gail Badner
 * @deprecated use {@link AbstractTransactSQLDialect},
 * {@link SybaseASE15Dialect} or {@link SQLServerDialect} instead depending on
 * need.
 */
public class SybaseDialect extends AbstractTransactSQLDialect {
}

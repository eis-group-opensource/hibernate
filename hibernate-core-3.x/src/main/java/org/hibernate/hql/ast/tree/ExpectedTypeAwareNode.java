/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.hql.ast.tree;

import org.hibernate.type.Type;

/**
 * Interface for nodes which wish to be made aware of any determined "expected
 * type" based on the context within they appear in the query.
 *
 * @author Steve Ebersole
 */
public interface ExpectedTypeAwareNode {
	public void setExpectedType(Type expectedType);
	public Type getExpectedType();
}

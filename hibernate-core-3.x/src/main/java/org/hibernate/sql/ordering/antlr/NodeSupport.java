/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.sql.ordering.antlr;

import antlr.CommonAST;

/**
 * Basic implementation of a {@link Node}.
 *
 * @author Steve Ebersole
 */
public class NodeSupport extends CommonAST implements Node {
	/**
	 * {@inheritDoc}
	 */
	public String getDebugText() {
		return getText();
	}

	/**
	 * {@inheritDoc}
	 */
	public String getRenderableText() {
		return getText();
	}
}

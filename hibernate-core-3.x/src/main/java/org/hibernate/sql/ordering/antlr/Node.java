/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.sql.ordering.antlr;

/**
 * General contract for AST nodes.
 *
 * @author Steve Ebersole
 */
public interface Node {
	/**
	 * Get the intrinsic text of this node.
	 *
	 * @return The node's text.
	 */
	public String getText();

	/**
	 * Get a string representation of this node usable for debug logging or similar.
	 *
	 * @return The node's debugging text.
	 */
	public String getDebugText();

	/**
	 * Build the node's representation for use in the resulting rendering.
	 *
	 * @return The renderable text.
	 */
	public String getRenderableText();
}

/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.hql.ast.tree;

/**
 * Contract for nodes representing unary operators.
 *
 * @author Steve Ebersole
 */
public interface UnaryOperatorNode extends OperatorNode {
	/**
	 * Retrievs the node representing the operator's single operand.
	 * 
	 * @return The operator's single operand.
	 */
	public Node getOperand();
}

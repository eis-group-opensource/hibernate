/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.hql.ast.tree;

import org.hibernate.type.Type;
import org.hibernate.Hibernate;
import antlr.SemanticException;

/**
 * Contract for nodes representing logcial BETWEEN (ternary) operators.
 *
 * @author Steve Ebersole
 */
public class BetweenOperatorNode extends SqlNode implements OperatorNode {

	public void initialize() throws SemanticException {
		Node fixture = getFixtureOperand();
		if ( fixture == null ) {
			throw new SemanticException( "fixture operand of a between operator was null" );
		}
		Node low = getLowOperand();
		if ( low == null ) {
			throw new SemanticException( "low operand of a between operator was null" );
		}
		Node high = getHighOperand();
		if ( high == null ) {
			throw new SemanticException( "high operand of a between operator was null" );
		}
		check( fixture, low, high );
		check( low, high, fixture );
		check( high, fixture, low );
	}

	public Type getDataType() {
		// logic operators by definition resolve to boolean.
		return Hibernate.BOOLEAN;
	}

	public Node getFixtureOperand() {
		return ( Node ) getFirstChild();
	}

	public Node getLowOperand() {
		return ( Node ) getFirstChild().getNextSibling();
	}

	public Node getHighOperand() {
		return ( Node ) getFirstChild().getNextSibling().getNextSibling();
	}

	private void check(Node check, Node first, Node second) {
		if ( ExpectedTypeAwareNode.class.isAssignableFrom( check.getClass() ) ) {
			Type expectedType = null;
			if ( SqlNode.class.isAssignableFrom( first.getClass() ) ) {
				expectedType = ( ( SqlNode ) first ).getDataType();
			}
			if ( expectedType == null && SqlNode.class.isAssignableFrom( second.getClass() ) ) {
				expectedType = ( ( SqlNode ) second ).getDataType();
			}
			( ( ExpectedTypeAwareNode ) check ).setExpectedType( expectedType );
		}
	}
}

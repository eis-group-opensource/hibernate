/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.hql.ast.tree;

import java.util.List;
import java.util.ArrayList;

import org.hibernate.sql.JoinFragment;
import org.hibernate.param.ParameterSpecification;

/**
 * Represents an SQL fragment in the AST.
 *
 * @author josh
 */
public class SqlFragment extends Node implements ParameterContainer {
	private JoinFragment joinFragment;
	private FromElement fromElement;

	public void setJoinFragment(JoinFragment joinFragment) {
		this.joinFragment = joinFragment;
	}

	public boolean hasFilterCondition() {
		return joinFragment.hasFilterCondition();
	}

	public void setFromElement(FromElement fromElement) {
		this.fromElement = fromElement;
	}

	public FromElement getFromElement() {
		return fromElement;
	}


	// ParameterContainer impl ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	private List embeddedParameters;

	public void addEmbeddedParameter(ParameterSpecification specification) {
		if ( embeddedParameters == null ) {
			embeddedParameters = new ArrayList();
		}
		embeddedParameters.add( specification );
	}

	public boolean hasEmbeddedParameters() {
		return embeddedParameters != null && ! embeddedParameters.isEmpty();
	}

	public ParameterSpecification[] getEmbeddedParameters() {
		return ( ParameterSpecification[] ) embeddedParameters.toArray( new ParameterSpecification[ embeddedParameters.size() ] );
	}
}

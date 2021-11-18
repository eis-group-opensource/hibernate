/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.sql.ordering.antlr;

import antlr.collections.AST;

import org.hibernate.util.StringHelper;
import org.hibernate.hql.ast.util.ASTPrinter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO : javadoc
 *
 * @author Steve Ebersole
 */
public class OrderByFragmentRenderer extends GeneratedOrderByFragmentRenderer {
	private static final Logger log = LoggerFactory.getLogger( OrderByFragmentRenderer.class );
	private static final ASTPrinter printer = new ASTPrinter( GeneratedOrderByFragmentRendererTokenTypes.class );

	protected void out(AST ast) {
		out( ( ( Node ) ast ).getRenderableText() );
	}


	// handle trace logging ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    private int traceDepth = 0;

	public void traceIn(String ruleName, AST tree) {
		if ( inputState.guessing > 0 ) {
			return;
		}
		String prefix = StringHelper.repeat( '-', (traceDepth++ * 2) ) + "-> ";
		String traceText = ruleName + " (" + buildTraceNodeName(tree) + ")";
		log.trace( prefix + traceText );
	}

	private String buildTraceNodeName(AST tree) {
		return tree == null
				? "???"
				: tree.getText() + " [" + printer.getTokenTypeName( tree.getType() ) + "]";
	}

	public void traceOut(String ruleName, AST tree) {
		if ( inputState.guessing > 0 ) {
			return;
		}
		String prefix = "<-" + StringHelper.repeat( '-', (--traceDepth * 2) ) + " ";
		log.trace( prefix + ruleName );
	}
}

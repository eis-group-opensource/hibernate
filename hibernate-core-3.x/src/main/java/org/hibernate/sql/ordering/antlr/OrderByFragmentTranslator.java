/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.sql.ordering.antlr;

import java.io.StringReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.hibernate.HibernateException;
import org.hibernate.hql.ast.util.ASTPrinter;

/**
 * A translator which coordinates translation of an <tt>order-by</tt> mapping.
 *
 * @author Steve Ebersole
 */
public class OrderByFragmentTranslator {
	private static final Logger log = LoggerFactory.getLogger( OrderByFragmentTranslator.class );

	public final TranslationContext context;

	public OrderByFragmentTranslator(TranslationContext context) {
		this.context = context;
	}

	/**
	 * The main contract, performing the transaction.
	 *
	 * @param fragment The <tt>order-by</tt> mapping fragment to be translated.
	 *
	 * @return The translated fragment.
	 */
	public String render(String fragment) {
		GeneratedOrderByLexer lexer = new GeneratedOrderByLexer( new StringReader( fragment ) );
		OrderByFragmentParser parser = new OrderByFragmentParser( lexer, context );
		try {
			parser.orderByFragment();
		}
		catch ( HibernateException e ) {
			throw e;
		}
		catch ( Throwable t ) {
			throw new HibernateException( "Unable to parse order-by fragment", t );
		}

		if ( log.isTraceEnabled() ) {
			ASTPrinter printer = new ASTPrinter( OrderByTemplateTokenTypes.class );
			log.trace( printer.showAsString( parser.getAST(), "--- {order-by fragment} ---" ) );
		}

		OrderByFragmentRenderer renderer = new OrderByFragmentRenderer();
		try {
			renderer.orderByFragment( parser.getAST() );
		}
		catch ( HibernateException e ) {
			throw e;
		}
		catch ( Throwable t ) {
			throw new HibernateException( "Unable to render parsed order-by fragment", t );
		}

		return renderer.getRenderedFragment();
	}
}

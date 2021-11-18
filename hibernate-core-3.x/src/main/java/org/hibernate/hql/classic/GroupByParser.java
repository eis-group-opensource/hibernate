/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.hql.classic;

import org.hibernate.QueryException;
import org.hibernate.util.StringHelper;

/**
 * Parses the GROUP BY clause of an aggregate query
 */
public class GroupByParser implements Parser {

	//this is basically a copy/paste of OrderByParser ... might be worth refactoring

	// This uses a PathExpressionParser but notice that compound paths are not valid,
	// only bare names and simple paths:

	// SELECT p FROM p IN CLASS eg.Person GROUP BY p.Name, p.Address, p

	// The reason for this is SQL doesn't let you sort by an expression you are
	// not returning in the result set.

	private final PathExpressionParser pathExpressionParser;

	{
		pathExpressionParser = new PathExpressionParser();
		pathExpressionParser.setUseThetaStyleJoin( true ); //TODO: would be nice to use false, but issues with MS SQL
	}

	public void token(String token, QueryTranslatorImpl q) throws QueryException {

		if ( q.isName( StringHelper.root( token ) ) ) {
			ParserHelper.parse( pathExpressionParser, q.unalias( token ), ParserHelper.PATH_SEPARATORS, q );
			q.appendGroupByToken( pathExpressionParser.getWhereColumn() );
			pathExpressionParser.addAssociation( q );
		}
		else {
			q.appendGroupByToken( token );
		}
	}

	public void start(QueryTranslatorImpl q) throws QueryException {
	}

	public void end(QueryTranslatorImpl q) throws QueryException {
	}


}






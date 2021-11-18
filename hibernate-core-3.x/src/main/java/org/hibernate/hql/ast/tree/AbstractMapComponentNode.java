/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.hql.ast.tree;

import java.util.Map;

import antlr.SemanticException;
import antlr.collections.AST;

import org.hibernate.hql.antlr.HqlSqlTokenTypes;
import org.hibernate.hql.ast.util.ColumnHelper;
import org.hibernate.type.CollectionType;
import org.hibernate.type.Type;
import org.hibernate.persister.collection.QueryableCollection;
import org.hibernate.util.StringHelper;

/**
 * TODO : javadoc
 *
 * @author Steve Ebersole
 */
public abstract class AbstractMapComponentNode extends FromReferenceNode implements HqlSqlTokenTypes {
	private String[] columns;

	public FromReferenceNode getMapReference() {
		return ( FromReferenceNode ) getFirstChild();
	}

	public String[] getColumns() {
		return columns;
	}

	public void setScalarColumnText(int i) throws SemanticException {
		ColumnHelper.generateScalarColumns( this, getColumns(), i );
	}

	public void resolve(
			boolean generateJoin,
			boolean implicitJoin,
			String classAlias,
			AST parent) throws SemanticException {
		if ( parent != null ) {
			throw attemptedDereference();
		}

		FromReferenceNode mapReference = getMapReference();
		mapReference.resolve( true, true );
		if ( mapReference.getDataType().isCollectionType() ) {
			CollectionType collectionType = (CollectionType) mapReference.getDataType();
			if ( Map.class.isAssignableFrom( collectionType.getReturnedClass() ) ) {
				FromElement sourceFromElement = mapReference.getFromElement();
				setFromElement( sourceFromElement );
				setDataType( resolveType( sourceFromElement.getQueryableCollection() ) );
				this.columns = resolveColumns( sourceFromElement.getQueryableCollection() );
				initText( this.columns );
				setFirstChild( null );
				return;
			}
		}

		throw nonMap();
	}

	private void initText(String[] columns) {
		String text = StringHelper.join( ", ", columns );
		if ( columns.length > 1 && getWalker().isComparativeExpressionClause() ) {
			text = "(" + text + ")";
		}
		setText( text );
	}

	protected abstract String expressionDescription();
	protected abstract String[] resolveColumns(QueryableCollection collectionPersister);
	protected abstract Type resolveType(QueryableCollection collectionPersister);

	protected SemanticException attemptedDereference() {
		return new SemanticException( expressionDescription() + " expression cannot be further de-referenced" );
	}

	protected SemanticException nonMap() {
		return new SemanticException( expressionDescription() + " expression did not reference map property" );
	}

	public void resolveIndex(AST parent) throws SemanticException {
		throw new UnsupportedOperationException( expressionDescription() + " expression cannot be the source for an index operation" );
	}
}

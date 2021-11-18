/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.hql.ast.tree;

import org.hibernate.type.ComponentType;
import org.hibernate.type.Type;
import org.hibernate.persister.collection.QueryableCollection;
import org.hibernate.persister.entity.PropertyMapping;
import org.hibernate.persister.entity.EntityPersister;
import org.hibernate.QueryException;
import org.hibernate.util.StringHelper;
import org.hibernate.hql.NameGenerator;

/**
 * Models an explicit join terminating at a component value (e.g. <tt>... from Person p join p.name as n ...</tt>)
 *
 * @author Steve Ebersole
 */
public class ComponentJoin extends FromElement {
	private final String componentPath;
	private final ComponentType componentType;

	private final String componentProperty;
	private final String columns;

	public ComponentJoin(
			FromClause fromClause,
			FromElement origin,
			String alias,
			String componentPath,
			ComponentType componentType) {
		super( fromClause, origin, alias );
		this.componentPath = componentPath;
		this.componentType = componentType;
		this.componentProperty = StringHelper.unqualify( componentPath );
		fromClause.addJoinByPathMap( componentPath, this );
		initializeComponentJoin( new ComponentFromElementType( this ) );

		final String[] cols = origin.getPropertyMapping( "" ).toColumns( getTableAlias(), componentProperty );
		StringBuffer buf = new StringBuffer();
		for ( int j = 0; j < cols.length; j++ ) {
			final String column = cols[j];
			if ( j > 0 ) {
				buf.append( ", " );
			}
			buf.append( column );
		}
		this.columns = buf.toString();
	}

	public String getComponentPath() {
		return componentPath;
	}

	public String getComponentProperty() {
		return componentProperty;
	}

	public ComponentType getComponentType() {
		return componentType;
	}


	public Type getDataType() {
		return getComponentType();
	}

	/**
	 * {@inheritDoc}
	 */
	public String getIdentityColumn() {
		// used to "resolve" the IdentNode when our alias is encountered *by itself* in the query; so
		//		here we use the component
		// NOTE : ^^ is true *except for* when encountered by itself in the SELECT clause.  That gets
		// 		routed through org.hibernate.hql.ast.tree.ComponentJoin.ComponentFromElementType.renderScalarIdentifierSelect()
		//		which we also override to account for
		return columns;
	}

	/**
	 * {@inheritDoc}
	 */
	public String getDisplayText() {
		return "ComponentJoin{path=" + getComponentPath() + ", type=" + componentType.getReturnedClass() + "}";
	}

	public class ComponentFromElementType extends FromElementType {
		private final PropertyMapping propertyMapping = new ComponentPropertyMapping();

		public ComponentFromElementType(FromElement fromElement) {
			super( fromElement );
		}

		public Type getDataType() {
			return getComponentType();
		}

		/**
		 * {@inheritDoc}
		 */
		public QueryableCollection getQueryableCollection() {
			return null;
		}

		/**
		 * {@inheritDoc}
		 */
		public PropertyMapping getPropertyMapping(String propertyName) {
			return propertyMapping;
		}

		/**
		 * {@inheritDoc}
		 */
		public Type getPropertyType(String propertyName, String propertyPath) {
			int index = getComponentType().getPropertyIndex( propertyName );
			return getComponentType().getSubtypes()[index];
		}

		public String renderScalarIdentifierSelect(int i) {
			String[] cols = getBasePropertyMapping().toColumns( getTableAlias(), getComponentProperty() );
			StringBuffer buf = new StringBuffer();
			// For property references generate <tablealias>.<columnname> as <projectionalias>
			for ( int j = 0; j < cols.length; j++ ) {
				final String column = cols[j];
				if ( j > 0 ) {
					buf.append( ", " );
				}
				buf.append( column ).append( " as " ).append( NameGenerator.scalarName( i, j ) );
			}
			return buf.toString();
		}
	}

	protected PropertyMapping getBasePropertyMapping() {
		return getOrigin().getPropertyMapping( "" );
	}

	private final class ComponentPropertyMapping implements PropertyMapping {
		public Type getType() {
			return getComponentType();
		}

		public Type toType(String propertyName) throws QueryException {
			return getBasePropertyMapping().toType( getPropertyPath( propertyName ) );
		}

		protected String getPropertyPath(String propertyName) {
			return getComponentPath() + '.' + propertyName;
		}

		public String[] toColumns(String alias, String propertyName) throws QueryException {
			return getBasePropertyMapping().toColumns( alias, getPropertyPath( propertyName ) );
		}

		public String[] toColumns(String propertyName) throws QueryException, UnsupportedOperationException {
			return getBasePropertyMapping().toColumns( getPropertyPath( propertyName ) );
		}
	}
}

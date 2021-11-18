/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.tuple.entity;

import java.util.Map;

import org.hibernate.EntityMode;
import org.hibernate.HibernateException;
import org.hibernate.EntityNameResolver;
import org.hibernate.engine.SessionFactoryImplementor;
import org.hibernate.tuple.Instantiator;
import org.hibernate.tuple.DynamicMapInstantiator;
import org.hibernate.mapping.PersistentClass;
import org.hibernate.mapping.Property;
import org.hibernate.property.Getter;
import org.hibernate.property.PropertyAccessor;
import org.hibernate.property.PropertyAccessorFactory;
import org.hibernate.property.Setter;
import org.hibernate.proxy.map.MapProxyFactory;
import org.hibernate.proxy.ProxyFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * An {@link EntityTuplizer} specific to the dynamic-map entity mode.
 *
 * @author Steve Ebersole
 * @author Gavin King
 */
public class DynamicMapEntityTuplizer extends AbstractEntityTuplizer {

	static final Logger log = LoggerFactory.getLogger( DynamicMapEntityTuplizer.class );

	DynamicMapEntityTuplizer(EntityMetamodel entityMetamodel, PersistentClass mappedEntity) {
		super(entityMetamodel, mappedEntity);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public EntityMode getEntityMode() {
		return EntityMode.MAP;
	}

	private PropertyAccessor buildPropertyAccessor(Property mappedProperty) {
		if ( mappedProperty.isBackRef() ) {
			return mappedProperty.getPropertyAccessor(null);
		}
		else {
			return PropertyAccessorFactory.getDynamicMapPropertyAccessor();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	protected Getter buildPropertyGetter(Property mappedProperty, PersistentClass mappedEntity) {
		return buildPropertyAccessor(mappedProperty).getGetter( null, mappedProperty.getName() );
	}

	/**
	 * {@inheritDoc}
	 */
	protected Setter buildPropertySetter(Property mappedProperty, PersistentClass mappedEntity) {
		return buildPropertyAccessor(mappedProperty).getSetter( null, mappedProperty.getName() );
	}

	/**
	 * {@inheritDoc}
	 */
	protected Instantiator buildInstantiator(PersistentClass mappingInfo) {
        return new DynamicMapInstantiator( mappingInfo );
	}

	/**
	 * {@inheritDoc}
	 */
	protected ProxyFactory buildProxyFactory(PersistentClass mappingInfo, Getter idGetter, Setter idSetter) {

		ProxyFactory pf = new MapProxyFactory();
		try {
			//TODO: design new lifecycle for ProxyFactory
			pf.postInstantiate(
					getEntityName(),
					null,
					null,
					null,
					null,
					null
			);
		}
		catch ( HibernateException he ) {
			log.warn( "could not create proxy factory for:" + getEntityName(), he );
			pf = null;
		}
		return pf;
	}

	/**
	 * {@inheritDoc}
	 */
	public Class getMappedClass() {
		return Map.class;
	}

	/**
	 * {@inheritDoc}
	 */
	public Class getConcreteProxyClass() {
		return Map.class;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean isInstrumented() {
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	public EntityNameResolver[] getEntityNameResolvers() {
		return new EntityNameResolver[] { BasicEntityNameResolver.INSTANCE };
	}

	/**
	 * {@inheritDoc}
	 */
	public String determineConcreteSubclassEntityName(Object entityInstance, SessionFactoryImplementor factory) {
		return extractEmbeddedEntityName( ( Map ) entityInstance );
	}

	public static String extractEmbeddedEntityName(Map entity) {
		return ( String ) entity.get( DynamicMapInstantiator.KEY );
	}

	public static class BasicEntityNameResolver implements EntityNameResolver {
		public static final BasicEntityNameResolver INSTANCE = new BasicEntityNameResolver();

		/**
		 * {@inheritDoc}
		 */
		public String resolveEntityName(Object entity) {
			final String entityName = extractEmbeddedEntityName( ( Map ) entity );
			if ( entityName == null ) {
				throw new HibernateException( "Could not determine type of dynamic map entity" );
			}
			return entityName;
		}

		/**
		 * {@inheritDoc}
		 */
		public boolean equals(Object obj) {
			return getClass().equals( obj.getClass() );
		}

		/**
		 * {@inheritDoc}
		 */
		public int hashCode() {
			return getClass().hashCode();
		}
	}
}

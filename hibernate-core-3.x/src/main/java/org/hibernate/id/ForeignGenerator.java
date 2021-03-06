/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.id;

import java.io.Serializable;
import java.util.Properties;

import org.hibernate.MappingException;
import org.hibernate.Session;
import org.hibernate.TransientObjectException;
import org.hibernate.dialect.Dialect;
import org.hibernate.engine.ForeignKeys;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.persister.entity.EntityPersister;
import org.hibernate.type.EntityType;
import org.hibernate.type.Type;

/**
 * <b>foreign</b><br>
 * <br>
 * An <tt>Identifier</tt> generator that uses the value of the id property of an
 * associated object<br>
 * <br>
 * One mapping parameter is required: property.
 *
 * @author Gavin King
 */
public class ForeignGenerator implements IdentifierGenerator, Configurable {
	private String entityName;
	private String propertyName;

	/**
	 * Getter for property 'entityName'.
	 *
	 * @return Value for property 'entityName'.
	 */
	public String getEntityName() {
		return entityName;
	}

	/**
	 * Getter for property 'propertyName'.
	 *
	 * @return Value for property 'propertyName'.
	 */
	public String getPropertyName() {
		return propertyName;
	}

	/**
	 * Getter for property 'role'.  Role is the {@link #getPropertyName property name} qualified by the
	 * {@link #getEntityName entity name}.
	 *
	 * @return Value for property 'role'.
	 */
	public String getRole() {
		return getEntityName() + '.' + getPropertyName();
	}

	/**
	 * {@inheritDoc}
	 */
	public void configure(Type type, Properties params, Dialect d) {
		propertyName = params.getProperty( "property" );
		entityName = params.getProperty( ENTITY_NAME );
		if ( propertyName==null ) {
			throw new MappingException( "param named \"property\" is required for foreign id generation strategy" );
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public Serializable generate(SessionImplementor sessionImplementor, Object object) {
		Session session = ( Session ) sessionImplementor;

		final EntityPersister persister = sessionImplementor.getFactory().getEntityPersister( entityName );
		Object associatedObject = persister.getPropertyValue( object, propertyName, session.getEntityMode() );
		if ( associatedObject == null ) {
			throw new IdentifierGenerationException(
					"attempted to assign id from null one-to-one property [" + getRole() + "]"
			);
		}

		final EntityType foreignValueSourceType;
		final Type propertyType = persister.getPropertyType( propertyName );
		if ( propertyType.isEntityType() ) {
			// the normal case
			foreignValueSourceType = (EntityType) propertyType;
		}
		else {
			// try identifier mapper
			foreignValueSourceType = (EntityType) persister.getPropertyType( "_identifierMapper." + propertyName );
		}

		Serializable id;
		try {
			id = ForeignKeys.getEntityIdentifierIfNotUnsaved(
					foreignValueSourceType.getAssociatedEntityName(),
					associatedObject, 
					sessionImplementor
			);
		}
		catch (TransientObjectException toe) {
			id = session.save( foreignValueSourceType.getAssociatedEntityName(), associatedObject );
		}

		if ( session.contains(object) ) {
			//abort the save (the object is already saved by a circular cascade)
			return IdentifierGeneratorHelper.SHORT_CIRCUIT_INDICATOR;
			//throw new IdentifierGenerationException("save associated object first, or disable cascade for inverse association");
		}
		return id;
	}
}

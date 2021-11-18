/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.id.factory;

import java.util.Properties;
import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.hibernate.id.IdentifierGenerator;
import org.hibernate.id.UUIDHexGenerator;
import org.hibernate.id.TableHiLoGenerator;
import org.hibernate.id.Assigned;
import org.hibernate.id.IdentityGenerator;
import org.hibernate.id.SelectGenerator;
import org.hibernate.id.SequenceGenerator;
import org.hibernate.id.SequenceHiLoGenerator;
import org.hibernate.id.IncrementGenerator;
import org.hibernate.id.ForeignGenerator;
import org.hibernate.id.GUIDGenerator;
import org.hibernate.id.SequenceIdentityGenerator;
import org.hibernate.id.Configurable;
import org.hibernate.id.enhanced.SequenceStyleGenerator;
import org.hibernate.id.enhanced.TableGenerator;
import org.hibernate.type.Type;
import org.hibernate.util.FastHashMap;
import org.hibernate.util.ReflectHelper;
import org.hibernate.dialect.Dialect;
import org.hibernate.MappingException;

/**
 * Basic <tt>templated</tt> support for {@link IdentifierGeneratorFactory} implementations.
 *
 * @author Steve Ebersole
 */
public class DefaultIdentifierGeneratorFactory implements IdentifierGeneratorFactory, Serializable {
	private static final Logger log = LoggerFactory.getLogger( DefaultIdentifierGeneratorFactory.class );

	private transient Dialect dialect;
	private FastHashMap generatorStrategyToClassNameMap = new FastHashMap();

	/**
	 * Constructs a new DefaultIdentifierGeneratorFactory.
	 */
	public DefaultIdentifierGeneratorFactory() {
		register( "uuid", UUIDHexGenerator.class );
		register( "hilo", TableHiLoGenerator.class );
		register( "assigned", Assigned.class );
		register( "identity", IdentityGenerator.class );
		register( "select", SelectGenerator.class );
		register( "sequence", SequenceGenerator.class );
		register( "seqhilo", SequenceHiLoGenerator.class );
		register( "increment", IncrementGenerator.class );
		register( "foreign", ForeignGenerator.class );
		register( "guid", GUIDGenerator.class );
		register( "uuid.hex", UUIDHexGenerator.class ); 	// uuid.hex is deprecated
		register( "sequence-identity", SequenceIdentityGenerator.class );
		register( "enhanced-sequence", SequenceStyleGenerator.class );
		register( "enhanced-table", TableGenerator.class );
	}

	/**
	 * {@inheritDoc}
	 */
	public void setDialect(Dialect dialect) {
		log.debug( "Setting dialect [" + dialect + "]" );
		this.dialect = dialect;
	}

	public void register(String strategy, Class generatorClass) {
		String msg = "Registering IdentifierGenerator strategy [" + strategy + "] -> [" + generatorClass + "]";
		Object old = generatorStrategyToClassNameMap.put( strategy, generatorClass );
		if ( old != null ) {
			msg += ", overriding [" + old + "]";
		}
		log.debug( msg );
	}

	/**
	 * {@inheritDoc}
	 */
	public IdentifierGenerator createIdentifierGenerator(String strategy, Type type, Properties config) {
		try {
			Class clazz = getIdentifierGeneratorClass( strategy );
			IdentifierGenerator idgen = ( IdentifierGenerator ) clazz.newInstance();
			if ( idgen instanceof Configurable ) {
				( ( Configurable ) idgen ).configure( type, config, dialect );
			}
			return idgen;
		}
		catch ( Exception e ) {
			String msg = "Could not instantiate id generator [entity-name="
					+ config.get( IdentifierGenerator.ENTITY_NAME ) + "]";
			throw new MappingException( msg, e );
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public Class getIdentifierGeneratorClass(String strategy) {
		if ( "native".equals( strategy ) ) {
			return dialect.getNativeIdentifierGeneratorClass();
		}

		Class generatorClass = ( Class ) generatorStrategyToClassNameMap.get( strategy );
		try {
			if ( generatorClass == null ) {
				generatorClass = ReflectHelper.classForName( strategy );
			}
		}
		catch ( ClassNotFoundException e ) {
			throw new MappingException( "Could not interpret id generator strategy [" + strategy + "]" );
		}
		return generatorClass;
	}
}

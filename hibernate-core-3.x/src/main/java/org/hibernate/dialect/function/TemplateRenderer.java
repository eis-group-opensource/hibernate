/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.dialect.function;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.hibernate.engine.SessionFactoryImplementor;

/**
 * Delegate for handling function "templates".
 *
 * @author Steve Ebersole
 */
public class TemplateRenderer {
	private static final Logger log = LoggerFactory.getLogger( TemplateRenderer.class );

	private final String template;
	private final String[] chunks;
	private final int[] paramIndexes;

	public TemplateRenderer(String template) {
		this.template = template;

		List chunkList = new ArrayList();
		List paramList = new ArrayList();
		StringBuffer chunk = new StringBuffer( 10 );
		StringBuffer index = new StringBuffer( 2 );

		for ( int i = 0; i < template.length(); ++i ) {
			char c = template.charAt( i );
			if ( c == '?' ) {
				chunkList.add( chunk.toString() );
				chunk.delete( 0, chunk.length() );

				while ( ++i < template.length() ) {
					c = template.charAt( i );
					if ( Character.isDigit( c ) ) {
						index.append( c );
					}
					else {
						chunk.append( c );
						break;
					}
				}

				paramList.add( Integer.valueOf( index.toString() ) );
				index.delete( 0, index.length() );
			}
			else {
				chunk.append( c );
			}
		}

		if ( chunk.length() > 0 ) {
			chunkList.add( chunk.toString() );
		}

		chunks = (String[]) chunkList.toArray( new String[chunkList.size()] );
		paramIndexes = new int[paramList.size()];
		for ( int i = 0; i < paramIndexes.length; ++i ) {
			paramIndexes[i] = ( (Integer) paramList.get( i ) ).intValue();
		}
	}

	public String getTemplate() {
		return template;
	}

	public int getAnticipatedNumberOfArguments() {
		return paramIndexes.length;
	}

	public String render(List args, SessionFactoryImplementor factory) {
		int numberOfArguments = args.size();
		if ( getAnticipatedNumberOfArguments() > 0 && numberOfArguments != getAnticipatedNumberOfArguments() ) {
			log.warn(
					"Function template anticipated " + getAnticipatedNumberOfArguments()
							+ " arguments, but " + numberOfArguments + " arguments encountered"
			);
		}
		StringBuffer buf = new StringBuffer();
		for ( int i = 0; i < chunks.length; ++i ) {
			if ( i < paramIndexes.length ) {
				final int index = paramIndexes[i] - 1;
				final Object arg =  index < numberOfArguments ? args.get( index ) : null;
				if ( arg != null ) {
					buf.append( chunks[i] ).append( arg );
				}
			}
			else {
				buf.append( chunks[i] );
			}
		}
		return buf.toString();
	}
}

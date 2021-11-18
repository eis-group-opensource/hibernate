/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.engine;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A strategy for determining if an identifier value is an identifier of
 * a new transient instance or a previously persistent transient instance.
 * The strategy is determined by the <tt>unsaved-value</tt> attribute in
 * the mapping file.
 * 
 * @author Gavin King
 */
public class IdentifierValue {

	private static final Logger log = LoggerFactory.getLogger(IdentifierValue.class);
	
	private final Serializable value;
	
	/**
	 * Always assume the transient instance is newly instantiated
	 */
	public static final IdentifierValue ANY = new IdentifierValue() {
		public final Boolean isUnsaved(Serializable id) {
			log.trace("id unsaved-value strategy ANY");
			return Boolean.TRUE;
		}
		public Serializable getDefaultValue(Serializable currentValue) {
			return currentValue;
		}
		public String toString() {
			return "SAVE_ANY";
		}
	};
	
	/**
	 * Never assume the transient instance is newly instantiated
	 */
	public static final IdentifierValue NONE = new IdentifierValue() {
		public final Boolean isUnsaved(Serializable id) {
			log.trace("id unsaved-value strategy NONE");
			return Boolean.FALSE;
		}
		public Serializable getDefaultValue(Serializable currentValue) {
			return currentValue;
		}
		public String toString() {
			return "SAVE_NONE";
		}
	};
	
	/**
	 * Assume the transient instance is newly instantiated if the identifier
	 * is null.
	 */
	public static final IdentifierValue NULL = new IdentifierValue() {
		public final Boolean isUnsaved(Serializable id) {
			log.trace("id unsaved-value strategy NULL");
			return id==null ? Boolean.TRUE : Boolean.FALSE;
		}
		public Serializable getDefaultValue(Serializable currentValue) {
			return null;
		}
		public String toString() {
			return "SAVE_NULL";
		}
	};
	
	/**
	 * Assume nothing.
	 */
	public static final IdentifierValue UNDEFINED = new IdentifierValue() {
		public final Boolean isUnsaved(Serializable id) {
			log.trace("id unsaved-value strategy UNDEFINED");
			return null;
		}
		public Serializable getDefaultValue(Serializable currentValue) {
			return null;
		}
		public String toString() {
			return "UNDEFINED";
		}
	};
	
	protected IdentifierValue() {
		this.value = null;
	}
	
	/**
	 * Assume the transient instance is newly instantiated if
	 * its identifier is null or equal to <tt>value</tt>
	 */
	public IdentifierValue(Serializable value) {
		this.value = value;
	}
	
	/**
	 * Does the given identifier belong to a new instance?
	 */
	public Boolean isUnsaved(Serializable id) {
		if ( log.isTraceEnabled() ) log.trace("id unsaved-value: " + value);
		return id==null || id.equals(value) ? Boolean.TRUE : Boolean.FALSE;
	}
	
	public Serializable getDefaultValue(Serializable currentValue) {
		return value;
	}
	
	public String toString() {
		return "identifier unsaved-value: " + value;
	}
}
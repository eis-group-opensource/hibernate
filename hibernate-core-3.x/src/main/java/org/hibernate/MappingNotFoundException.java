/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate;

/**
 * Thrown when a resource for a mapping could not be found.
 * 
 * @author Max Rydahl Andersen
 */
public class MappingNotFoundException extends MappingException {

	private final String path;
	private final String type;

	public MappingNotFoundException(String customMessage, String type, String path, Throwable cause) {
		super(customMessage, cause);
		this.type=type;
		this.path=path;
	}
	
	public MappingNotFoundException(String customMessage, String type, String path) {
		super(customMessage);
		this.type=type;
		this.path=path;
	}
	
	public MappingNotFoundException(String type, String path) {
		this(type + ": " + path + " not found", type, path);
	}

	public MappingNotFoundException(String type, String path, Throwable cause) {
		this(type + ": " + path + " not found", type, path, cause);
	}

	public String getType() {
		return type;
	}
	
	public String getPath() {
		return path;
	}
}

/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.cfg;

import java.util.Map;

import org.dom4j.Element;
import org.hibernate.MappingException;
import org.hibernate.engine.ResultSetMappingDefinition;

/**
 * @author Emmanuel Bernard
 */
public class ResultSetMappingSecondPass extends ResultSetMappingBinder implements QuerySecondPass {
	private Element element;
	private String path;
	private Mappings mappings;

	public ResultSetMappingSecondPass(Element element, String path, Mappings mappings) {
		this.element = element;
		this.path = path;
		this.mappings = mappings;
	}

	public void doSecondPass(Map persistentClasses) throws MappingException {
		ResultSetMappingDefinition definition = buildResultSetMappingDefinition( element, path, mappings);
		mappings.addResultSetMapping( definition );
	}
}

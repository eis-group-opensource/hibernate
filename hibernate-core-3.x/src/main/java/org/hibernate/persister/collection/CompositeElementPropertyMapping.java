/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.persister.collection;

import org.hibernate.MappingException;
import org.hibernate.engine.Mapping;
import org.hibernate.persister.entity.AbstractPropertyMapping;
import org.hibernate.type.AbstractComponentType;
import org.hibernate.type.Type;

/**
 * @author Gavin King
 */
public class CompositeElementPropertyMapping extends AbstractPropertyMapping {

	private final AbstractComponentType compositeType;
	
	public CompositeElementPropertyMapping(
			String[] elementColumns,
			String[] elementColumnReaders,
			String[] elementColumnReaderTemplates, 
			String[] elementFormulaTemplates, 
			AbstractComponentType compositeType, 
			Mapping factory)
	throws MappingException {

		this.compositeType = compositeType;

		initComponentPropertyPaths(null, compositeType, elementColumns, elementColumnReaders,
				elementColumnReaderTemplates, elementFormulaTemplates, factory);

	}

	public Type getType() {
		return compositeType;
	}

	protected String getEntityName() {
		return compositeType.getName();
	}

}

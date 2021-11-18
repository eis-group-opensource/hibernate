/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.mapping;

import java.util.Iterator;

import org.hibernate.MappingException;
import org.hibernate.engine.Mapping;
import org.hibernate.util.JoinedIterator;

/**
 * @author Gavin King
 */
public class SingleTableSubclass extends Subclass {
	
	public SingleTableSubclass(PersistentClass superclass) {
		super(superclass);
	}
	
	protected Iterator getNonDuplicatedPropertyIterator() {
		return new JoinedIterator( 
				getSuperclass().getUnjoinedPropertyIterator(),
				getUnjoinedPropertyIterator()
		);
	}
	
	protected Iterator getDiscriminatorColumnIterator() {
		if ( isDiscriminatorInsertable() && !getDiscriminator().hasFormula() ) {
			return getDiscriminator().getColumnIterator();
		}
		else {
			return super.getDiscriminatorColumnIterator();
		}
	}

	public Object accept(PersistentClassVisitor mv) {
		return mv.accept(this);
	}
    
    public void validate(Mapping mapping) throws MappingException {
        if(getDiscriminator()==null) {
            throw new MappingException("No discriminator found for " + getEntityName() + ". Discriminator is needed when 'single-table-per-hierarchy' is used and a class has subclasses");
        }
        super.validate(mapping);
    }
}

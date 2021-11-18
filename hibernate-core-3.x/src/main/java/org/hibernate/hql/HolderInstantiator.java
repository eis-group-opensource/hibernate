/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.hql;

import java.lang.reflect.Constructor;

import org.hibernate.transform.AliasToBeanConstructorResultTransformer;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.transform.Transformers;

/**
 * @author Gavin King
 */
public final class HolderInstantiator {
		
	public static final HolderInstantiator NOOP_INSTANTIATOR = new HolderInstantiator(null,null);
	
	private final ResultTransformer transformer;
	private final String[] queryReturnAliases;
	
	public static HolderInstantiator getHolderInstantiator(ResultTransformer selectNewTransformer, ResultTransformer customTransformer, String[] queryReturnAliases) {
		return new HolderInstantiator(
				resolveResultTransformer( selectNewTransformer, customTransformer ),
				queryReturnAliases
		);
	}

	public static ResultTransformer resolveResultTransformer(ResultTransformer selectNewTransformer, ResultTransformer customTransformer) {
		return selectNewTransformer != null ? selectNewTransformer : customTransformer;
	}	

	public static ResultTransformer createSelectNewTransformer(Constructor constructor, boolean returnMaps, boolean returnLists) {
		if ( constructor != null ) {
			return new AliasToBeanConstructorResultTransformer(constructor);
		}
		else if ( returnMaps ) {
			return Transformers.ALIAS_TO_ENTITY_MAP;			
		}
		else if ( returnLists ) {
			return Transformers.TO_LIST;
		}		
		else {
			return null;
		}
	}
	
	static public HolderInstantiator createClassicHolderInstantiator(Constructor constructor, 
			ResultTransformer transformer) {
		return new HolderInstantiator( resolveClassicResultTransformer( constructor, transformer ), null );
	}

	static public ResultTransformer resolveClassicResultTransformer(
			Constructor constructor,
			ResultTransformer transformer) {
		return constructor != null ? new AliasToBeanConstructorResultTransformer( constructor ) : transformer;
	}	

	public HolderInstantiator( 
			ResultTransformer transformer,
			String[] queryReturnAliases
	) {
		this.transformer = transformer;		
		this.queryReturnAliases = queryReturnAliases;
	}
	
	public boolean isRequired() {
		return transformer!=null;
	}
	
	public Object instantiate(Object[] row) {
		if(transformer==null) {
			return row;
		} else {
			return transformer.transformTuple(row, queryReturnAliases);
		}
	}	
	
	public String[] getQueryReturnAliases() {
		return queryReturnAliases;
	}

	public ResultTransformer getResultTransformer() {
		return transformer;
	}

}

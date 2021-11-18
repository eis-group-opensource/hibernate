/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.mapping;

/**
 * Common interface for things that can handle meta attributes.
 * 
 * @since 3.0.1
 */
public interface MetaAttributable {

	public java.util.Map getMetaAttributes();

	public void setMetaAttributes(java.util.Map metas);
		
	public MetaAttribute getMetaAttribute(String name);

}

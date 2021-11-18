/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.proxy;

import java.io.Serializable;

/**
 * Convenience base class for SerializableProxy.
 * 
 * @author Gail Badner
 */
public abstract class AbstractSerializableProxy implements Serializable {
	private String entityName;
	private Serializable id;
	private Boolean readOnly;

	/**
	 * For serialization
	 */
	protected AbstractSerializableProxy() {
	}

	protected AbstractSerializableProxy(String entityName, Serializable id, Boolean readOnly) {
		this.entityName = entityName;
		this.id = id;
		this.readOnly = readOnly;
	}

	protected String getEntityName() {
		return entityName;
	}

	protected Serializable getId() {
		return id;
	}

	/**
	 * Set the read-only/modifiable setting from this object in an AbstractLazyInitializer.
	 *
	 * This method should only be called during deserialization, before associating the
	 * AbstractLazyInitializer with a session.
	 *
	 * @param li, the read-only/modifiable setting to use when
	 * associated with a session; null indicates that the default should be used.
	 * @throws IllegalStateException if isReadOnlySettingAvailable() == true
	 */
	protected void setReadOnlyBeforeAttachedToSession(AbstractLazyInitializer li) {
		li.setReadOnlyBeforeAttachedToSession( readOnly );
	}
}

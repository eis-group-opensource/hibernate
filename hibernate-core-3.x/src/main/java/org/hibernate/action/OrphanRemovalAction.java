/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.action;
 
import java.io.Serializable;
 
import org.hibernate.engine.SessionImplementor;
import org.hibernate.persister.entity.EntityPersister;
 
public final class OrphanRemovalAction extends EntityDeleteAction {
 
        public OrphanRemovalAction(Serializable id, Object[] state, Object version, Object instance,
                        EntityPersister persister, boolean isCascadeDeleteEnabled, SessionImplementor session) {
                super( id, state, version, instance, persister, isCascadeDeleteEnabled, session );
        }
}
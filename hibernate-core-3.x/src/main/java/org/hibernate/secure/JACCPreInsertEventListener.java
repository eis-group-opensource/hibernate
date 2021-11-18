/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.secure;

import javax.security.jacc.EJBMethodPermission;

import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.event.Initializable;
import org.hibernate.event.PreInsertEvent;
import org.hibernate.event.PreInsertEventListener;

/**
 * Check security before an insertion
 *
 * @author <a href="mailto:kabir.khan@jboss.org">Kabir Khan</a>
 */
public class JACCPreInsertEventListener implements PreInsertEventListener, Initializable, JACCSecurityListener {
	private String contextID;

	public boolean onPreInsert(PreInsertEvent event) {

		EJBMethodPermission insertPermission = new EJBMethodPermission(
				event.getPersister().getEntityName(),
				HibernatePermission.INSERT,
				null,
				null
		);

		JACCPermissions.checkPermission( event.getEntity().getClass(), contextID, insertPermission );

		return false;
	}


   public void initialize(Configuration cfg){
      contextID = cfg.getProperty(Environment.JACC_CONTEXTID);
   }
}

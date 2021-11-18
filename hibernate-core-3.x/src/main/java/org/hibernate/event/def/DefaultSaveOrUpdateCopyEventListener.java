/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.event.def;

import org.hibernate.engine.CascadingAction;

public class DefaultSaveOrUpdateCopyEventListener extends DefaultMergeEventListener {

	protected CascadingAction getCascadeAction() {
		return CascadingAction.SAVE_UPDATE_COPY;
	}

}

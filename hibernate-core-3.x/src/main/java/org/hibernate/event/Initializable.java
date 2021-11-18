/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.event;

import org.hibernate.cfg.Configuration;

/**
 * An event listener that requires access to mappings to initialize state at 
 * initialization time.
 *
 * @author Gavin King
 */
public interface Initializable {
	public void initialize(Configuration cfg);
}

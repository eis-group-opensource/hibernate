/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.event;

/**
 * Contract for listeners which require notification of SessionFactory closing,
 * presumably to destroy internal state.
 *
 * @author Steve Ebersole
 */
public interface Destructible {
	/**
	 * Notification of {@link org.hibernate.SessionFactory} shutdown.
	 */
	public void cleanup();
}

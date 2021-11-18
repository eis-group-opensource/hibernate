/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.cache.impl;

import java.util.Comparator;

import org.hibernate.cache.CacheDataDescription;
import org.hibernate.mapping.PersistentClass;
import org.hibernate.mapping.Collection;
import org.hibernate.type.VersionType;

/**
 * {@inheritDoc}
 *
 * @author Steve Ebersole
 */
public class CacheDataDescriptionImpl implements CacheDataDescription {
	private final boolean mutable;
	private final boolean versioned;
	private final Comparator versionComparator;

	public CacheDataDescriptionImpl(boolean mutable, boolean versioned, Comparator versionComparator) {
		this.mutable = mutable;
		this.versioned = versioned;
		this.versionComparator = versionComparator;
	}

	public boolean isMutable() {
		return mutable;
	}

	public boolean isVersioned() {
		return versioned;
	}

	public Comparator getVersionComparator() {
		return versionComparator;
	}

	public static CacheDataDescriptionImpl decode(PersistentClass model) {
		return new CacheDataDescriptionImpl(
				model.isMutable(),
				model.isVersioned(),
				model.isVersioned() ? ( ( VersionType ) model.getVersion().getType() ).getComparator() : null
		);
	}

	public static CacheDataDescriptionImpl decode(Collection model) {
		return new CacheDataDescriptionImpl(
				model.isMutable(),
				model.getOwner().isVersioned(),
				model.getOwner().isVersioned() ? ( ( VersionType ) model.getOwner().getVersion().getType() ).getComparator() : null
		);
	}
}

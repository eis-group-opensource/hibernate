/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.engine.profile;

import java.util.Map;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.hibernate.engine.SessionFactoryImplementor;
import org.hibernate.type.Type;
import org.hibernate.type.BagType;
import org.hibernate.type.AssociationType;

/**
 * A 'fetch profile' allows a user to dynamically modify the fetching
 * strategy used for particular associations at runtime, whereas that
 * information was historically only statically defined in the metadata.
 *
 * @author Steve Ebersole
 */
public class FetchProfile {
	private static final Logger log = LoggerFactory.getLogger( FetchProfile.class );

	private final String name;
	private Map fetches = new HashMap();

	private boolean containsJoinFetchedCollection = false;
	private boolean containsJoinFetchedBag = false;
	private Fetch bagJoinFetch;

	/**
	 * A 'fetch profile' is uniquely named within a
	 * {@link SessionFactoryImplementor SessionFactory}, thus it is also
	 * uniquely and easily identifiable within that
	 * {@link SessionFactoryImplementor SessionFactory}.
	 *
	 * @param name The name under which we are bound in the sessionFactory
	 */
	public FetchProfile(String name) {
		this.name = name;
	}

	/**
	 * Add a fetch to the profile.
	 *
	 * @param association The association to be fetched
	 * @param fetchStyleName The name of the fetch style to apply
	 */
	public void addFetch(Association association, String fetchStyleName) {
		addFetch( association, Fetch.Style.parse( fetchStyleName ) );
	}

	/**
	 * Add a fetch to the profile.
	 *
	 * @param association The association to be fetched
	 * @param style The style to apply
	 */
	public void addFetch(Association association, Fetch.Style style) {
		addFetch( new Fetch( association, style ) );
	}

	/**
	 * Add a fetch to the profile.
	 *
	 * @param fetch The fetch to add.
	 */
	public void addFetch(Fetch fetch) {
		Type associationType = fetch.getAssociation().getOwner().getPropertyType( fetch.getAssociation().getAssociationPath() );
		if ( associationType.isCollectionType() ) {
			log.trace( "handling request to add collection fetch [{}]", fetch.getAssociation().getRole() );

			// couple of things for whcih to account in the case of collection
			// join fetches
			if ( Fetch.Style.JOIN == fetch.getStyle() ) {
				// first, if this is a bag we need to ignore it if we previously
				// processed collection join fetches
				if ( BagType.class.isInstance( associationType ) ) {
					if ( containsJoinFetchedCollection ) {
						log.warn( "Ignoring bag join fetch [{}] due to prior collection join fetch", fetch.getAssociation().getRole() );
						return; // EARLY EXIT!!!
					}
				}

				// also, in cases where we are asked to add a collection join
				// fetch where we had already added a bag join fetch previously,
				// we need to go back and ignore that previous bag join fetch.
				if ( containsJoinFetchedBag ) {
					if ( fetches.remove( bagJoinFetch.getAssociation().getRole() ) != bagJoinFetch ) {
						// just for safety...
						log.warn( "Unable to erase previously added bag join fetch" );
					}
					bagJoinFetch = null;
					containsJoinFetchedBag = false;
				}

				containsJoinFetchedCollection = true;
			}
		}
		fetches.put( fetch.getAssociation().getRole(), fetch );
	}

	/**
	 * Getter for property 'name'.
	 *
	 * @return Value for property 'name'.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Getter for property 'fetches'.  Map of {@link Fetch} instances,
	 * keyed by associaion <tt>role</tt>
	 *
	 * @return Value for property 'fetches'.
	 */
	public Map getFetches() {
		return fetches;
	}

	public Fetch getFetchByRole(String role) {
		return ( Fetch ) fetches.get( role );
	}

	/**
	 * Getter for property 'containsJoinFetchedCollection', which flags whether
	 * this fetch profile contained any collection join fetches.
	 *
	 * @return Value for property 'containsJoinFetchedCollection'.
	 */
	public boolean isContainsJoinFetchedCollection() {
		return containsJoinFetchedCollection;
	}

	/**
	 * Getter for property 'containsJoinFetchedBag', which flags whether this
	 * fetch profile contained any bag join fetches
	 *
	 * @return Value for property 'containsJoinFetchedBag'.
	 */
	public boolean isContainsJoinFetchedBag() {
		return containsJoinFetchedBag;
	}
}

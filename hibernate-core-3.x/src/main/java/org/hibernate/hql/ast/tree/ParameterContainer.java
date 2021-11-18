/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.hql.ast.tree;

import org.hibernate.param.ParameterSpecification;

/**
 * Currently this is needed in order to deal with {@link FromElement FromElements} which
 * contain "hidden" JDBC parameters from applying filters.
 * <p/>
 * Would love for this to go away, but that would require that Hibernate's
 * internal {@link org.hibernate.engine.JoinSequence join handling} be able to either:<ul>
 * <li>render the same AST structures</li>
 * <li>render structures capable of being converted to these AST structures</li>
 * </ul>
 * <p/>
 * In the interim, this allows us to at least treat these "hidden" parameters properly which is
 * the most pressing need.
 *
 * @deprecated
 * @author Steve Ebersole
 */
public interface ParameterContainer {
	/**
	 * Set the renderable text of this node.
	 *
	 * @param text The renderable text
	 */
	public void setText(String text);

	/**
	 * Adds a parameter specification for a parameter encountered within this node.  We use the term 'embedded' here
	 * because of the fact that the parameter was simply encountered as part of the node's text; it does not exist
	 * as part of a subtree as it might in a true AST.
	 *
	 * @param specification The generated specification.
	 */
	public void addEmbeddedParameter(ParameterSpecification specification);

	/**
	 * Determine whether this node contains embedded parameters.  The implication is that
	 * {@link #getEmbeddedParameters()} is allowed to return null if this method returns false.
	 *
	 * @return True if this node contains embedded parameters; false otherwise.
	 */
	public boolean hasEmbeddedParameters();

	/**
	 * Retrieve all embedded parameter specifications.
	 *
	 * @return All embedded parameter specifications; may return null.
	 * @see #hasEmbeddedParameters()
	 */
	public ParameterSpecification[] getEmbeddedParameters();
}

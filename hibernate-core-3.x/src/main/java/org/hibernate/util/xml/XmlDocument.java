/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.util.xml;

import java.io.Serializable;

import org.dom4j.Document;

/**
 * Describes a parsed xml document.
 *
 * @author Steve Ebersole
 */
public interface XmlDocument extends Serializable {
	/**
	 * Retrieve the parsed DOM tree.
	 *
	 * @return the DOM tree
	 */
	public Document getDocumentTree();

	/**
	 * Retrieve the document's origin.
	 *
	 * @return The origin
	 */
	public Origin getOrigin();
}

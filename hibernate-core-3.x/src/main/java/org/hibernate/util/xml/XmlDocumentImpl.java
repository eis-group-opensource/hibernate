/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.util.xml;

import java.io.Serializable;

import org.dom4j.Document;

/**
 * Basic implemementation of {@link XmlDocument}
 *
 * @author Steve Ebersole
 */
public class XmlDocumentImpl implements XmlDocument, Serializable {
	private final Document documentTree;
	private final Origin origin;

	public XmlDocumentImpl(Document documentTree, String originType, String originName) {
		this( documentTree, new OriginImpl( originType, originName ) );
	}

	public XmlDocumentImpl(Document documentTree, Origin origin) {
		this.documentTree = documentTree;
		this.origin = origin;
	}

	/**
	 * {@inheritDoc}
	 */
	public Document getDocumentTree() {
		return documentTree;
	}

	/**
	 * {@inheritDoc}
	 */
	public Origin getOrigin() {
		return origin;
	}
}

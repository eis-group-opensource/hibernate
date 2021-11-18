/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.lob;

import java.io.IOException;
import java.io.Reader;

/**
 * Exposes a {@link java.io.Reader} as an {@link java.io.InputStream}.
 *
 * @deprecated Should not be used anymore. 
 */
public class ReaderInputStream extends org.hibernate.engine.jdbc.ReaderInputStream {

	public ReaderInputStream(Reader reader) {
		super(reader);
	}

	public int read() throws IOException {
		return super.read();
	}

}
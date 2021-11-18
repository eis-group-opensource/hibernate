/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.engine.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

/**
 * Exposes a {@link Reader} as an {@link InputStream}.
 * 
 * @author Gavin King
 */
public class ReaderInputStream extends InputStream {
	private Reader reader;
	
	public ReaderInputStream(Reader reader) {
		this.reader = reader;
	}
	
	public int read() throws IOException {
		return reader.read();
	}
	
}

/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.sql;

import java.util.Iterator;
import java.util.Map;

/**
 * An Oracle-style DECODE function.
 * <br>
 * <code>decode(pkvalue, key1, 1, key2, 2, ..., 0)</code>
 *
 * @author Simon Harris
 */
public class DecodeCaseFragment extends CaseFragment {

	public String toFragmentString() {
		
		StringBuffer buf = new StringBuffer( cases.size() * 15 + 10 )
			.append("decode(");

		Iterator iter = cases.entrySet().iterator();
		while ( iter.hasNext() ) {
			Map.Entry me = (Map.Entry) iter.next();

			if ( iter.hasNext() ) {
				buf.append(", ")
					.append( me.getKey() )
					.append(", ")
					.append( me.getValue() );
			}
			else {
				buf.insert( 7, me.getKey() )
					.append(", ")
					.append( me.getValue() );
			}
		}

		buf.append(')');
		
		if (returnColumnName!=null) {
			buf.append(" as ")
				.append(returnColumnName);
		}
		
		return buf.toString();
	}
}
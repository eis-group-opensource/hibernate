/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.mapping;

import java.io.Serializable;

import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.function.SQLFunctionRegistry;
import org.hibernate.sql.Template;

/**
 * A formula is a derived column value
 * @author Gavin King
 */
public class Formula implements Selectable, Serializable {
	private static int formulaUniqueInteger=0;

	private String formula;
	private int uniqueInteger;

	public Formula() {
		uniqueInteger = formulaUniqueInteger++;
	}

	public String getTemplate(Dialect dialect, SQLFunctionRegistry functionRegistry) {
		return Template.renderWhereStringTemplate(formula, dialect, functionRegistry);
	}
	public String getText(Dialect dialect) {
		return getFormula();
	}
	public String getText() {
		return getFormula();
	}
	public String getAlias(Dialect dialect) {
		return "formula" + Integer.toString(uniqueInteger) + '_';
	}
	public String getAlias(Dialect dialect, Table table) {
		return getAlias(dialect);
	}
	public String getFormula() {
		return formula;
	}
	public void setFormula(String string) {
		formula = string;
	}
	public boolean isFormula() {
		return true;
	}

	public String toString() {
		return this.getClass().getName() + "( " + formula + " )";
	}
}

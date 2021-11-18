/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.bytecode.javassist;

/**
 * An exception thrown while generating a bulk accessor.
 * 
 * @author Muga Nishizawa
 * @author modified by Shigeru Chiba
 */
public class BulkAccessorException extends RuntimeException {
    private Throwable myCause;

    /**
     * Gets the cause of this throwable.
     * It is for JDK 1.3 compatibility.
     */
    public Throwable getCause() {
        return (myCause == this ? null : myCause);
    }

    /**
     * Initializes the cause of this throwable.
     * It is for JDK 1.3 compatibility.
     */
    public synchronized Throwable initCause(Throwable cause) {
        myCause = cause;
        return this;
    }

    private int index;

    /**
     * Constructs an exception.
     */
    public BulkAccessorException(String message) {
        super(message);
        index = -1;
        initCause(null);
    }

    /**
     * Constructs an exception.
     *
     * @param index     the index of the property that causes an exception.
     */
    public BulkAccessorException(String message, int index) {
        this(message + ": " + index);
        this.index = index;
    }

    /**
     * Constructs an exception.
     */
    public BulkAccessorException(String message, Throwable cause) {
        super(message);
        index = -1;
        initCause(cause);
    }

    /**
     * Constructs an exception.
     *
     * @param index     the index of the property that causes an exception.
     */
    public BulkAccessorException(Throwable cause, int index) {
        this("Property " + index);
        this.index = index;
        initCause(cause);
    }

    /**
     * Returns the index of the property that causes this exception.
     *
     * @return -1 if the index is not specified.
     */
    public int getIndex() {
        return this.index;
    }
}

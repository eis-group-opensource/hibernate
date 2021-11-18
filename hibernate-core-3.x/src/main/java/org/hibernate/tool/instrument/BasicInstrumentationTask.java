/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.hibernate.tool.instrument;

import org.apache.tools.ant.Task;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.DirectoryScanner;
import org.apache.tools.ant.types.FileSet;

import org.hibernate.bytecode.buildtime.Instrumenter;
import org.hibernate.bytecode.buildtime.Logger;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.HashSet;
import java.io.File;

/**
 * Super class for all Hibernate instrumentation tasks.  Provides the basic templating of how instrumentation
 * should occur; subclasses simply plug in to that process appropriately for the given bytecode provider.
 *
 * @author Steve Ebersole
 */
public abstract class BasicInstrumentationTask extends Task implements Instrumenter.Options {

	private final LoggerBridge logger = new LoggerBridge();

	private List filesets = new ArrayList();
	private boolean extended;

	// deprecated option...
	private boolean verbose;

	public void addFileset(FileSet set) {
		this.filesets.add( set );
	}

	protected final Iterator filesets() {
		return filesets.iterator();
	}

	public boolean isExtended() {
		return extended;
	}

	public void setExtended(boolean extended) {
		this.extended = extended;
	}

	public boolean isVerbose() {
		return verbose;
	}

	public void setVerbose(boolean verbose) {
		this.verbose = verbose;
	}

	public final boolean performExtendedInstrumentation() {
		return isExtended();
	}

	protected abstract Instrumenter buildInstrumenter(Logger logger, Instrumenter.Options options);

	public void execute() throws BuildException {
		try {
			buildInstrumenter( logger, this )
					.execute( collectSpecifiedFiles() );
		}
		catch ( Throwable t ) {
			throw new BuildException( t );
		}
	}

	private Set collectSpecifiedFiles() {
		HashSet files = new HashSet();
		Project project = getProject();
		Iterator filesets = filesets();
		while ( filesets.hasNext() ) {
			FileSet fs = ( FileSet ) filesets.next();
			DirectoryScanner ds = fs.getDirectoryScanner( project );
			String[] includedFiles = ds.getIncludedFiles();
			File d = fs.getDir( project );
			for ( int i = 0; i < includedFiles.length; ++i ) {
				files.add( new File( d, includedFiles[i] ) );
			}
		}
		return files;
	}

	protected class LoggerBridge implements Logger {
		public void trace(String message) {
			log( message, Project.MSG_VERBOSE );
		}

		public void debug(String message) {
			log( message, Project.MSG_DEBUG );
		}

		public void info(String message) {
			log( message, Project.MSG_INFO );
		}

		public void warn(String message) {
			log( message, Project.MSG_WARN );
		}

		public void error(String message) {
			log( message, Project.MSG_ERR );
		}
	}

}

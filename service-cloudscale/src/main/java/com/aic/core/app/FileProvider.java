package com.aic.core.app;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import at.ac.tuwien.infosys.cloudscale.annotations.FileDependency.DependentFile;
import at.ac.tuwien.infosys.cloudscale.annotations.FileDependency.IFileDependencyProvider;

public class FileProvider implements IFileDependencyProvider {

	private Logger log = Logger.getLogger(FileProvider.class);
	
	@Override
	public DependentFile[] getDependentFiles() {
		
		log.debug("entering getDependentFiles");
		
		List<DependentFile> dependentFiles = new ArrayList<DependentFile>();
		
		for(File file : new File("files").listFiles()) {
			if(file.isFile())
				dependentFiles.add(new DependentFile(file.getPath()));
		}
		
		return dependentFiles.toArray(new DependentFile[0]);
		
	}
}

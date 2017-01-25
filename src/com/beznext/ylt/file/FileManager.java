package com.beznext.ylt.file;

import java.io.File;

import org.apache.log4j.Logger;

public class FileManager {
	
	
	static Logger log = Logger.getLogger(FileManager.class);
	
//	public FileManager(){
//		
//	}
//	
//	public FileManager(String path){
//		
//	}
	
	public File GetFile(String pathname){
		File file = new File(pathname);
		return file;
	}
	
	public File[] getFiles(String pathname){
		
		File dir = new File(pathname);
		File[] files = dir.listFiles();
		if (files==null || files.length==0) {
			log.info("No agent data files found - skipping.." );
			return null;
		}
		return files;
	}

}

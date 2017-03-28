package com.beznext.ylt.file;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;

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
		File[] files = dir.listFiles(new FileFilter() {
			
			@Override
			public boolean accept(File pathname) {				
		        return  pathname.isFile();				
			}
		});
		if (files==null || files.length==0) {
			log.info("No agent data files found - skipping.." );
			return null;
		}
		return files;
	}
	
	public void archive(String archiveDir, String files, List<String> filesToArchive) throws IOException {
		
		log.info("Archiving processed Yarn files");
		for(String fq : filesToArchive){
			
			File name = new File(files + File.separatorChar + fq);
			File fDest = new File(archiveDir + File.separatorChar + fq);				
			Path pSrc = name.toPath();
			Path pDest = fDest.toPath();
			log.info("Archiving processed Yarn file : "+fq);
			Files.move(pSrc, pDest, StandardCopyOption.REPLACE_EXISTING);				

		}
	}

}

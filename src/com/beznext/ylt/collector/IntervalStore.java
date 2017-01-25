package com.beznext.ylt.collector;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.HashSet;
import java.util.Set;

public class IntervalStore {
	
	private String intervalStorePath;
	
//	private File intervalStoreFile;
	
	private Set<Long> alreadyProcessedhours;	

	public IntervalStore(){
		
	}
	
	public IntervalStore(String intervalStorePath){
		setIntervalStorePath(intervalStorePath);
	}	
	
	public String getIntervalStorePath() {
		return intervalStorePath;
	}

	public void setIntervalStorePath(String intervalStorePath) {
		this.intervalStorePath = intervalStorePath;
	}	
	
	public File getIntervalStoreFile(){
		File file = new File(this.getIntervalStorePath());
		return file;
	}
	
	public Set<Long> getAlreadyProcessedhours() {
		return alreadyProcessedhours;
	}

	public void setAlreadyProcessedhours(Set<Long> alreadyProcessedhours) {
		this.alreadyProcessedhours = alreadyProcessedhours;
	}
	
	public Set<Long> getProcessedFiles(){
		
		File file = getIntervalStoreFile();
		Set<Long> processedIntervals = new HashSet<Long>();
		FileInputStream input = null;
		
		String lineJustFetched;
		
		try {
			input = new FileInputStream(file);
			BufferedReader br = new BufferedReader(new InputStreamReader(input));
			while ((lineJustFetched = br.readLine()) != null)   {
				 
				 String[] intervals = lineJustFetched.split(",");
				 for(String interval: intervals){
					 processedIntervals.add(Long.valueOf(interval.toString()));
				 }				
			}
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return processedIntervals;
	}
	
	public void wirteIntervalToFile(Set<Long> intervals){
		File file = getIntervalStoreFile();		
		OutputStream output = null;
		
		try {
			output = new FileOutputStream(file,true);
			for(Long interval: intervals){
				// get the content in bytes
				byte[] contentInBytes = interval.toString().getBytes();
				
				output.write(contentInBytes);
				output.write(",".getBytes());
			}	
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (output != null) {
				try {
					output.flush();
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

}

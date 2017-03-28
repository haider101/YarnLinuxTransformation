package com.beznext.ylt.collector;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.beznext.ylt.file.FileManager;
import com.beznext.ylt.key.MixKey;
import com.beznext.ylt.key.TimestampKey;
import com.beznext.ylt.metric.YarnMetric;
import com.opencsv.CSVParser;
import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;

public class YarnCollector implements ICollector{
	
	private String rawDataPath;
	private IntervalStore intervalStore;
	private List<String> filesToArchive;
	
	public YarnCollector(){
		
	}
	
	public YarnCollector(String path){
		this.rawDataPath = path;
	}
	
	public YarnCollector(String path, IntervalStore intervalStore){
		this.intervalStore = intervalStore;
		this.rawDataPath = path;
	}
	
	static Logger log = Logger.getLogger(YarnCollector.class);    
    
    public Map<MixKey, List<?>> collect(){    	
    		
		log.info("Collecting yarn raw data");
		filesToArchive = new ArrayList<>();
		Map<MixKey, List<?>> yarnData = new HashMap<MixKey,List<?>>();
		
		FileManager fReader = new FileManager();
		File[] files = fReader.getFiles(rawDataPath);
		Set<Long> alreadyProcessedhours = intervalStore.getProcessedFiles();
		intervalStore.setAlreadyProcessedhours(alreadyProcessedhours);
//		Set<Long> processedHours = new HashSet<Long>(); 
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy-HH");
		StringBuilder alreadyProcessedFiles = new  StringBuilder();
		boolean processed = false;
		if(files!=null){
			for(File file : files){    			
				try {
					
					if(file.isFile() && file.getName().contains("YARN_PERF")){
						
						log.info("Collecting yarn raw data from file : "+file.getName());
		
	//					CSVReader reader = new CSVReader(new FileReader(file.getAbsolutePath()), ',', '\'', 1);
						CSVReader reader = new CSVReader(new FileReader(file.getAbsolutePath()), CSVParser.DEFAULT_SEPARATOR, CSVParser.DEFAULT_QUOTE_CHARACTER ,  1);
						ColumnPositionMappingStrategy<YarnMetric> strat = new ColumnPositionMappingStrategy<YarnMetric>();
					    strat.setType(YarnMetric.class);
					    String[] columns = new String[] {"finishedTime","memorySeconds","name","vcoreSeconds","startedTime","queue","state","elapsedTime","applicationType","id","user"}; // the fields to bind do in your JavaBean
					    strat.setColumnMapping(columns);
					    CsvToBean<YarnMetric> csv = new CsvToBean<YarnMetric>();
					    List<YarnMetric> yarnMetricList = csv.parse(strat, reader);
		//			    Iterator<YarnMetric> iter = yarnMetricList.iterator(); 
		//			    while(iter.hasNext()){
		//			    	YarnMetric metric = iter.next();
		//			    	System.out.println(metric.toString());
		//			    }
		//			    return yarnMetricList;
					    String fileName = file.getName();
					    String dateTime = fileName.substring(fileName.indexOf(".")+1);
					    dateTime = dateTime.substring(0,13);				    
					    TimestampKey ts = new TimestampKey(dateTime,sdf);
					    if(alreadyProcessedhours!=null && !alreadyProcessedhours.contains(ts.getTimestamp())){
	//					    processedHours.add(ts.getTimestamp());
						    MixKey mkey = new MixKey(ts, null);
						    yarnData.put(mkey, yarnMetricList);
						    filesToArchive.add(file.getName());
					    }
					    else{
					    	processed = true;
					    	alreadyProcessedFiles.append(fileName+", ");
	//				    	log.info("Skipping already process file for Yarn Collector. File: "+fileName);
					    }
					    reader.close();
					}
		
				} catch (FileNotFoundException e) {
					log.error("Yarn collector exception" + e);
					e.printStackTrace();
					return null;
				} catch (Exception f) {
					log.error("Yarn collector exception" + f);
					f.printStackTrace();
					return null;
				}
			}
		}else{
			return null;
		}
		if(processed){
	    	log.info("Skipped already processed files for Yarn Collector. Files: "+alreadyProcessedFiles);			
		}
//		intervalStore.wirteIntervalToFile(processedHours);
//		System.out.println(yarnData.toString());
		return yarnData;
	}   	
    
    public List<String> getFilesToArchive(){
    	return filesToArchive;
    }
    
}


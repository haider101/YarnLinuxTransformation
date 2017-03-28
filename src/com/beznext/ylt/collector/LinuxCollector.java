package com.beznext.ylt.collector;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.beznext.ylt.file.FileManager;
import com.beznext.ylt.key.MixKey;
import com.beznext.ylt.key.NodeKey;
import com.beznext.ylt.key.TimestampKey;
import com.beznext.ylt.metric.LinuxMetric;
import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;

public class LinuxCollector implements ICollector{
	
	private String rawDataPath;
	private IntervalStore intervalStore;
	private List<String> filesToArchive;
	
	public LinuxCollector(){
		
	}
	
	public LinuxCollector(String path){
		this.rawDataPath = path;
	}
	
	public LinuxCollector(String path, IntervalStore intervalStore){
		this.intervalStore = intervalStore;
		this.rawDataPath = path;
	}
	
	static Logger log = Logger.getLogger(LinuxCollector.class);    

	public Map<MixKey,List<?>> collect(){    	
    		
		log.info("Collecting linux raw data");   		
		
		Map<MixKey,List<?>> linuxData = new HashMap<>();
		
		FileManager fReader = new FileManager();
		File[] files = fReader.getFiles(rawDataPath);
		Set<Long> alreadyProcessedhours = intervalStore.getAlreadyProcessedhours();
		Set<Long> processedHours = new HashSet<Long>(); 
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHH");
		StringBuilder alreadyProcessedFiles = new  StringBuilder();
		boolean processed = false;
		if(files!=null){
			for(File file : files){    			
				
				try {
					
					if(file.getName().contains("PROCESS")){					
					
						log.info("Collecting linux raw data from file : "+file.getName());
						
						CSVReader reader = new CSVReader(new FileReader(file.getAbsolutePath()), ',', '\'', 1);
						ColumnPositionMappingStrategy<LinuxMetric> strat = new ColumnPositionMappingStrategy<LinuxMetric>();
					    strat.setType(LinuxMetric.class);
					    String[] columns = new String[] {"START_TIME","END_TIME","WKLD_ELEMENT_1","NODE","WKLD_ELEMENT_3","WKLD_ELEMENT_4","PROCESS_ID","COMMAND","UsrTimeMS","SysTimeMS","ElapsedTimeSec","VMRssKB","LogReads","LogReadsKB","LogWrites","LogWritesKB","PhysReadsKB","PhysWritesKB","AvgReqParallelism"}; // the fields to bind do in your JavaBean
					    strat.setColumnMapping(columns);
					    CsvToBean<LinuxMetric> csv = new CsvToBean<LinuxMetric>();
					    List<LinuxMetric> linuxMetricList = csv.parse(strat, reader);
		//			    Iterator<LinuxMetric> iter = linuxMetricList.iterator(); 
		//			    while(iter.hasNext()){
		//			    	LinuxMetric metric = iter.next();
		//				    	System.out.println(metric.toString());
		//			    }
		//				    return linuxMetricList;
					    String fileName = file.getName();
					    String dateTime = fileName.substring(fileName.lastIndexOf("_")+1);
					    dateTime = dateTime.substring(0,8);
					    
					    TimestampKey ts = new TimestampKey(dateTime,sdf);
					    if(alreadyProcessedhours!=null && !alreadyProcessedhours.contains(ts.getTimestamp())){
					    	processedHours.add(ts.getTimestamp());
					    	String nodeName = fileName.substring(fileName.indexOf("_")+1,fileName.lastIndexOf("_"));
						    NodeKey nodekey = new NodeKey(nodeName);
						    MixKey mk = new MixKey(ts, nodekey);
						    linuxData.put(mk, linuxMetricList);
					    }
					    else{
					    	processed = true;
					    	alreadyProcessedFiles.append(fileName+", ");
	//				    	log.info("Skipping already process file for Linux Collector. File: "+fileName);
					    }
					    reader.close();
					}
				    
				} catch (FileNotFoundException e) {
					log.error("Linux collector exception" + e);
					e.printStackTrace();
					return null;
				} catch (Exception fe) {
					log.error("Linux collector exception" + fe);
					fe.printStackTrace();
					return null;
				}
			}
		}
		else{			
			return null;
		}
		if(processed){
	    	log.info("Skipped already processed files for Linux Collector. Files: "+alreadyProcessedFiles);			
		}
//		intervalStore.wirteIntervalToFile(processedHours);
		return linuxData;
    }
	
	public List<String> getFilesToArchive(){
    	return filesToArchive;
    }
	
}

package com.beznext.ylt.collector;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.beznext.ylt.file.FileManager;
import com.beznext.ylt.key.MixKey;
import com.beznext.ylt.key.TimestampKey;
import com.beznext.ylt.metric.QueuePriorityMetric;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

public class QueuePriorityCollector implements ICollector{
	
	private String rawDataPath;
	private IntervalStore intervalStore;
	
	public QueuePriorityCollector(){
		
	}
	
	public QueuePriorityCollector(String path){
		this.rawDataPath = path;
	}
	
	public QueuePriorityCollector(String path, IntervalStore intervalStore){
		this.intervalStore = intervalStore;
		this.rawDataPath = path;
	}
	
	static Logger log = Logger.getLogger(YarnCollector.class);

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Map<MixKey, List<?>> collect() {
		
		log.info("Collecting yarn queue prority data");		

		Map<MixKey, List<?>> queuePriorityData = new HashMap<MixKey,List<?>>();
		
		FileManager fReader = new FileManager();
		File[] files = fReader.getFiles(rawDataPath);
		Set<Long> alreadyProcessedhours = intervalStore.getProcessedFiles();
//		intervalStore.setAlreadyProcessedhours(alreadyProcessedhours);
//		Set<Long> processedHours = new HashSet<Long>(); 
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy-HH");
		StringBuilder alreadyProcessedFiles = new  StringBuilder();
		boolean processed = false;
		for(File file : files){    			
			try {
				
				if(file.isFile() && file.getName().contains("YARN_CONF")){
					
					log.info("Collecting yarn raw data from file : "+file.getName());
				    List<QueuePriorityMetric> queuePrioritylst = new ArrayList<QueuePriorityMetric>();
				    String fileName = file.getName();
				    String rootElement = fileName.substring(0,fileName.indexOf("_YARN_CONF"));
				    JsonReader json = new JsonReader(new FileReader(file));
				    
				    Map<String,List<Object>> rootMap ;
				    Map<String,List<Object>> schedulerMap = null ;
				    Map<String,List<Object>> schedulerInfoMap = null ;
				    Map<String,List<Object>> queuesMap = null ;
				    Map RootObject = new Gson().fromJson(json, Map.class);
				    
				    rootMap = (Map<String,List<Object>>) RootObject.get(rootElement);				    
				    if(rootMap.get("scheduler")!=null){				    	
				    	
					    schedulerMap = (Map<String,List<Object>>) rootMap.get("scheduler");				    
					    if(schedulerMap.get("schedulerInfo")!=null){
						    	
						    	schedulerInfoMap = (Map<String,List<Object>>) schedulerMap.get("schedulerInfo");
							    if(schedulerInfoMap.get("queues")!=null){
							    	
								    queuesMap = (Map<String,List<Object>>) schedulerInfoMap.get("queues");
								    if(queuesMap.get("queue")!=null){
								    	List queues = queuesMap.get("queue");
								    	Iterator iter = queues.iterator();
									    while(iter.hasNext()){
									    	Map<String, Object> tmp = (Map<String, Object>)iter.next();				    	
									    	QueuePriorityMetric metric = new QueuePriorityMetric();
									    	metric.setQueueName(tmp.get("queueName").toString());				    	
									    	metric.setPriority(Double.parseDouble(tmp.get("absoluteCapacity").toString()));
									    	queuePrioritylst.add(metric);
									    	if(tmp.get("queues")!=null){
									    		iterativeQueueGrabber(tmp,queuePrioritylst);
									    	}
									    }
	
	//								    Iterator<QueuePriorityMetric> itera = queuePrioritylst.iterator(); 
	//								    while(itera.hasNext()){
	//								    	QueuePriorityMetric metric = itera.next();
	//								    	System.out.println(metric.toString());
	//								    }
	//								    System.out.println(queuePrioritylst.size());
					   
									    String dateTime = fileName.substring(fileName.indexOf(".")+1);
									    dateTime = dateTime.substring(0,13);				    
									    TimestampKey ts = new TimestampKey(dateTime,sdf);
									    if(alreadyProcessedhours!=null && !alreadyProcessedhours.contains(ts.getTimestamp())){
	//									    processedHours.add(ts.getTimestamp());
										    MixKey mkey = new MixKey(ts, null);
										    queuePriorityData.put(mkey, queuePrioritylst);
									    }
									    else{
									    	processed = true;
									    	alreadyProcessedFiles.append(fileName+", ");
	//								    	log.info("Skipping already process file for Yarn Collector. File: "+fileName);
									    }
								    }else{
								    	log.info("No \"queue\" element found in file : "+fileName);
								    }
							    }else{
							    	log.info("No \"queues\" element found in file : "+fileName);
							    }
					    	}else{
						    	log.info("No \"schedulerInfo\" element found in file : "+fileName);
						    }
				    	}else{
					    	log.info("No \"scheduler\" element found in file : "+fileName);
					    }
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
		if(processed){
	    	log.info("Skipped already processed files for Queue Collector. Files: "+alreadyProcessedFiles);			
		}
//		intervalStore.wirteIntervalToFile(processedHours);
		return queuePriorityData;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void iterativeQueueGrabber(Map<String, Object> tmp, List<QueuePriorityMetric> queuePriorityLst){		    		
		Map<String,List<Object>> queuesMap = (Map<String,List<Object>>) tmp.get("queues");		
		List queues = queuesMap.get("queue");
		Iterator iter = queues.iterator();
	    while(iter.hasNext()){
	    	Map<String, Object> tmpe = (Map<String, Object>)iter.next();
	    	QueuePriorityMetric metric = new QueuePriorityMetric();
	    	metric.setQueueName(tmpe.get("queueName").toString());				    	
	    	metric.setPriority(Double.parseDouble(tmpe.get("absoluteCapacity").toString()));
	    	queuePriorityLst.add(metric);
	    	if(tmpe.get("queues")!= null){
	    		iterativeQueueGrabber(tmpe,queuePriorityLst);
	    	}
	    }		
	}
	
}

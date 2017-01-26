package com.beznext.ylt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.log4j.Logger;

import com.beznext.ylt.key.MixKey;
import com.beznext.ylt.key.Key;
import com.beznext.ylt.metric.LinuxMetric;
import com.beznext.ylt.metric.OutputMetric;
import com.beznext.ylt.metric.QueuePriorityMetric;
import com.beznext.ylt.metric.YarnMetric;
import com.beznext.ylt.collector.QueuePriorityCollector;
import com.beznext.ylt.dataprocessor.LinuxDataProcessor;
import com.beznext.ylt.dataprocessor.YarnDataProcessor;
import com.beznext.ylt.hourly.HourlyMetric;

public class Transformer {
	
	static Logger log = Logger.getLogger(Transformer.class);
	
	@SuppressWarnings("unchecked")
	public List<HourlyMetric> getHourlyMetrics(Map<MixKey,List<?>> yMap,Map<MixKey,List<?>> qMap,Map<MixKey,List<?>> lMap){
		
		List<HourlyMetric> hourlyMetrics = new ArrayList<HourlyMetric>();
		
		Set<MixKey> yarnkeys = new HashSet<MixKey>(yMap.keySet());
		Set<MixKey> linuxkeys = new HashSet<MixKey>(lMap.keySet());
//		Set<MixKey> queuekeys = new HashSet<MixKey>(qMap.keySet());
		 
		for(MixKey yarnkey: yarnkeys){			
			
//			System.out.println("Yarn data hour : "+yarnkey.getTimestampkey().getTimestamp());
			
			for(MixKey linuxkey: linuxkeys){				
				
//				System.out.println("Linux data hour : "+linuxkey.getTimestampkey().getTimestamp());
				
				if(yarnkey.getTimestampkey().equals(linuxkey.getTimestampkey())){
					
					List<YarnMetric> yarnlst= (List<YarnMetric>) yMap.get(yarnkey);
					List<LinuxMetric> linuxlst = (List<LinuxMetric>) lMap.get(linuxkey);
					List<QueuePriorityMetric> qlst = (List<QueuePriorityMetric>) qMap.get(yarnkey);					
					
					HourlyMetric hm = new HourlyMetric(yarnlst,linuxlst,qlst,linuxkey);
					hourlyMetrics.add(hm);
				}
			}
		}		
		return hourlyMetrics;		
	}
	
	public List<OutputMetric> Transform(HourlyMetric hourlyMetric){
		
		List<LinuxMetric> linuxlst = hourlyMetric.getLinuxlst();
		List<YarnMetric> yarnlst = hourlyMetric.getYarnlst();
		List<QueuePriorityMetric> queuelst = hourlyMetric.getQlst();
		Map<String,Double> queuePriority = new HashMap<String, Double>();
		
		if(queuelst !=null){
			
			Iterator qiter = queuelst.iterator();
			while(qiter.hasNext()){
				QueuePriorityMetric q = (QueuePriorityMetric)qiter.next();
				queuePriority.put(q.getQueueName(), q.getPriority());
			}
		}
			
		List<OutputMetric> outputlst = new ArrayList<OutputMetric>();	
		
		//accumulate Linux data with same application id
//		log.info("Accumulating linux data with same application id");
		
		LinuxDataProcessor linuxDataProcessor = new LinuxDataProcessor();
		YarnDataProcessor yarnDataProcessor = new YarnDataProcessor();

//		Map<Key, List<LinuxMetric>> equivalentMetrics = linuxDataProcessor.MetricsWithKey(linuxlst);
//		Map<String, YarnMetric> yarnKeyMetrics = yarnDataProcessor.MetricsWithKey(yarnlst);
		Map<Key, LinuxMetric> linuxkeyMetrics = linuxDataProcessor.MetricsWithKey(linuxlst);
		Map<Key, YarnMetric> yarnKeyMetrics = yarnDataProcessor.MetricsWithKey(yarnlst);
		Set<Key> ignoreMetrics = new HashSet<>();
		
		try{
			
			log.info("Combining linux and yarn processed metrics.");			

			Iterator<Entry<Key, LinuxMetric>> it = linuxkeyMetrics.entrySet().iterator();
			while (it.hasNext()) {
				    	
		    	Entry<Key, LinuxMetric> entry = it.next();
		    	Key key = entry.getKey();		    	
		    	YarnMetric yarnMetric = yarnKeyMetrics.get(key);			
		    	OutputMetric output = new OutputMetric();
		    	if(yarnMetric!=null){
			    	LinuxMetric linuxmetric = linuxkeyMetrics.get(key);
			    	
//					if(linuxmetriclst != null && linuxmetriclst.size()>0){
						
//						for(LinuxMetric linuxmetric: linuxmetriclst){		
							
							output.setSTART_TIME(yarnMetric.getStartedTime());
							output.setEND_TIME(yarnMetric.getFinishedTime());
							
							output.setWKLD_ELEMENT_1(yarnMetric.getUser());
							output.setWKLD_ELEMENT_2(linuxmetric.getNode());
							output.setWKLD_ELEMENT_3(yarnMetric.getName());
							if(linuxmetric.getWKLD_ELEMENT_4().equals("YARN")){
								output.setWKLD_ELEMENT_4("YARN_"+yarnMetric.getApplicationType());
							}else{
								output.setWKLD_ELEMENT_4(yarnMetric.getApplicationType());
							}
							output.setWKLD_ELEMENT_5(yarnMetric.getQueue());
							output.setCpuYarn(yarnMetric.getVcoreSeconds());
							output.setElapsedTimeSec(yarnMetric.getElapsedTime()/1000);			
								
							output.setUsrTimeMS(linuxmetric.getUsrTimeMS());
							output.setSysTimeMS(linuxmetric.getSysTimeMS());
							output.setVMRssKB(linuxmetric.getVMRssKB());
							
							output.setMemoryYarn(yarnMetric.getMemorySeconds()/yarnMetric.getElapsedTime());
							
							output.setLogReads(linuxmetric.getLogReads());
							output.setLogReadsKB(linuxmetric.getLogReadsKB());
							output.setLogWrites(linuxmetric.getLogWrites());
							output.setLogWritesKB(linuxmetric.getLogWritesKB());
							output.setPhysReadsKB(linuxmetric.getPhysReadsKB());
							output.setPhysWritesKB(linuxmetric.getPhysWritesKB());
							output.setAvgReqParallelism(linuxmetric.getAvgReqParallelism());
							output.setPriority(queuePriority.get(yarnMetric.getQueue()));
//							equivalentMetrics.remove(key);
							ignoreMetrics.add(key);
							
							outputlst.add(output);
						}
					}			
//				}
//			}
		
		//add all Linux metric left unmatched with yarn metrics to output		
		Iterator<Entry<Key, LinuxMetric>> iter = linuxkeyMetrics.entrySet().iterator();
		
	    while (iter.hasNext()) {	    	
	    	
	    	Entry<Key, LinuxMetric> entry = iter.next();	    	
	    	LinuxMetric metric = entry.getValue();
	    	Key key = entry.getKey();
	    	if(!ignoreMetrics.contains(key)){
//		    	for(LinuxMetric metric: metricLst){
			    	OutputMetric output = new OutputMetric();
			    	
			    	output.setSTART_TIME(metric.getSTART_TIME());
					output.setEND_TIME(metric.getEND_TIME());
			    	
			    	output.setWKLD_ELEMENT_1(metric.getWKLD_ELEMENT_1());
			    	output.setWKLD_ELEMENT_2(metric.getNode());
					output.setWKLD_ELEMENT_3(metric.getWKLD_ELEMENT_3());
					output.setWKLD_ELEMENT_4(metric.getWKLD_ELEMENT_4());
					output.setWKLD_ELEMENT_5("");
					output.setCpuYarn(0);
					output.setElapsedTimeSec(0);			
						
					output.setUsrTimeMS(metric.getUsrTimeMS());
					output.setSysTimeMS(metric.getSysTimeMS());
					output.setVMRssKB(metric.getVMRssKB());
					
					output.setMemoryYarn(0);
					
					output.setLogReads(metric.getLogReads());
					output.setLogReadsKB(metric.getLogReadsKB());
					output.setLogWrites(metric.getLogWrites());
					output.setLogWritesKB(metric.getLogWritesKB());
					output.setPhysReadsKB(metric.getPhysReadsKB());
					output.setPhysWritesKB(metric.getPhysWritesKB());
					output.setAvgReqParallelism(metric.getAvgReqParallelism());
					
					outputlst.add(output);
		    	}	        
		    }
//	    }
		}catch(Exception e ){
			log.error("Linux Data Processing Exception" +e);
			System.out.println("Transformer Exception "+e);
		}
				
		return outputlst;
		
	}
	
}

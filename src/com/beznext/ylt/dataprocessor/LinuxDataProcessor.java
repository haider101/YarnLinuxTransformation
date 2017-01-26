package com.beznext.ylt.dataprocessor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.beznext.ylt.metric.LinuxMetric;
import com.beznext.ylt.key.Key;

public class LinuxDataProcessor {
	
	static Logger log = Logger.getLogger(LinuxDataProcessor.class);
	
//	public Map<Key, LinuxMetric> accumulateEquivalentMetrics(List<LinuxMetric> linuxMetriclst){
//		
//		//Put the calculated metrics in targetMetrics
//		Map<Key, LinuxMetric> equivalentMetrics = new HashMap<Key, LinuxMetric>();
//		
//		for (LinuxMetric collectedMetric : linuxMetriclst) {
//			
//			try {
//			
//				Key key = collectedMetric.createKey(collectedMetric.getWKLD_ELEMENT_3());
//				LinuxMetric equivalentMetric = equivalentMetrics.get(key);	
//				if (equivalentMetric == null) {				
//					
//					equivalentMetric = new LinuxMetric();
//					equivalentMetrics.put(key, equivalentMetric);
//					equivalentMetric.setPROCESS_ID(collectedMetric.getPROCESS_ID());
//					equivalentMetric.setWKLD_ELEMENT_1(collectedMetric.getWKLD_ELEMENT_1());
//					equivalentMetric.setWKLD_ELEMENT_3(collectedMetric.getWKLD_ELEMENT_3());
////					equivalentMetric.setWKLD_ELEMENT_2(collectedMetric.getWKLD_ELEMENT_2());
//					equivalentMetric.setNode(collectedMetric.getNode());
//					equivalentMetric.setWKLD_ELEMENT_4(collectedMetric.getWKLD_ELEMENT_4());
//					equivalentMetric.setSTART_TIME(collectedMetric.getSTART_TIME());
//					equivalentMetric.setEND_TIME(collectedMetric.getEND_TIME());
//					
//				}
//				equivalentMetric.setUsrTimeMS(equivalentMetric.getUsrTimeMS() + collectedMetric.getUsrTimeMS());
//				equivalentMetric.setSysTimeMS(equivalentMetric.getSysTimeMS() + collectedMetric.getSysTimeMS());
//				equivalentMetric.setVMRssKB(equivalentMetric.getVMRssKB() + collectedMetric.getVMRssKB());			
//				equivalentMetric.setLogReads(equivalentMetric.getLogReads() + collectedMetric.getLogReads());
//				equivalentMetric.setLogReadsKB(equivalentMetric.getLogReadsKB() + collectedMetric.getLogReadsKB());
//				equivalentMetric.setLogWrites(equivalentMetric.getLogWrites() + collectedMetric.getLogWrites());
//				equivalentMetric.setLogWritesKB(equivalentMetric.getLogWritesKB() + collectedMetric.getLogWritesKB());
//				equivalentMetric.setPhysReadsKB(equivalentMetric.getPhysReadsKB() + collectedMetric.getPhysReadsKB());
//				equivalentMetric.setPhysWritesKB(equivalentMetric.getPhysWritesKB() + collectedMetric.getPhysWritesKB());
//				equivalentMetric.setAvgReqParallelism(equivalentMetric.getAvgReqParallelism() + collectedMetric.getAvgReqParallelism());
//			
//			}catch (Exception ex) {
//				log.error("Linux data accumulation exception " +ex);
//				System.out.println("Dataprocessor Exception "+ ex);				
//			}
//		}		
//		
//		return equivalentMetrics;
//	}
	
//	public Map<Key, List<LinuxMetric>> MetricsWithKey(List<LinuxMetric> linuxMetriclst){	
//		
//		//Put the calculated metrics in targetMetrics
//		Map<Key, List<LinuxMetric>> equivalentMetrics = new HashMap<Key, List<LinuxMetric>>();
//			
//		try{	
//			for (LinuxMetric collectedMetric : linuxMetriclst) {			
//				Key key = collectedMetric.createKey(collectedMetric.getWKLD_ELEMENT_3());
//				if(!equivalentMetrics.containsKey(key)){
//					List<LinuxMetric> equivalentMetricLst = new ArrayList<>();
//					equivalentMetrics.put(key,equivalentMetricLst);
//				}			
//			}
//						
//			for (LinuxMetric collectedMetric : linuxMetriclst) {
//				
//				Key key = collectedMetric.createKey(collectedMetric.getWKLD_ELEMENT_3());
//				List<LinuxMetric> equivalentMetricLst = equivalentMetrics.get(key);
//				
//				LinuxMetric equivalentMetric = new LinuxMetric();					
//				
//				equivalentMetric.setPROCESS_ID(collectedMetric.getPROCESS_ID());
//				equivalentMetric.setWKLD_ELEMENT_1(collectedMetric.getWKLD_ELEMENT_1());
//				equivalentMetric.setWKLD_ELEMENT_3(collectedMetric.getWKLD_ELEMENT_3());
////				equivalentMetric.setWKLD_ELEMENT_2(collectedMetric.getWKLD_ELEMENT_2());
//				equivalentMetric.setNode(collectedMetric.getNode());
//				equivalentMetric.setWKLD_ELEMENT_4(collectedMetric.getWKLD_ELEMENT_4());
//				equivalentMetric.setSTART_TIME(collectedMetric.getSTART_TIME());
//				equivalentMetric.setEND_TIME(collectedMetric.getEND_TIME());
//	
//				equivalentMetric.setUsrTimeMS(collectedMetric.getUsrTimeMS());
//				equivalentMetric.setSysTimeMS(collectedMetric.getSysTimeMS());
//				equivalentMetric.setVMRssKB(collectedMetric.getVMRssKB());			
//				equivalentMetric.setLogReads(collectedMetric.getLogReads());
//				equivalentMetric.setLogReadsKB(collectedMetric.getLogReadsKB());
//				equivalentMetric.setLogWrites(collectedMetric.getLogWrites());
//				equivalentMetric.setLogWritesKB(collectedMetric.getLogWritesKB());
//				equivalentMetric.setPhysReadsKB(collectedMetric.getPhysReadsKB());
//				equivalentMetric.setPhysWritesKB(collectedMetric.getPhysWritesKB());
//				equivalentMetric.setAvgReqParallelism(collectedMetric.getAvgReqParallelism());
//				
//				equivalentMetricLst.add(equivalentMetric);						
//			}				
//		}
//		catch (Exception ex) {
//			log.error("Linux data accumulation exception " +ex);
//			System.out.println("Dataprocessor Exception "+ ex);				
//		}	
//		return equivalentMetrics;
//	}
	
	public Map<Key, LinuxMetric> MetricsWithKey(List<LinuxMetric> linuxMetriclst){
		
		Map<Key, LinuxMetric> linuxKeyMetrics = new HashMap<>();
		
		for(LinuxMetric metric : linuxMetriclst){
			Key key = new Key(metric.getWKLD_ELEMENT_3(), metric.getWKLD_ELEMENT_1());
			linuxKeyMetrics.put(key, metric);
//			System.out.println(metric.getId().toString());			
			
		}	
		return linuxKeyMetrics;	
	}

}

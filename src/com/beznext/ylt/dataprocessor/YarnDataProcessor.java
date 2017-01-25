package com.beznext.ylt.dataprocessor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.beznext.ylt.metric.YarnMetric;

public class YarnDataProcessor {
	
	static Logger log = Logger.getLogger(YarnDataProcessor.class);
	
	public Map<String, YarnMetric> MetricsWithKey(List<YarnMetric> YarnMetriclst){	
		
		Map<String, YarnMetric> yarnKeyMetrics = new HashMap<>();
		
		for(YarnMetric metric : YarnMetriclst){
			
			yarnKeyMetrics.put(metric.getId().toString(), metric);
//			System.out.println(metric.getId().toString());			
			
		}	
		return yarnKeyMetrics;		
	}
}

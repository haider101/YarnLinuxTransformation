package com.beznext.ylt.dataprocessor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.beznext.ylt.metric.YarnMetric;
import com.beznext.ylt.key.Key;

public class YarnDataProcessor {
	
	static Logger log = Logger.getLogger(YarnDataProcessor.class);
	
	public Map<Key, YarnMetric> MetricsWithKey(List<YarnMetric> YarnMetriclst){	
		
		Map<Key, YarnMetric> yarnKeyMetrics = new HashMap<>();
		
		for(YarnMetric metric : YarnMetriclst){
			String wkld3 = metric.getId();
			if(metric.getId().contains("application")){
				wkld3= metric.getId().substring(metric.getId().indexOf("_")+1);
			}
			Key key = new Key(wkld3, metric.getUser());
			yarnKeyMetrics.put(key, metric);
//			System.out.println(metric.getId().toString());			
			
		}	
		return yarnKeyMetrics;		
	}
}

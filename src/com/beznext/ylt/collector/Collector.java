package com.beznext.ylt.collector;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Iterator;
import java.util.List;


import com.beznext.ylt.metric.LinuxMetric;
import com.beznext.ylt.metric.YarnMetric;
import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;


public class Collector {
	
	public Collector(){
		
	}
	
    public List<YarnMetric> collectYarnMetric(){
    
    	try {			

			String path = "D:\\New folder\\Desktop\\Yarn agent specs\\QA_YARN_PERF.2016-04-08 08%3A54%3A38";
			CSVReader reader = new CSVReader(new FileReader(path));
			
			ColumnPositionMappingStrategy<YarnMetric> strat = new ColumnPositionMappingStrategy<YarnMetric>();
		    strat.setType(YarnMetric.class);
		    String[] columns = new String[] {"finishedTime","memorySeconds","name","vcoreSeconds","startedTime","queue","state","elapsedTime","applicationType","id","user"}; // the fields to bind do in your JavaBean
		    strat.setColumnMapping(columns);
		    CsvToBean<YarnMetric> csv = new CsvToBean<YarnMetric>();
		    List<YarnMetric> yarnMetricList = csv.parse(strat, reader);
		    Iterator<YarnMetric> iter = yarnMetricList.iterator(); 
		    while(iter.hasNext()){
		    	YarnMetric metric = iter.next();
		    	System.out.println(metric.toString());
		    }
		    return yarnMetricList;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (Exception f) {
			f.printStackTrace();
			return null;
		}
    }
    
    public List<LinuxMetric> collectLinuxMetric(){
        
    	try {			

			String path = "C:\\Users\\hakhan\\Desktop\\UDTlogs\\UniversalTransOut2\\UniversalTransOut2\\PROCESS_BigDataLabm1_1607270800.csv";
			CSVReader reader = new CSVReader(new FileReader(path));
			
			ColumnPositionMappingStrategy<LinuxMetric> strat = new ColumnPositionMappingStrategy<LinuxMetric>();
		    strat.setType(LinuxMetric.class);
		    String[] columns = new String[] {"START_TIME","END_TIME","NODE","WKLD_ELEMENT_1","WKLD_ELEMENT_3","WKLD_ELEMENT_4","PROCESS_ID","COMMAND","UsrTimeMS","SysTimeMS","ElapsedTimeSec","VMRssKB","LogReads","LogReadsKB","LogWrites","LogWritesKB","PhysReadsKB","PhysWritesKB","AvgReqParallelism"}; // the fields to bind do in your JavaBean
		    strat.setColumnMapping(columns);
		    CsvToBean<LinuxMetric> csv = new CsvToBean<LinuxMetric>();
		    List<LinuxMetric> linuxMetricList = csv.parse(strat, reader);
		    Iterator<LinuxMetric> iter = linuxMetricList.iterator(); 
		    while(iter.hasNext()){
		    	LinuxMetric metric = iter.next();
		    	System.out.println(metric.toString());
		    }
		    return linuxMetricList;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (Exception f) {
			f.printStackTrace();
			return null;
		}
    }

}

package com.beznext.ylt.filewriter;

import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import com.beznext.ylt.collector.IntervalStore;
import com.beznext.ylt.key.MixKey;
import com.beznext.ylt.metric.OutputMetric;
import com.opencsv.CSVWriter;
import com.opencsv.bean.BeanToCsv;
import com.opencsv.bean.ColumnPositionMappingStrategy;

public class OutputWriter 
{
	public static String outputPath;
	public IntervalStore intervalStore;
	
	public OutputWriter(){
		
	}
	
//	public OutputWriter(String path){
//		outputPath = path;
//	}
	
	public OutputWriter(String path, IntervalStore intervalStore){
		outputPath = path;
		this.intervalStore = intervalStore; 
	}
	
	static Logger log = Logger.getLogger(OutputWriter.class);
	
	public void WriteOuputToFile(List<OutputMetric> outpList, MixKey mk)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHH");
		String datetime = sdf.format(mk.getTimestampkey().getTimestamp());
		
		String OutputFileName = "WKLD_"+mk.getNodekey().toString()+"_"+datetime+".csv";
//		String OutputFileName = "WKLD_"+mk.getNodekey().toString()+".csv";
		
		CSVWriter csvWriter = null;
		
		log.info("Creating Output File");
		
		try
		{
			//Create CSVWriter for writing to output.csv 
			csvWriter = new CSVWriter(new FileWriter(outputPath+OutputFileName), CSVWriter.DEFAULT_SEPARATOR, CSVWriter.NO_QUOTE_CHARACTER);
            BeanToCsv<OutputMetric> bc = new BeanToCsv<OutputMetric>();
            
        	 //mapping of columns with their positions
            ColumnPositionMappingStrategy<OutputMetric> mappingStrategy = new ColumnPositionMappingStrategy<OutputMetric>();
            
            //Set mappingStrategy type to output Type
            mappingStrategy.setType(OutputMetric.class);
            
            //Fields in Employee Bean
            String[] columns = new String[]{"START_TIME","END_TIME","WKLD_ELEMENT_1","WKLD_ELEMENT_2","WKLD_ELEMENT_3","WKLD_ELEMENT_4",
						            		"PROCESS_ID","COMMAND","UsrTimeMS","SysTimeMS","elapsedTimeSec","VMRssKB",
						            		"logReads","logReadsKB","logWrites","logWritesKB","physReadsKB",
						            		"physWritesKB","avgReqParallelism","priority","WKLD_ELEMENT_5","cpuYarn","memoryYarn",};
            
            //Setting the colums for mappingStrategy
            mappingStrategy.setColumnMapping(columns);
            
            //Writing empList to csv file
            bc.write(mappingStrategy,csvWriter,outpList);
//            System.out.println("CSV File created successfully.");
            log.info("CSV File created successfully, File name : " +OutputFileName);
            Set<Long> processedHours = new HashSet<Long>();
            Set<Long> alreadyNotedhour = intervalStore.getProcessedFiles();
            if(!alreadyNotedhour.contains(mk.getTimestampkey().getTimestamp())){
            	processedHours.add(mk.getTimestampkey().getTimestamp());
            	intervalStore.wirteIntervalToFile(processedHours);
            }
            
		}
		catch(Exception ee)
		{
			log.error("Output Writer Exception " +ee);
			ee.printStackTrace();
		}
		finally
		{
			try
			{
				//closing the writer
				csvWriter.close();
			}
			catch(Exception ee)
			{
				log.error("Output Writer Exception " +ee);
				ee.printStackTrace();
			}
		}
	}
}


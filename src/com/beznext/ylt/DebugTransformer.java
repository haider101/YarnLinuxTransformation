package com.beznext.ylt;

import java.util.List;
import java.util.Map;


import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.beznext.ylt.collector.ICollector;
import com.beznext.ylt.collector.IntervalStore;
import com.beznext.ylt.collector.LinuxCollector;
import com.beznext.ylt.collector.QueuePriorityCollector;
import com.beznext.ylt.collector.YarnCollector;
import com.beznext.ylt.file.FileManager;
import com.beznext.ylt.filewriter.OutputWriter;
import com.beznext.ylt.hourly.HourlyMetric;
import com.beznext.ylt.key.MixKey;
import com.beznext.ylt.metric.OutputMetric;


public class DebugTransformer {
	
	static Logger log = Logger.getLogger(RunTransformer.class.getName());
	
	public static void main(String[] args){		
		
		String yarnDataPath = null;
		String linuxDataPath = null;
		String outputDirPath = null;
		String intervalStorePath = null;
		String logConfig = null;
		boolean enableArchiving = false;
		String yarnArchiveDirectory = null; 
	    
	    if (args==null || args.length!=5) {
			printUsage();
			System.exit(0);
		}
		
		yarnDataPath = args[0]; 
		linuxDataPath = args[1];
		outputDirPath = args[2];
		intervalStorePath = args[3];
		logConfig = args[4];
		enableArchiving = Boolean.getBoolean(args[5]);
		yarnArchiveDirectory = args[6];
	    
	    
//	    Properties prop = new Properties();
//		InputStream input = null;
//		
//		
//		try {
//
//			input = new FileInputStream("config.properties");
//
//			// load a properties file
//			prop.load(input);
//			
//			yarnDataPath = prop.getProperty("Yarn.rawData.dir");
//			linuxDataPath = prop.getProperty("Linux.rawData.dir");
//			
//		} catch (IOException ex) {
//			ex.printStackTrace();
//		} finally {
//			if (input != null) {
//				try {
//					input.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}	    
	    
		// Set up a simple configuration that logs on the console.
		PropertyConfigurator.configure(logConfig);
//	    BasicConfigurator.configure();
		
		log.info("Starting Transformation");
		
		IntervalStore intervalStore = new IntervalStore(intervalStorePath);
//		Set<String> alreadProcessedFiles= intervalStore.getProcessedFiles();
		
		ICollector yColletor = new YarnCollector(yarnDataPath,intervalStore);
		ICollector lColletor = new LinuxCollector(linuxDataPath,intervalStore);
		ICollector qColletor = new QueuePriorityCollector(yarnDataPath,intervalStore);
		FileManager fm = new FileManager();
		Transformer t = new Transformer();		
		Map<MixKey,List<?>> yarnData = yColletor.collect();
		Map<MixKey,List<?>> queueData = qColletor.collect();
		Map<MixKey,List<?>> linuxData = lColletor.collect();
		if(yarnData!= null && queueData!= null && linuxData!= null){
			List<HourlyMetric> hourlymetrics = t.getHourlyMetrics(yarnData, queueData, linuxData);
		
			for(HourlyMetric hourlymetric:hourlymetrics){
				List<OutputMetric> outputLst =  t.Transform(hourlymetric);		
				OutputWriter writer = new OutputWriter(outputDirPath,intervalStore);
				writer.WriteOuputToFile(outputLst,hourlymetric.getMk());
			}
		}
		else{
			log.info("No Transformation Performed. Data not found");
			System.exit(1);
		}
		if (enableArchiving) {
			// transformation is successful, now archive these files
			try {
				// load up all the files
				log.info("Using input directory: "+yarnDataPath);
				String localFileDir = yarnDataPath;
				if (localFileDir.startsWith("file://")) {
					localFileDir = yarnDataPath.replaceFirst("file://", "");
				}
				fm.archive(yarnArchiveDirectory, localFileDir, yColletor.getFilesToArchive());
				fm.archive(yarnArchiveDirectory, localFileDir, qColletor.getFilesToArchive());
			} catch (Exception e) {
//				log.error("Error detected - continuing: "+continueOnError, e);
//				if (!continueOnError) {
					throw new RuntimeException(e);
//				}
			}
		}
		log.info("Transformation Ended");
	}
	
	public static void printUsage(){
		System.out.println("Usage: java com.beznext.ylt.Debugtransformer <yarnDir> <linuxDir> <outputDir> <intervalStoreFile> <logConfig>");
	}
}

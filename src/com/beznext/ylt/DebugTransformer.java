package com.beznext.ylt;

import java.util.List;
import java.util.Set;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.beznext.ylt.collector.ICollector;
import com.beznext.ylt.collector.IntervalStore;
import com.beznext.ylt.collector.LinuxCollector;
import com.beznext.ylt.collector.QueuePriorityCollector;
import com.beznext.ylt.collector.YarnCollector;
import com.beznext.ylt.filewriter.OutputWriter;
import com.beznext.ylt.hourly.HourlyMetric;
import com.beznext.ylt.metric.OutputMetric;


public class DebugTransformer {
	
	static Logger log = Logger.getLogger(RunTransformer.class.getName());
	
	public static void main(String[] args){		
		
		String yarnDataPath = null;
		String linuxDataPath = null;
		String outputDirPath = null;
		String intervalStorePath = null;
		String logConfig = null;
	    
	    if (args==null || args.length!=5) {
			printUsage();
			System.exit(0);
		}
		
		yarnDataPath = args[0]; 
		linuxDataPath = args[1];
		outputDirPath = args[2];
		intervalStorePath = args[3];
		logConfig = args[4];
	    
	    
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
		
		Transformer t = new Transformer();		
		List<HourlyMetric> hourlymetrics = t.getHourlyMetrics(yColletor.collect(), qColletor.collect(),lColletor.collect());
		
		for(HourlyMetric hourlymetric:hourlymetrics){
			List<OutputMetric> outputLst =  t.Transform(hourlymetric);		
			OutputWriter writer = new OutputWriter(outputDirPath,intervalStore);
			writer.WriteOuputToFile(outputLst,hourlymetric.getMk());
		}
		
		log.info("Transformation Ended");
	}
	
	public static void printUsage(){
		System.out.println("Usage: java com.beznext.ylt.Debugtransformer <yarnDir> <linuxDir> <outputDir> <intervalStoreFile> <logConfig>");
	}
}

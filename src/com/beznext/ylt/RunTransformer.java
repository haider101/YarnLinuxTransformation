package com.beznext.ylt;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
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


public class RunTransformer {	
	
	static Logger log = Logger.getLogger(RunTransformer.class.getName());
	
	public static void main(String[] args){
		
		if(args==null || args.length==0){
			printUsage();
			System.exit(0);
		}
		//java -jar yarn-linux-transformer1.jar "D:\shared\projects\Yarn&LinuxTransformer\YarnLinuxTransformer\ylt.properties"
	    String yltProperties = args[0]; // D:\shared\projects\Yarn&LinuxTransformer\YarnLinuxTransformer\ylt.properties
		
		Properties prop = new Properties();
		InputStream input = null;
		String yarnDataPath = null;
		String linuxDataPath = null;
		String outputDirPath = null;
		String intervalStorePath = null;
		String logConfig = null; //"log4j.properties"
		
		
		
		try {

			input = new FileInputStream(yltProperties);

			// load a properties file
			prop.load(input);
			
			yarnDataPath = prop.getProperty("Yarn.rawData.dir");
			linuxDataPath = prop.getProperty("Linux.rawData.dir");
			outputDirPath = prop.getProperty("Output.dir.path");
			intervalStorePath = prop.getProperty("IntervalStore");
			logConfig = prop.getProperty("logConfig");
			
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		// Set up a simple configuration that logs on the console.
	    PropertyConfigurator.configure(logConfig);
//		BasicConfigurator.configure();
	    
		log.info("Starting Transformation");
		
		IntervalStore intervalStore = new IntervalStore(intervalStorePath);
		
		ICollector yColletor = new YarnCollector(yarnDataPath,intervalStore);
		ICollector lColletor = new LinuxCollector(linuxDataPath,intervalStore);
		ICollector qColletor = new QueuePriorityCollector(yarnDataPath,intervalStore);
		
		Transformer t = new Transformer();		
		List<HourlyMetric> hourlymetrics = t.getHourlyMetrics(yColletor.collect(), qColletor.collect(), lColletor.collect());
		
		if(hourlymetrics.size()>0 && !hourlymetrics.isEmpty()){
			for(HourlyMetric hourlymetric:hourlymetrics){
				List<OutputMetric> outputLst =  t.Transform(hourlymetric);		
				OutputWriter writer = new OutputWriter(outputDirPath,intervalStore);
				writer.WriteOuputToFile(outputLst,hourlymetric.getMk());
			}
		}else{
			log.info("No hours matched for yarn and linux raw data.");
		}
		
		log.info("Transformation Ended");
		System.exit(1);
	}
	
	public static void printUsage(){
		System.out.println("Usage: java com.beznext.ylt.Runtransformer <YLTPropFile>");
	}
}

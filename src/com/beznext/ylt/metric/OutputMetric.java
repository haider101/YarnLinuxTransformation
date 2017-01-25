package com.beznext.ylt.metric;

import com.opencsv.bean.CsvBindByName;

public class OutputMetric {
	

	@CsvBindByName
	private String START_TIME;
	

	@CsvBindByName
	private String END_TIME;
	
	/** 
     * userName(WKLD_ELEMENT_1) 
     */
	@CsvBindByName
	private String WKLD_ELEMENT_1;
	
	/** 
     * hostName(WKLD_ELEMENT_2) 
     */
	@CsvBindByName
	private String WKLD_ELEMENT_2;
	
	/** 
     * application(WKLD_ELEMENT_3) 
     */
	@CsvBindByName
	private String WKLD_ELEMENT_3;
	
	/** 
     * subSystem(WKLD_ELEMENT_4)
     */
	@CsvBindByName
	private String WKLD_ELEMENT_4;
	
	/** 
     * externalWorkload(WKLD_ELEMENT_5) 
     */
	@CsvBindByName
	private String WKLD_ELEMENT_5;
	

	@CsvBindByName
	private double UsrTimeMS;
	

	@CsvBindByName
	private double SysTimeMS;
	

	@CsvBindByName
	private double cpuYarn;
	

	@CsvBindByName
	private double elapsedTimeSec;
	

	@CsvBindByName
	private double VMRssKB;
	

	@CsvBindByName
	private double memoryYarn;
	

	@CsvBindByName
	private double logReads;
	

	@CsvBindByName
	private double logReadsKB;
	

	@CsvBindByName
	private double logWrites;
	

	@CsvBindByName
	private double logWritesKB;
	

	@CsvBindByName
	private double physReadsKB;
	

	@CsvBindByName
	private double physWritesKB;
	

	@CsvBindByName
	private double avgReqParallelism;
	

	@CsvBindByName
	private double priority;
	
	
	@CsvBindByName
	private String PROCESS_ID;
	
	@CsvBindByName
	private String COMMAND;

	public String getSTART_TIME() {
		return START_TIME;
	}

	public void setSTART_TIME(String appStartTime) {
		this.START_TIME = appStartTime;
	}

	public String getEND_TIME() {
		return END_TIME;
	}

	public void setEND_TIME(String appEndTime) {
		this.END_TIME = appEndTime;
	}	

	public String getWKLD_ELEMENT_1() {
		return WKLD_ELEMENT_1;
	}

	public void setWKLD_ELEMENT_1(String wKLD_ELEMENT_1) {
		WKLD_ELEMENT_1 = wKLD_ELEMENT_1;
	}

	public String getWKLD_ELEMENT_2() {
		return WKLD_ELEMENT_2;
	}

	public void setWKLD_ELEMENT_2(String wKLD_ELEMENT_2) {
		WKLD_ELEMENT_2 = wKLD_ELEMENT_2;
	}

	public String getWKLD_ELEMENT_3() {
		return WKLD_ELEMENT_3;
	}

	public void setWKLD_ELEMENT_3(String wKLD_ELEMENT_3) {
		WKLD_ELEMENT_3 = wKLD_ELEMENT_3;
	}

	public String getWKLD_ELEMENT_4() {
		return WKLD_ELEMENT_4;
	}

	public void setWKLD_ELEMENT_4(String wKLD_ELEMENT_4) {
		WKLD_ELEMENT_4 = wKLD_ELEMENT_4;
	}

	public String getWKLD_ELEMENT_5() {
		return WKLD_ELEMENT_5;
	}

	public void setWKLD_ELEMENT_5(String wKLD_ELEMENT_5) {
		WKLD_ELEMENT_5 = wKLD_ELEMENT_5;
	}

	public double getUsrTimeMS() {
		return UsrTimeMS;
	}

	public void setUsrTimeMS(double usrTimeMS) {
		UsrTimeMS = usrTimeMS;
	}

	public double getSysTimeMS() {
		return SysTimeMS;
	}

	public void setSysTimeMS(double sysTimeMS) {
		SysTimeMS = sysTimeMS;
	}

	public double getCpuYarn() {
		return cpuYarn;
	}

	public void setCpuYarn(double cpuYarn) {
		this.cpuYarn = cpuYarn;
	}

	public double getElapsedTimeSec() {
		return elapsedTimeSec;
	}

	public void setElapsedTimeSec(double elapsedTimeSec) {
		this.elapsedTimeSec = elapsedTimeSec;
	}

	public double getVMRssKB() {
		return VMRssKB;
	}

	public void setVMRssKB(double vMRssKB) {
		VMRssKB = vMRssKB;
	}

	public double getMemoryYarn() {
		return memoryYarn;
	}

	public void setMemoryYarn(double memoryYarn) {
		this.memoryYarn = memoryYarn;
	}

	public double getLogReads() {
		return logReads;
	}

	public void setLogReads(double logReads) {
		this.logReads = logReads;
	}

	public double getLogReadsKB() {
		return logReadsKB;
	}

	public void setLogReadsKB(double logReadsKB) {
		this.logReadsKB = logReadsKB;
	}

	public double getLogWrites() {
		return logWrites;
	}

	public void setLogWrites(double logWrites) {
		this.logWrites = logWrites;
	}

	public double getLogWritesKB() {
		return logWritesKB;
	}

	public void setLogWritesKB(double logWritesKB) {
		this.logWritesKB = logWritesKB;
	}

	public double getPhysReadsKB() {
		return physReadsKB;
	}

	public void setPhysReadsKB(double physReadsKB) {
		this.physReadsKB = physReadsKB;
	}

	public double getPhysWritesKB() {
		return physWritesKB;
	}

	public void setPhysWritesKB(double physWritesKB) {
		this.physWritesKB = physWritesKB;
	}

	public double getAvgReqParallelism() {
		return avgReqParallelism;
	}

	public void setAvgReqParallelism(double avgReqParallelism) {
		this.avgReqParallelism = avgReqParallelism;
	}

	public double getPriority() {
		return priority;
	}

	public void setPriority(double priority) {
		this.priority = priority;
	}

	public String getPROCESS_ID() {
		return PROCESS_ID;
	}

	public void setPROCESS_ID(String pROCESS_ID) {
		PROCESS_ID = pROCESS_ID;
	}

	public String getCOMMAND() {
		return COMMAND;
	}

	public void setCOMMAND(String cOMMAND) {
		COMMAND = cOMMAND;
	}

	@Override
	public String toString() {
		return "OutputMetric [START_TIME=" + START_TIME + ", END_TIME="
				+ END_TIME + ", WKLD_ELEMENT_1=" + WKLD_ELEMENT_1
				+ ", WKLD_ELEMENT_2=" + WKLD_ELEMENT_2 + ", WKLD_ELEMENT_3="
				+ WKLD_ELEMENT_3 + ", WKLD_ELEMENT_4=" + WKLD_ELEMENT_4
				+ ", WKLD_ELEMENT_5=" + WKLD_ELEMENT_5 + ", UsrTimeMS="
				+ UsrTimeMS + ", SysTimeMS=" + SysTimeMS + ", cpuYarn="
				+ cpuYarn + ", elapsedTimeSec=" + elapsedTimeSec + ", VMRssKB="
				+ VMRssKB + ", memoryYarn=" + memoryYarn + ", logReads="
				+ logReads + ", logReadsKB=" + logReadsKB + ", logWrites="
				+ logWrites + ", logWritesKB=" + logWritesKB + ", physReadsKB="
				+ physReadsKB + ", physWritesKB=" + physWritesKB
				+ ", avgReqParallelism=" + avgReqParallelism + ", priority="
				+ priority + ", PROCESS_ID=" + PROCESS_ID + ", COMMAND="
				+ COMMAND + "]";
	}

}

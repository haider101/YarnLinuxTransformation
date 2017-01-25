package com.beznext.ylt.metric;

import com.opencsv.bean.CsvBindByName;
 

public class YarnMetric {
	
	public YarnMetric(){
		
	}
	
	/** 
     * Id from yarn 
     */
	@CsvBindByName
	private String id;
	
	/** 
     * User from yarn 
     */
	@CsvBindByName
	private String user;

    /** 
     * Name from yarn 
     */
	@CsvBindByName
    private String name;

    /**
     * SubSystem from yarn
     */
	@CsvBindByName
    private String applicationType;

    /** 
     * External Workload from yarn 
     */
	@CsvBindByName
    private String queue;

    /** 
     * Application start time stamp in seconds 
     */
	@CsvBindByName
    private String startedTime;

    /** 
     * Application end time stamp in seconds 
     */
	@CsvBindByName
    private String finishedTime;
	
	/** 
     * Elapsed time in Ms 
     */
	@CsvBindByName
    private double elapsedTime;

    /** 
     * Memory used on all nodes 
     */
	@CsvBindByName
    private double memorySeconds;

    /** 
     * Total vCore time allocated on all nodes, CPU_YARN 
     */
	@CsvBindByName
    private double vcoreSeconds;
	

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}


	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}


	/**
	 * @param user the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}


	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}


	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}


	/**
	 * @return the applicationType
	 */
	public String getApplicationType() {
		return applicationType;
	}


	/**
	 * @param applicationType the applicationType to set
	 */
	public void setApplicationType(String applicationType) {
		this.applicationType = applicationType;
	}


	/**
	 * @return the queue
	 */
	public String getQueue() {
		return queue;
	}


	/**
	 * @param queue the queue to set
	 */
	public void setQueue(String queue) {
		this.queue = queue;
	}


	/**
	 * @return the startedTime
	 */
	public String getStartedTime() {
		return startedTime;
	}


	/**
	 * @param startedTime the startedTime to set
	 */
	public void setStartedTime(String startedTime) {
		this.startedTime = startedTime;
	}


	/**
	 * @return the finishedTime
	 */
	public String getFinishedTime() {
		return finishedTime;
	}


	/**
	 * @param finishedTime the finishedTime to set
	 */
	public void setFinishedTime(String finishedTime) {
		this.finishedTime = finishedTime;
	}


	/**
	 * @return the elapsedTime
	 */
	public double getElapsedTime() {
		return elapsedTime;
	}


	/**
	 * @param elapsedTime the elapsedTime to set
	 */
	public void setElapsedTime(double elapsedTime) {
		this.elapsedTime = elapsedTime;
	}


	/**
	 * @return the memorySeconds
	 */
	public double getMemorySeconds() {
		return memorySeconds;
	}


	/**
	 * @param memorySeconds the memorySeconds to set
	 */
	public void setMemorySeconds(double memorySeconds) {
		this.memorySeconds = memorySeconds;
	}


	/**
	 * @return the vcoreSeconds
	 */
	public double getVcoreSeconds() {
		return vcoreSeconds;
	}


	/**
	 * @param vcoreSeconds the vcoreSeconds to set
	 */
	public void setVcoreSeconds(double vcoreSeconds) {
		this.vcoreSeconds = vcoreSeconds;
	}


	@Override
	public String toString() {
		return "YarnMetric [id=" + id + ", user=" + user + ", name=" + name
				+ ", applicationType=" + applicationType + ", queue=" + queue
				+ ", startedTime=" + startedTime + ", finishedTime="
				+ finishedTime + ", elapsedTime=" + elapsedTime
				+ ", memorySeconds=" + memorySeconds + ", vcoreSeconds="
				+ vcoreSeconds + "]";
	}    

}

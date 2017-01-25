package com.beznext.ylt.metric;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.opencsv.bean.CsvBindByName;
 

public class LinuxMetric {
	
	/** 
     * Node 
     */
	@CsvBindByName
	private String NODE;
	
	/** 
     * Id from Linux 
     */
	@CsvBindByName
	private String PROCESS_ID;
	
	/** 
     * User Time in milliseconds 
     */
	@CsvBindByName
	private double UsrTimeMS;
	
	/** 
     * System Time in milliseconds
     */
	@CsvBindByName
	private double SysTimeMS;

    /** 
     * VMRss in KB 
     */
	@CsvBindByName
    private double VMRssKB;

    /**
     * Logical Reads
     */
	@CsvBindByName
    private double LogReads;

    /** 
     * Logical Read KBs 
     */
	@CsvBindByName
    private double LogReadsKB;

    /** 
     * Logical Writes
     */
	@CsvBindByName
    private double LogWrites;

    /** 
     * Logical Write KBs 
     */
	@CsvBindByName
    private double LogWritesKB;
	
	/** 
     *  Physical Read KBs 
     */
	@CsvBindByName
    private double PhysReadsKB;

    /** 
     * Physical Write KBs 
     */
	@CsvBindByName
    private double PhysWritesKB;

    /** 
     * Average Request Parallelism 
     */
	@CsvBindByName
    private double AvgReqParallelism;
	
	/** 
     * Start time  
     */
	@CsvBindByName
    private String START_TIME;
	
	/** 
     * End time  
     */
	@CsvBindByName
    private String END_TIME;
	
	/** 
     * User  
     */
	@CsvBindByName
    private String WKLD_ELEMENT_1;	
	
	/** 
     * Application  
     */
	@CsvBindByName
    private String WKLD_ELEMENT_3;
	
	/** 
     * SubSystem  
     */
	@CsvBindByName
    private String WKLD_ELEMENT_4;

	/**
	 * @return the pROCESS_ID
	 */
	public String getPROCESS_ID() {
		return PROCESS_ID;
	}

	/**
	 * @param pROCESS_ID the pROCESS_ID to set
	 */
	public void setPROCESS_ID(String pROCESS_ID) {
		PROCESS_ID = pROCESS_ID;
	}

	/**
	 * @return the usrTimeMS
	 */
	public double getUsrTimeMS() {
		return UsrTimeMS;
	}

	/**
	 * @param usrTimeMS the usrTimeMS to set
	 */
	public void setUsrTimeMS(double usrTimeMS) {
		UsrTimeMS = usrTimeMS;
	}

	/**
	 * @return the sysTimeMS
	 */
	public double getSysTimeMS() {
		return SysTimeMS;
	}

	/**
	 * @param sysTimeMS the sysTimeMS to set
	 */
	public void setSysTimeMS(double sysTimeMS) {
		SysTimeMS = sysTimeMS;
	}

	/**
	 * @return the vMRssKB
	 */
	public double getVMRssKB() {
		return VMRssKB;
	}

	/**
	 * @param vMRssKB the vMRssKB to set
	 */
	public void setVMRssKB(double vMRssKB) {
		VMRssKB = vMRssKB;
	}

	/**
	 * @return the logReads
	 */
	public double getLogReads() {
		return LogReads;
	}

	/**
	 * @param logReads the logReads to set
	 */
	public void setLogReads(double logReads) {
		LogReads = logReads;
	}

	/**
	 * @return the logReadsKB
	 */
	public double getLogReadsKB() {
		return LogReadsKB;
	}

	/**
	 * @param logReadsKB the logReadsKB to set
	 */
	public void setLogReadsKB(double logReadsKB) {
		LogReadsKB = logReadsKB;
	}

	/**
	 * @return the logWrites
	 */
	public double getLogWrites() {
		return LogWrites;
	}

	/**
	 * @param logWrites the logWrites to set
	 */
	public void setLogWrites(double logWrites) {
		LogWrites = logWrites;
	}

	/**
	 * @return the logWritesKB
	 */
	public double getLogWritesKB() {
		return LogWritesKB;
	}

	/**
	 * @param logWritesKB the logWritesKB to set
	 */
	public void setLogWritesKB(double logWritesKB) {
		LogWritesKB = logWritesKB;
	}

	/**
	 * @return the physReadsKB
	 */
	public double getPhysReadsKB() {
		return PhysReadsKB;
	}

	/**
	 * @param physReadsKB the physReadsKB to set
	 */
	public void setPhysReadsKB(double physReadsKB) {
		PhysReadsKB = physReadsKB;
	}

	/**
	 * @return the physWritesKB
	 */
	public double getPhysWritesKB() {
		return PhysWritesKB;
	}

	/**
	 * @param physWritesKB the physWritesKB to set
	 */
	public void setPhysWritesKB(double physWritesKB) {
		PhysWritesKB = physWritesKB;
	}

	/**
	 * @return the avgReqParallelism
	 */
	public double getAvgReqParallelism() {
		return AvgReqParallelism;
	}

	/**
	 * @param avgReqParallelism the avgReqParallelism to set
	 */
	public void setAvgReqParallelism(double avgReqParallelism) {
		AvgReqParallelism = avgReqParallelism;
	}

	/**
	 * @return the sTART_TIME
	 */
	public String getSTART_TIME() {
		return START_TIME;
	}

	/**
	 * @param sTART_TIME the sTART_TIME to set
	 */
	public void setSTART_TIME(String sTART_TIME) {
		START_TIME = sTART_TIME;
	}

	/**
	 * @return the eND_TIME
	 */
	public String getEND_TIME() {
		return END_TIME;
	}

	/**
	 * @param eND_TIME the eND_TIME to set
	 */
	public void setEND_TIME(String eND_TIME) {
		END_TIME = eND_TIME;
	}

	/**
	 * @return the wKLD_ELEMENT_1
	 */
	public String getWKLD_ELEMENT_1() {
		return WKLD_ELEMENT_1;
	}

	/**
	 * @param wKLD_ELEMENT_1 the wKLD_ELEMENT_1 to set
	 */
	public void setWKLD_ELEMENT_1(String wKLD_ELEMENT_1) {
		WKLD_ELEMENT_1 = wKLD_ELEMENT_1;
	}	

	/**
	 * @return the wKLD_ELEMENT_3
	 */
	public String getWKLD_ELEMENT_3() {
		return WKLD_ELEMENT_3;
	}

	/**
	 * @param wKLD_ELEMENT_3 the wKLD_ELEMENT_3 to set
	 */
	public void setWKLD_ELEMENT_3(String wKLD_ELEMENT_3) {
		WKLD_ELEMENT_3 = wKLD_ELEMENT_3;
	}

	/**
	 * @return the wKLD_ELEMENT_4
	 */
	public String getWKLD_ELEMENT_4() {
		return WKLD_ELEMENT_4;
	}

	/**
	 * @param wKLD_ELEMENT_4 the wKLD_ELEMENT_4 to set
	 */
	public void setWKLD_ELEMENT_4(String wKLD_ELEMENT_4) {
		WKLD_ELEMENT_4 = wKLD_ELEMENT_4;
	}
	
	/**
	 * @return the NODE
	 */
	public String getNode() {
		return NODE;
	}

	/**
	 * @param node the node to set
	 */
	public void setNode(String node) {
		this.NODE = node;
	}

	@Override
	public String toString() {
		return "LinuxMetric [PROCESS_ID=" + PROCESS_ID + ", UsrTimeMS="
				+ UsrTimeMS + ", SysTimeMS=" + SysTimeMS + ", VMRssKB="
				+ VMRssKB + ", LogReads=" + LogReads + ", LogReadsKB="
				+ LogReadsKB + ", LogWrites=" + LogWrites + ", LogWritesKB="
				+ LogWritesKB + ", PhysReadsKB=" + PhysReadsKB
				+ ", PhysWritesKB=" + PhysWritesKB + ", AvgReqParallelism="
				+ AvgReqParallelism + ", START_TIME=" + START_TIME
				+ ", END_TIME=" + END_TIME + ", WKLD_ELEMENT_1="
				+ WKLD_ELEMENT_1 + ", WKLD_ELEMENT_3=" + WKLD_ELEMENT_3 + ", WKLD_ELEMENT_4="
				+ WKLD_ELEMENT_4 + "]";
	}

	/**
	 * Unique key getter
	 * @param Id
	 * @return
	 */
	public Key createKey(String Id) {
		return new Key(Id);
	}
	
	/**
	 * {@link LinuxMetric} key
	 * unique key based on the Process metric id
	 */
	public static class Key {	
	    
	    /**
	     * Linux Process Id of the metric
	     */
	    private String m_Id;
	    
	    public String getId() {
			return m_Id;
		}

		public void setId(String m_Id) {
			this.m_Id = m_Id;
		}
		
		protected Key(String Id) {
			m_Id = Id;
		}
		
		/**
	     * Two keys are equal only if their Ids are equal.
	     */
	    public boolean equals(Object o) {
	        if(o instanceof Key) {
	            Key key = (Key)o;
	            return new EqualsBuilder()
					.append(m_Id, key.getId())					
					.isEquals();
	        }
	        return false;
	    }   

		/**
	     * Return a hash code for this key.
	     */
	    public int hashCode() {
	        return new HashCodeBuilder(89574615, 564413)
	        	.append(m_Id)
	        	.toHashCode();
	    }	

	}

}

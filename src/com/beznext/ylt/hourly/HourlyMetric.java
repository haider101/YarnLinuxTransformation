package com.beznext.ylt.hourly;

import java.util.List;

import com.beznext.ylt.collector.QueuePriorityCollector;
import com.beznext.ylt.key.MixKey;
import com.beznext.ylt.metric.LinuxMetric;
import com.beznext.ylt.metric.QueuePriorityMetric;
import com.beznext.ylt.metric.YarnMetric;

public class HourlyMetric {
	
	List<YarnMetric> yarnlst;
	List<LinuxMetric> linuxlst;	
	List<QueuePriorityMetric> qlst;
	MixKey mk;

	public HourlyMetric(){
		
	}
	
	public HourlyMetric(List<YarnMetric> yarnlst, List<LinuxMetric> linuxlst, MixKey mk){
		
		this.yarnlst=yarnlst;
		this.linuxlst=linuxlst;
		this.mk=mk;	
		
	}
	
	public HourlyMetric(List<YarnMetric> yarnlst, List<LinuxMetric> linuxlst, List<QueuePriorityMetric> qlst,MixKey mk){
		
		this.yarnlst=yarnlst;
		this.linuxlst=linuxlst;
		this.qlst=qlst;
		this.mk=mk;	
		
	}
	
	/**
	 * @return the yarnlst
	 */
	public List<YarnMetric> getYarnlst() {
		return yarnlst;
	}


	/**
	 * @param yarnlst the yarnlst to set
	 */
	public void setYarnlst(List<YarnMetric> yarnlst) {
		this.yarnlst = yarnlst;
	}


	/**
	 * @return the linuxlst
	 */
	public List<LinuxMetric> getLinuxlst() {
		return linuxlst;
	}


	/**
	 * @param linuxlst the linuxlst to set
	 */
	public void setLinuxlst(List<LinuxMetric> linuxlst) {
		this.linuxlst = linuxlst;
	}

	public MixKey getMk() {
		return mk;
	}

	public void setMk(MixKey mk) {
		this.mk = mk;
	}

	public List<QueuePriorityMetric> getQlst() {
		return qlst;
	}

	public void setQlst(List<QueuePriorityMetric> qlst) {
		this.qlst = qlst;
	}	

}

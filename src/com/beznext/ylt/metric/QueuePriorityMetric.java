package com.beznext.ylt.metric;

public class QueuePriorityMetric {
	
	private String queueName;
	
	private double priority;

	public String getQueueName() {
		return queueName;
	}

	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}

	public double getPriority() {
		return priority;
	}

	public void setPriority(double priority) {
		this.priority = priority;
	}

	@Override
	public String toString() {
		return "QueuePriorityMetric [queueName=" + queueName + ", priority="
				+ priority + "]";
	}	

}

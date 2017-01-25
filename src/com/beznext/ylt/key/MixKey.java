package com.beznext.ylt.key;

public class MixKey {
	
	private TimestampKey timestampkey;
	private NodeKey nodekey;
	
	public MixKey(TimestampKey timestampkey,NodeKey nodekey){
		
		this.timestampkey=timestampkey;
		this.nodekey=nodekey;		
	}

	public TimestampKey getTimestampkey() {
		return timestampkey;
	}

	public void setTimestampkey(TimestampKey timestampkey) {
		this.timestampkey = timestampkey;
	}

	public NodeKey getNodekey() {
		return nodekey;
	}

	public void setNodekey(NodeKey nodekey) {
		this.nodekey = nodekey;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nodekey == null) ? 0 : nodekey.hashCode());
		result = prime * result
				+ ((timestampkey == null) ? 0 : timestampkey.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MixKey other = (MixKey) obj;
		if (nodekey == null) {
			if (other.nodekey != null)
				return false;
		} else if (!nodekey.equals(other.nodekey))
			return false;
		if (timestampkey == null) {
			if (other.timestampkey != null)
				return false;
		} else if (!timestampkey.equals(other.timestampkey))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MixKey [timestampkey=" + timestampkey + ", nodekey=" + nodekey
				+ "]";
	}	

}

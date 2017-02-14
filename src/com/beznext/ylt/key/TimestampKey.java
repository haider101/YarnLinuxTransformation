package com.beznext.ylt.key;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TimestampKey  {
	

	private long timestamp;
	
	public TimestampKey() {
		
	}

	public TimestampKey(long ts) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(ts);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		this.timestamp=cal.getTimeInMillis();
	}
	
	public TimestampKey(String ts, SimpleDateFormat sdf) {		
				
		Date date = null;
		try {
			date = sdf.parse(ts);
		} catch (ParseException e) {			
			e.printStackTrace();
		}		
		Calendar cal = Calendar.getInstance();  
		cal.setTimeInMillis(date.getTime());
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		this.timestamp=cal.getTimeInMillis();
//		System.out.println("TimeStamp : "+ cal.getTime());
	}
	
	public String roundToNextHour(String ts) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(Long.parseLong(ts));
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);		
		return String.valueOf((cal.getTimeInMillis() + 3600000));
	}

	public long getTimestamp() {
		return timestamp;
	}

	
	public boolean equals(Object obj) {
		
		if (obj instanceof TimestampKey) {
			TimestampKey other = (TimestampKey)obj;
			if ((this.timestamp==other.timestamp)){
				return true;
			}
		}
		return false;
	}
	
	public int hashCode() {
		int hash = 3;
		hash = (int) (7 * hash + this.timestamp);
		return hash;
	}

	@Override
	public String toString() {
		return "TimestampKey [timestamp=" + timestamp + "]";
	}

}

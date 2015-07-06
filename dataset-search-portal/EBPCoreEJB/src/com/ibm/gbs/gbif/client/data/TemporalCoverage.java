package com.ibm.gbs.gbif.client.data;

import java.util.Date;

public class TemporalCoverage 
{
	private String type;
	private Date start;
	private Date end;
	
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Date getStart() {
		return start;
	}
	public void setStart(Date start) {
		this.start = start;
	}
	public Date getEnd() {
		return end;
	}
	public void setEnd(Date end) {
		this.end = end;
	}
	
	

}

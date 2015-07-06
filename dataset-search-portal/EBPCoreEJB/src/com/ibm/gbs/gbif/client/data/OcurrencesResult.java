package com.ibm.gbs.gbif.client.data;

import java.util.List;

public class OcurrencesResult 
{
	
	private long offset;
	private long limit;
	private boolean endOfRecords;
	private long count;

	private List<OcurrenceRow> ocurrences;
	
	
	public long getOffset() {
		return offset;
	}
	public void setOffset(long offset) {
		this.offset = offset;
	}
	public long getLimit() {
		return limit;
	}
	public void setLimit(long limit) {
		this.limit = limit;
	}
	public boolean isEndOfRecords() {
		return endOfRecords;
	}
	public void setEndOfRecords(boolean endOfRecords) {
		this.endOfRecords = endOfRecords;
	}
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	public List<OcurrenceRow> getOcurrences() {
		return ocurrences;
	}
	public void setOcurrences(List<OcurrenceRow> ocurrences) {
		this.ocurrences = ocurrences;
	}

}

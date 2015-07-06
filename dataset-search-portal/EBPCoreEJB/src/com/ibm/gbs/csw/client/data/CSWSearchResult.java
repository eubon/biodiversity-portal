package com.ibm.gbs.csw.client.data;

import java.util.ArrayList;
import java.util.List;

import com.ibm.gbs.gbif.client.data.DataSetRow;

public class CSWSearchResult {

	private long numberOfRecordsMatched;
	private long numberOfRecordsReturned;
	private boolean endOfRecords;
	private long nextRecord;

	private List<CSWRecord> records;

	public CSWSearchResult() {		
		super();
		records = new ArrayList<CSWRecord>();
	}

	public long getNumberOfRecordsMatched() {
		return numberOfRecordsMatched;
	}

	public void setNumberOfRecordsMatched(long numberOfRecordsMatched) {
		this.numberOfRecordsMatched = numberOfRecordsMatched;
	}

	public long getNumberOfRecordsReturned() {
		return numberOfRecordsReturned;
	}

	public void setNumberOfRecordsReturned(long numberOfRecordsReturned) {
		this.numberOfRecordsReturned = numberOfRecordsReturned;
	}

	public boolean isEndOfRecords() {
		return endOfRecords;
	}

	public void setEndOfRecords(boolean endOfRecords) {
		this.endOfRecords = endOfRecords;
	}

	public long getNextRecord() {
		return nextRecord;
	}

	public void setNextRecord(long nextRecord) {
		this.nextRecord = nextRecord;
	}

	public List<CSWRecord> getRecords() {
		return records;
	}

	public void setRecords(List<CSWRecord> records) {
		this.records = records;
	}
	
	
	
	
}

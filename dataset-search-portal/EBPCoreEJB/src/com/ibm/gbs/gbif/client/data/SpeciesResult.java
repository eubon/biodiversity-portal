package com.ibm.gbs.gbif.client.data;

import java.util.List;

public class SpeciesResult 
{
	
	private long offset;
	private long limit;
	private boolean endOfRecords;
	private long count;

	private List<SpecieRow> species;
	
	
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
	public List<SpecieRow> getSpecies() {
		return species;
	}
	public void setSpecies(List<SpecieRow> species) {
		this.species = species;
	}

}

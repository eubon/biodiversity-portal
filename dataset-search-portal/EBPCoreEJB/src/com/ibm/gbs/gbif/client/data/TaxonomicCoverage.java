package com.ibm.gbs.gbif.client.data;

import java.util.List;

public class TaxonomicCoverage 
{
	private String description;
	private List<Coverage> coverages;
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<Coverage> getCoverages() {
		return coverages;
	}
	public void setCoverages(List<Coverage> coverages) {
		this.coverages = coverages;
	}
	
	

}

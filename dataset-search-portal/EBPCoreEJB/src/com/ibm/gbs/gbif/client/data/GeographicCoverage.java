package com.ibm.gbs.gbif.client.data;

public class GeographicCoverage 
{
	private String description;
	private BoundingBox boundingBox;
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public BoundingBox getBoundingBox() {
		return boundingBox;
	}
	public void setBoundingBox(BoundingBox boundingBox) {
		this.boundingBox = boundingBox;
	}
	
	

}

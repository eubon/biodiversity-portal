package com.ibm.gbs.gbif.client.data;

public class BoundingBox 
{
	private double minLatitude;
	private double maxLatitude;
	private double minLongitude;
	private double maxLongitude;
	private boolean globalCoverage;
	
	
	public double getMinLatitude() {
		return minLatitude;
	}
	public void setMinLatitude(double minLatitude) {
		this.minLatitude = minLatitude;
	}
	public double getMaxLatitude() {
		return maxLatitude;
	}
	public void setMaxLatitude(double maxLatitude) {
		this.maxLatitude = maxLatitude;
	}
	public double getMinLongitude() {
		return minLongitude;
	}
	public void setMinLongitude(double minLongitude) {
		this.minLongitude = minLongitude;
	}
	public double getMaxLongitude() {
		return maxLongitude;
	}
	public void setMaxLongitude(double maxLongitude) {
		this.maxLongitude = maxLongitude;
	}
	public boolean isGlobalCoverage() {
		return globalCoverage;
	}
	public void setGlobalCoverage(boolean globalCoverage) {
		this.globalCoverage = globalCoverage;
	}
	
}

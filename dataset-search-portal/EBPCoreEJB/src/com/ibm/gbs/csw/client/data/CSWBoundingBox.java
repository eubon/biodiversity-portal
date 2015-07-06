package com.ibm.gbs.csw.client.data;

public class CSWBoundingBox 
{
	private double minLatitude;
	private double maxLatitude;
	private double minLongitude;
	private double maxLongitude;

	private String crs;
	
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
	public String getCrs() {
		return crs;
	}
	public void setCrs(String crs) {
		this.crs = crs;
	}
	
}

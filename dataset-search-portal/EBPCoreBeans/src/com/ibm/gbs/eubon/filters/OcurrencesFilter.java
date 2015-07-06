package com.ibm.gbs.eubon.filters;

import com.ibm.gbs.eubon.beans.GeoPosition;

public class OcurrencesFilter extends Filter
{
	
	private String year = "";
	private String country = "";
	private String taxonKey = "";
	private String publishingCountry= "";
	private GeoPosition point;
	private GeoPosition pos1;
	private GeoPosition pos2;
	private GeoPosition pos3;
	private GeoPosition pos4;
	
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getTaxonKey() {
		return taxonKey;
	}
	public void setTaxonKey(String taxonKey) {
		this.taxonKey = taxonKey;
	}
	public String getPublishingCountry() {
		return publishingCountry;
	}
	public void setPublishingCountry(String publishingCountry) {
		this.publishingCountry = publishingCountry;
	}
	public GeoPosition getPos1() {
		return pos1;
	}
	public void setPos1(GeoPosition pos1) {
		this.pos1 = pos1;
	}
	public GeoPosition getPos2() {
		return pos2;
	}
	public void setPos2(GeoPosition pos2) {
		this.pos2 = pos2;
	}
	public GeoPosition getPos3() {
		return pos3;
	}
	public void setPos3(GeoPosition pos3) {
		this.pos3 = pos3;
	}
	public GeoPosition getPos4() {
		return pos4;
	}
	public void setPos4(GeoPosition pos4) {
		this.pos4 = pos4;
	}
	public GeoPosition getPoint() {
		return point;
	}
	public void setPoint(GeoPosition point) {
		this.point = point;
	}
}

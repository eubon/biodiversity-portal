package com.ibm.gbs.eubon.filters;

public class DataSetFilter extends Filter 
{
	
	private String q = "";
	private String country = "";
	private String type = "";
	private String keyword = "";
	private String publishingOrg = "";
	private String hostingOrg= "";
	private String decade= "";
	private String publishingCountry= "";
	private String continent= "";
	
	public String getQ() {
		return q;
	}
	public void setQ(String q) {
		this.q = q;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getPublishingOrg() {
		return publishingOrg;
	}
	public void setPublishingOrg(String publishingOrg) {
		this.publishingOrg = publishingOrg;
	}
	public String getHostingOrg() {
		return hostingOrg;
	}
	public void setHostingOrg(String hostingOrg) {
		this.hostingOrg = hostingOrg;
	}
	public String getDecade() {
		return decade;
	}
	public void setDecade(String decade) {
		this.decade = decade;
	}
	public String getPublishingCountry() {
		return publishingCountry;
	}
	public void setPublishingCountry(String publishingCountry) {
		this.publishingCountry = publishingCountry;
	}
	public String getContinent() {
		return continent;
	}
	public void setContinent(String continent) {
		this.continent = continent;
	}
	
	

}

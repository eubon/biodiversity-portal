package com.ibm.gbs.gbif.client.data;

import java.util.Date;
import java.util.List;

public class OcurrenceRow 
{
	private String key;
	private String datasetKey;
	private String publishingCountry;
	private String publishingOrganizationKey;
	private String publishingOrganizationTitle;
	private String protocol;
	private Date lastCrawled;
	private Date lastParsed;
	private String basisOfRecord;
	
	private Taxon taxon; 
	
	private double decimalLongitude;
	private double decimalLatitude;
	
	private long year;
	private long month;
	private long day;
	
	private Date eventDate;
	private List<String> issues;
	private Date lastInterpreted;
	private List<String> identifiers;
	private List<String> facts;
	private List<String> relations;
	private String geodeticDatum;
	private String ocurrenceClass;
	private String countryCode;
	private String country;
	private String gbifID;
	private String institutionCode;
	private String catalogNumber;
	private String collectionCode;

	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getDatasetKey() {
		return datasetKey;
	}
	public void setDatasetKey(String datasetKey) {
		this.datasetKey = datasetKey;
	}
	public String getPublishingCountry() {
		return publishingCountry;
	}
	public void setPublishingCountry(String publishingCountry) {
		this.publishingCountry = publishingCountry;
	}
	public String getPublishingOrganizationKey() {
		return publishingOrganizationKey;
	}
	public void setPublishingOrganizationKey(String publishingOrganizationKey) {
		this.publishingOrganizationKey = publishingOrganizationKey;
	}
	public String getProtocol() {
		return protocol;
	}
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
	public Date getLastCrawled() {
		return lastCrawled;
	}
	public void setLastCrawled(Date lastCrawled) {
		this.lastCrawled = lastCrawled;
	}
	public Date getLastParsed() {
		return lastParsed;
	}
	public void setLastParsed(Date lastParsed) {
		this.lastParsed = lastParsed;
	}
	public String getBasisOfRecord() {
		return basisOfRecord;
	}
	public void setBasisOfRecord(String basisOfRecord) {
		this.basisOfRecord = basisOfRecord;
	}
	public Taxon getTaxon() {
		return taxon;
	}
	public void setTaxon(Taxon taxon) {
		this.taxon = taxon;
	}
	public double getDecimalLongitude() {
		return decimalLongitude;
	}
	public void setDecimalLongitude(double decimalLongitude) {
		this.decimalLongitude = decimalLongitude;
	}
	public double getDecimalLatitude() {
		return decimalLatitude;
	}
	public void setDecimalLatitude(double decimalLatitude) {
		this.decimalLatitude = decimalLatitude;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public Date getEventDate() {
		return eventDate;
	}
	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}
	public List<String> getIssues() {
		return issues;
	}
	public void setIssues(List<String> issues) {
		this.issues = issues;
	}
	public Date getLastInterpreted() {
		return lastInterpreted;
	}
	public void setLastInterpreted(Date lastInterpreted) {
		this.lastInterpreted = lastInterpreted;
	}
	public List<String> getIdentifiers() {
		return identifiers;
	}
	public void setIdentifiers(List<String> identifiers) {
		this.identifiers = identifiers;
	}
	public List<String> getFacts() {
		return facts;
	}
	public void setFacts(List<String> facts) {
		this.facts = facts;
	}
	public List<String> getRelations() {
		return relations;
	}
	public void setRelations(List<String> relations) {
		this.relations = relations;
	}
	public String getGeodeticDatum() {
		return geodeticDatum;
	}
	public void setGeodeticDatum(String geodeticDatum) {
		this.geodeticDatum = geodeticDatum;
	}
	public String getOcurrenceClass() {
		return ocurrenceClass;
	}
	public void setOcurrenceClass(String ocurrenceClass) {
		this.ocurrenceClass = ocurrenceClass;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getGbifID() {
		return gbifID;
	}
	public void setGbifID(String gbifID) {
		this.gbifID = gbifID;
	}
	public String getInstitutionCode() {
		return institutionCode;
	}
	public void setInstitutionCode(String institutionCode) {
		this.institutionCode = institutionCode;
	}
	public String getCatalogNumber() {
		return catalogNumber;
	}
	public void setCatalogNumber(String catalogNumber) {
		this.catalogNumber = catalogNumber;
	}
	public String getCollectionCode() {
		return collectionCode;
	}
	public void setCollectionCode(String collectionCode) {
		this.collectionCode = collectionCode;
	}
	public long getYear() {
		return year;
	}
	public void setYear(long year) {
		this.year = year;
	}
	public long getMonth() {
		return month;
	}
	public void setMonth(long month) {
		this.month = month;
	}
	public long getDay() {
		return day;
	}
	public void setDay(long day) {
		this.day = day;
	}
	public String getPublishingOrganizationTitle() {
		return publishingOrganizationTitle;
	}
	public void setPublishingOrganizationTitle(String publishingOrganizationTitle) {
		this.publishingOrganizationTitle = publishingOrganizationTitle;
	}

}

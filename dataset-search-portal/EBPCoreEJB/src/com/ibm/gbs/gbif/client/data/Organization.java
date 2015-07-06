package com.ibm.gbs.gbif.client.data;

import java.util.Date;
import java.util.List;

public class Organization
{

	private String key;
	private String endorsingNodeKey;
	private boolean endorsementApproved;
	private String title;
	private String description;
	private String language;
	private List<String> homepages;
	private List<String> address;
	private String city;
	private String country;
	private String postalCode;
	private double latitude;
	private double longitude;
	private long numPublishedDatasets;
	private String createdBy;
	private String modifiedBy;
	private Date created;
	private Date modified;
	private List<Contact> contacts;
	private List<EndPoint> endpoints;
	private List<MachineTags> machineTags;
	
	private List<Tags> tags;
	private List<Identifier> identifiers;
	
	private List<String> comments;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getEndorsingNodeKey() {
		return endorsingNodeKey;
	}

	public void setEndorsingNodeKey(String endorsingNodeKey) {
		this.endorsingNodeKey = endorsingNodeKey;
	}

	public boolean isEndorsementApproved() {
		return endorsementApproved;
	}

	public void setEndorsementApproved(boolean endorsementApproved) {
		this.endorsementApproved = endorsementApproved;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public long getNumPublishedDatasets() {
		return numPublishedDatasets;
	}

	public void setNumPublishedDatasets(long numPublishedDatasets) {
		this.numPublishedDatasets = numPublishedDatasets;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

	public List<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

	public List<EndPoint> getEndpoints() {
		return endpoints;
	}

	public void setEndpoints(List<EndPoint> endpoints) {
		this.endpoints = endpoints;
	}

	public List<MachineTags> getMachineTags() {
		return machineTags;
	}

	public void setMachineTags(List<MachineTags> machineTags) {
		this.machineTags = machineTags;
	}

	public List<Identifier> getIdentifiers() {
		return identifiers;
	}

	public void setIdentifiers(List<Identifier> identifiers) {
		this.identifiers = identifiers;
	}

	public List<String> getComments() {
		return comments;
	}

	public void setComments(List<String> comments) {
		this.comments = comments;
	}

	public List<String> getHomepages() {
		return homepages;
	}

	public void setHomepages(List<String> homepages) {
		this.homepages = homepages;
	}

	public List<String> getAddress() {
		return address;
	}

	public void setAddress(List<String> address) {
		this.address = address;
	}

	public List<Tags> getTags() {
		return tags;
	}

	public void setTags(List<Tags> tags) {
		this.tags = tags;
	}

}

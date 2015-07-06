package com.ibm.gbs.gbif.client.data;

import java.util.Date;
import java.util.List;

public class DataSet extends DataSetRow
{

	private String installationKey;
	private boolean external;
	private long numConstituents;
	private String language;
	private Citation citation;
	private String rights;
	private boolean lockedForAutoUpdate;
	private String createdBy;
	private String modifiedBy;
	private Date created;
	private Date modified;
	private List<Contact> contacts;
	private List<EndPoint> endpoints;
	private List<MachineTags> machineTags;
	
	private List<String> tags;
	private List<Identifier> identifiers;
	
	private List<String> comments;
	private List<String> bibliographicCitations;
	private List<String> curatorialUnits;
	private List<TaxonomicCoverage> taxonomicCoverages;
	private List<GeographicCoverage> geographicCoverages;
	private List<TemporalCoverage> temporalCoverages;
	private List<KeywordCollections> keywordCollections;
	
	private List<String> countryCoverage;
	private String dataLanguage;
	private String additionalInfo;
	private Date pubDate;
	
	public String getInstallationKey() {
		return installationKey;
	}
	public void setInstallationKey(String installationKey) {
		this.installationKey = installationKey;
	}
	public boolean isExternal() {
		return external;
	}
	public void setExternal(boolean external) {
		this.external = external;
	}
	public long getNumConstituents() {
		return numConstituents;
	}
	public void setNumConstituents(long numConstituents) {
		this.numConstituents = numConstituents;
	}
	public Citation getCitation() {
		return citation;
	}
	public void setCitation(Citation citation) {
		this.citation = citation;
	}
	public String getRights() {
		return rights;
	}
	public void setRights(String rights) {
		this.rights = rights;
	}
	public boolean isLockedForAutoUpdate() {
		return lockedForAutoUpdate;
	}
	public void setLockedForAutoUpdate(boolean lockedForAutoUpdate) {
		this.lockedForAutoUpdate = lockedForAutoUpdate;
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
	public List<String> getTags() {
		return tags;
	}
	public void setTags(List<String> tags) {
		this.tags = tags;
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
	public List<String> getBibliographicCitations() {
		return bibliographicCitations;
	}
	public void setBibliographicCitations(List<String> bibliographicCitations) {
		this.bibliographicCitations = bibliographicCitations;
	}
	public List<String> getCuratorialUnits() {
		return curatorialUnits;
	}
	public void setCuratorialUnits(List<String> curatorialUnits) {
		this.curatorialUnits = curatorialUnits;
	}
	public List<TaxonomicCoverage> getTaxonomicCoverages() {
		return taxonomicCoverages;
	}
	public void setTaxonomicCoverages(List<TaxonomicCoverage> taxonomicCoverages) {
		this.taxonomicCoverages = taxonomicCoverages;
	}
	public List<GeographicCoverage> getGeographicCoverages() {
		return geographicCoverages;
	}
	public void setGeographicCoverages(List<GeographicCoverage> geographicCoverages) {
		this.geographicCoverages = geographicCoverages;
	}
	public List<TemporalCoverage> getTemporalCoverages() {
		return temporalCoverages;
	}
	public void setTemporalCoverages(List<TemporalCoverage> temporalCoverages) {
		this.temporalCoverages = temporalCoverages;
	}
	public List<KeywordCollections> getKeywordCollections() {
		return keywordCollections;
	}
	public void setKeywordCollections(List<KeywordCollections> keywordCollections) {
		this.keywordCollections = keywordCollections;
	}
	public List<String> getCountryCoverage() {
		return countryCoverage;
	}
	public void setCountryCoverage(List<String> countryCoverage) {
		this.countryCoverage = countryCoverage;
	}
	public String getDataLanguage() {
		return dataLanguage;
	}
	public void setDataLanguage(String dataLanguage) {
		this.dataLanguage = dataLanguage;
	}
	public String getAdditionalInfo() {
		return additionalInfo;
	}
	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}
	public Date getPubDate() {
		return pubDate;
	}
	public void setPubDate(Date pubDate) {
		this.pubDate = pubDate;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	
}

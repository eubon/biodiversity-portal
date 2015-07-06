package com.ibm.gbs.gbif.client.data;

import java.util.Date;
import java.util.List;

public class SpecieRow 
{
	private String key;
	private String nubKey;
	private String datasetKey;
	private String parentKey;
	private String parent;
	
	private String canonicalName;
	private String vernacularName;
	private String authorship;
	private String nameType;
	private String taxonomicStatus;
	
	private String publishedIn;
	private String accordingTo;
	private long numDescendants;
	
	private Date modified;
	private Date lastInterpreted;
	private List<String> issues;
	private boolean synonym;
	private String specieClass;
	
	
	private Taxon taxon;


	public String getKey() {
		return key;
	}


	public void setKey(String key) {
		this.key = key;
	}


	public String getNubKey() {
		return nubKey;
	}


	public void setNubKey(String nubKey) {
		this.nubKey = nubKey;
	}


	public String getDatasetKey() {
		return datasetKey;
	}


	public void setDatasetKey(String datasetKey) {
		this.datasetKey = datasetKey;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}


	public String getCanonicalName() {
		return canonicalName;
	}


	public void setCanonicalName(String canonicalName) {
		this.canonicalName = canonicalName;
	}


	public String getVernacularName() {
		return vernacularName;
	}


	public void setVernacularName(String vernacularName) {
		this.vernacularName = vernacularName;
	}


	public String getAuthorship() {
		return authorship;
	}


	public void setAuthorship(String authorship) {
		this.authorship = authorship;
	}


	public String getNameType() {
		return nameType;
	}


	public void setNameType(String nameType) {
		this.nameType = nameType;
	}


	public String getTaxonomicStatus() {
		return taxonomicStatus;
	}


	public void setTaxonomicStatus(String taxonomicStatus) {
		this.taxonomicStatus = taxonomicStatus;
	}


	public String getPublishedIn() {
		return publishedIn;
	}


	public void setPublishedIn(String publishedIn) {
		this.publishedIn = publishedIn;
	}


	public String getAccordingTo() {
		return accordingTo;
	}


	public void setAccordingTo(String accordingTo) {
		this.accordingTo = accordingTo;
	}


	public long getNumDescendants() {
		return numDescendants;
	}


	public void setNumDescendants(long numDescendants) {
		this.numDescendants = numDescendants;
	}


	public Date getModified() {
		return modified;
	}


	public void setModified(Date modified) {
		this.modified = modified;
	}


	public Date getLastInterpreted() {
		return lastInterpreted;
	}


	public void setLastInterpreted(Date lastInterpreted) {
		this.lastInterpreted = lastInterpreted;
	}


	public List<String> getIssues() {
		return issues;
	}


	public void setIssues(List<String> issues) {
		this.issues = issues;
	}

	public String getSpecieClass() {
		return specieClass;
	}


	public void setSpecieClass(String specieClass) {
		this.specieClass = specieClass;
	}


	public Taxon getTaxon() {
		return taxon;
	}


	public void setTaxon(Taxon taxon) {
		this.taxon = taxon;
	}


	public String getParentKey() {
		return parentKey;
	}


	public void setParentKey(String parentKey) {
		this.parentKey = parentKey;
	}


	public boolean isSynonym() {
		return synonym;
	}


	public void setSynonym(boolean synonym) {
		this.synonym = synonym;
	}

	
}

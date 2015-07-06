package com.ibm.gbs.csw.client.data;

import java.util.Date;
import java.util.List;

public class CSWRecord 
{
	
	private String identifier;
	private Date date;
	private String title;
	private String type;
	private List<String> subjects;
	
	private Date modified;
	private String abstractTxt;
	private String description;
	private String rights;
	private String language;
	private String source;
	
	/// Bounding Box
	
	private List<CSWUri> uris;

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<String> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<String> subjects) {
		this.subjects = subjects;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

	public String getAbstractTxt() {
		return abstractTxt;
	}

	public void setAbstractTxt(String abstractTxt) {
		this.abstractTxt = abstractTxt;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRights() {
		return rights;
	}

	public void setRights(String rights) {
		this.rights = rights;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public List<CSWUri> getUris() {
		return uris;
	}

	public void setUris(List<CSWUri> uris) {
		this.uris = uris;
	}
	
	
	
	
	

}

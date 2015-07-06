package com.ibm.gbs.gbif.client.data;

import java.util.Date;

public class Identifier 
{
	private long key;
	private String type;
	private String identifier;
	private String createdBy;
	private Date created;
	
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public long getKey() {
		return key;
	}
	public void setKey(long key) {
		this.key = key;
	}
	
	

}

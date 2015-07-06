package com.ibm.gbs.gbif.client.data;

import java.util.Date;

public class MachineTags 
{
	private long key;
	private String namespace;
	private String name;
	private String value;
	private String createdBy;
	private Date created;
	
	
	
	public String getNamespace() {
		return namespace;
	}
	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public long getKey() {
		return key;
	}
	public void setKey(long key) {
		this.key = key;
	}
	

}

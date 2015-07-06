package com.ibm.gbs.gbif.client.data;

import java.util.Date;
import java.util.List;

public class EndPoint 
{
	private long key;
	private String type;
	private String url;
	private String createdBy;
	private String modifiedBy;
	private Date created;
	private Date modified;
	private List<MachineTags> machineTags;
	
	
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
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
	public List<MachineTags> getMachineTags() {
		return machineTags;
	}
	public void setMachineTags(List<MachineTags> machineTags) {
		this.machineTags = machineTags;
	}
	public long getKey() {
		return key;
	}
	public void setKey(long key) {
		this.key = key;
	}
	

}

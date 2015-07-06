package com.ibm.gbs.gbif.client.data;

import java.util.List;

public class KeywordCollections 
{
	private String thesaurus;
	private List<String> keywords;
	
	public String getThesaurus() {
		return thesaurus;
	}
	public void setThesaurus(String thesaurus) {
		this.thesaurus = thesaurus;
	}
	public List<String> getKeywords() {
		return keywords;
	}
	public void setKeywords(List<String> keywords) {
		this.keywords = keywords;
	}	

}

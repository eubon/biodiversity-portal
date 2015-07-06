package com.ibm.gbs.gbif.client.data;

public class Coverage 
{
	private String scientificName;
	private String commonName;
	private Rank rank;
	
	
	public String getScientificName() {
		return scientificName;
	}
	public void setScientificName(String scientificName) {
		this.scientificName = scientificName;
	}
	public String getCommonName() {
		return commonName;
	}
	public void setCommonName(String commonName) {
		this.commonName = commonName;
	}
	public Rank getRank() {
		return rank;
	}
	public void setRank(Rank rank) {
		this.rank = rank;
	}
	

}

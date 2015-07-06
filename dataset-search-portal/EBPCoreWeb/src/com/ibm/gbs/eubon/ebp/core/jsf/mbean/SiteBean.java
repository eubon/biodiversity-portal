package com.ibm.gbs.eubon.ebp.core.jsf.mbean;

import java.io.Serializable;

/**
 * MBean for a Site
 * 
 * @author sp81238 - IBM -
 *
 */
//TODO: PARA ELIMINAR CUANDO SE TENGA LA BBDD. Sustituir por la entidad SITE
public class SiteBean implements Serializable{

	private String siteName;
	private String siteShortName;
	private String siteURL;
	private double latitude;
	private double longitude;
	private String informationToShow;
	private String pathToIcon;
	
	public SiteBean(String siteName, String siteShortName, String siteURL,
			double latitude, double longitude, String pathToIcon) {
		super();
		this.siteName = siteName;
		this.siteShortName = siteShortName;
		this.siteURL = siteURL;
		this.latitude = latitude;
		this.longitude = longitude;
		this.informationToShow = "";
		this.pathToIcon = pathToIcon;
	}
	
	public String getSiteName() {
		return siteName;
	}
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	public String getSiteShortName() {
		return siteShortName;
	}
	public void setSiteShortName(String siteShortName) {
		this.siteShortName = siteShortName;
	}
	public String getSiteURL() {
		return siteURL;
	}
	public void setSiteURL(String siteURL) {
		this.siteURL = siteURL;
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
	public String getInformationToShow() {
		return informationToShow;
	}
	public void setInformationToShow(String siteName, String siteShortName, String siteURL, String pathToIcon) {
		this.informationToShow = "";
	}
	public String getPathToIcon() {
		return pathToIcon;
	}
	public void setPathToIcon(String pathToIcon) {
		this.pathToIcon = pathToIcon;
	}
	
}

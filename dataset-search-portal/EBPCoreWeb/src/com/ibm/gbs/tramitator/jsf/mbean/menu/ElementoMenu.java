package com.ibm.gbs.tramitator.jsf.mbean.menu;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

public class ElementoMenu implements Serializable{

	private List<ElementoMenu> elementos;

	private String id;

	private String label;
	private String action;
	private List<String> roles;

	@XmlElement(name = "id")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@XmlElement(name = "label")
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	@XmlElement(name = "action")
	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	@XmlElementWrapper(name = "roles")
	@XmlElement(name = "rol")
	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	@XmlElementWrapper(name = "elementos")
	@XmlElement(name = "elemento")
	public List<ElementoMenu> getElementos() {
		return elementos;
	}

	public void setElementos(List<ElementoMenu> elementos) {
		this.elementos = elementos;
	}

}

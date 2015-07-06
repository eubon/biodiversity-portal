package com.ibm.gbs.eubon.ebp.core.jpa.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the EBV_CLASS database table.
 * 
 */
@Entity
@Table(name="EBV_CLASS")
@NamedQuery(name="EbvClass.findAll", query="SELECT e FROM EbvClass e")
public class EbvClass implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="EBV_CLASS_ID_GENERATOR", sequenceName="EBV_CLASS_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EBV_CLASS_ID_GENERATOR")
	private long id;

	private String description;

	private String name;

	//bi-directional many-to-one association to EbvProvider
	@OneToMany(mappedBy="ebvClass")
	private List<EbvProvider> ebvProviders;

	//bi-directional many-to-one association to EbvVar
	@OneToMany(mappedBy="ebvClass")
	private List<EbvVar> ebvVars;

	public EbvClass() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<EbvProvider> getEbvProviders() {
		return this.ebvProviders;
	}

	public void setEbvProviders(List<EbvProvider> ebvProviders) {
		this.ebvProviders = ebvProviders;
	}

	public EbvProvider addEbvProvider(EbvProvider ebvProvider) {
		getEbvProviders().add(ebvProvider);
		ebvProvider.setEbvClass(this);

		return ebvProvider;
	}

	public EbvProvider removeEbvProvider(EbvProvider ebvProvider) {
		getEbvProviders().remove(ebvProvider);
		ebvProvider.setEbvClass(null);

		return ebvProvider;
	}

	public List<EbvVar> getEbvVars() {
		return this.ebvVars;
	}

	public void setEbvVars(List<EbvVar> ebvVars) {
		this.ebvVars = ebvVars;
	}

	public EbvVar addEbvVar(EbvVar ebvVar) {
		getEbvVars().add(ebvVar);
		ebvVar.setEbvClass(this);

		return ebvVar;
	}

	public EbvVar removeEbvVar(EbvVar ebvVar) {
		getEbvVars().remove(ebvVar);
		ebvVar.setEbvClass(null);

		return ebvVar;
	}

}
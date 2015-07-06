package com.ibm.gbs.eubon.ebp.core.jpa.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the EBV_VAR database table.
 * 
 */
@Entity
@Table(name="EBV_VAR")
@NamedQuery(name="EbvVar.findAll", query="SELECT e FROM EbvVar e")
public class EbvVar implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="EBV_VAR_ID_GENERATOR", sequenceName="EBV_VAR_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EBV_VAR_ID_GENERATOR")
	private long id;

	private String description;

	private String name;

	//bi-directional many-to-one association to EbvClass
	@ManyToOne
	@JoinColumn(name="EBV_CLASS_ID")
	private EbvClass ebvClass;

	public EbvVar() {
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

	public EbvClass getEbvClass() {
		return this.ebvClass;
	}

	public void setEbvClass(EbvClass ebvClass) {
		this.ebvClass = ebvClass;
	}

}
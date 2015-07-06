package com.ibm.gbs.eubon.ebp.core.jpa.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the COUNTRY database table.
 * 
 */
@Entity
@NamedQuery(name="Country.findAll", query="SELECT c FROM Country c")
public class Country implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="COUNTRY_ID_GENERATOR", sequenceName="COUNTRY_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="COUNTRY_ID_GENERATOR")
	private long id;

	private String code;

	private String name;

	public Country() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



}
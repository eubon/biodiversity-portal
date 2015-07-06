package com.ibm.gbs.eubon.ebp.core.jpa.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the EBV_PROVIDER database table.
 * 
 */
@Entity
@Table(name="EBV_PROVIDER")
@NamedQuery(name="EbvProvider.findAll", query="SELECT e FROM EbvProvider e")
public class EbvProvider implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="EBV_PROVIDER_ID_GENERATOR", sequenceName="EBV_PROVIDER_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EBV_PROVIDER_ID_GENERATOR")
	private long id;

	//bi-directional many-to-one association to EbvClass
	@ManyToOne
	@JoinColumn(name="EBV_CLASS_ID")
	private EbvClass ebvClass;

	//bi-directional many-to-one association to Provider
	@ManyToOne
	private Provider provider;

	public EbvProvider() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public EbvClass getEbvClass() {
		return this.ebvClass;
	}

	public void setEbvClass(EbvClass ebvClass) {
		this.ebvClass = ebvClass;
	}

	public Provider getProvider() {
		return this.provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}

}
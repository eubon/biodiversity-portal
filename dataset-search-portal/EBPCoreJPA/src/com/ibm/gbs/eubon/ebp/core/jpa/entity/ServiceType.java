package com.ibm.gbs.eubon.ebp.core.jpa.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the SERVICE_TYPE database table.
 * 
 */
@Entity
@Table(name="SERVICE_TYPE")
@NamedQuery(name="ServiceType.findAll", query="SELECT s FROM ServiceType s")
public class ServiceType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SERVICE_TYPE_ID_GENERATOR", sequenceName="SERVICE_TYPE_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SERVICE_TYPE_ID_GENERATOR")
	private long id;

	private String description;

	private String name;

	//bi-directional many-to-one association to Provider
	@OneToMany(mappedBy="serviceType")
	private List<Provider> providers;

	public ServiceType() {
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

	public List<Provider> getProviders() {
		return this.providers;
	}

	public void setProviders(List<Provider> providers) {
		this.providers = providers;
	}

	public Provider addProvider(Provider provider) {
		getProviders().add(provider);
		provider.setServiceType(this);

		return provider;
	}

	public Provider removeProvider(Provider provider) {
		getProviders().remove(provider);
		provider.setServiceType(null);

		return provider;
	}

}
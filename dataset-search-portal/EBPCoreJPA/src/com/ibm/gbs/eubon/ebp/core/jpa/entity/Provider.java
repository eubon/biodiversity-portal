package com.ibm.gbs.eubon.ebp.core.jpa.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the PROVIDERS database table.
 * 
 */
@Entity
@Table(name="PROVIDERS")
@NamedQuery(name="Provider.findAll", query="SELECT p FROM Provider p")
public class Provider implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PROVIDERS_ID_GENERATOR", sequenceName="PROVIDERS_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PROVIDERS_ID_GENERATOR")
	private long id;

	@Column(name="FLG_DEFAULT")
	private boolean flgDefault;

	private String name;

	private Integer priority;

	@Column(name="SERVICE_URL")
	private String serviceUrl;

	//bi-directional many-to-one association to EbvProvider
	@OneToMany(mappedBy="provider")
	private List<EbvProvider> ebvProviders;

	//bi-directional many-to-one association to FunctionProvider
	@OneToMany(mappedBy="provider", cascade = CascadeType.ALL)
	private List<FunctionProvider> functionProviders;

	//bi-directional many-to-one association to ServiceType
	@ManyToOne
	@JoinColumn(name="SERVICE_TYPE_ID")
	private ServiceType serviceType;

	//bi-directional many-to-one association to Request
	@OneToMany(mappedBy="provider")
	private List<Request> requests;

	public Provider() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean getFlgDefault() {
		return this.flgDefault;
	}

	public void setFlgDefault(boolean flgDefault) {
		this.flgDefault = flgDefault;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPriority() {
		return this.priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public String getServiceUrl() {
		return this.serviceUrl;
	}

	public void setServiceUrl(String serviceUrl) {
		this.serviceUrl = serviceUrl;
	}

	public List<EbvProvider> getEbvProviders() {
		return this.ebvProviders;
	}

	public void setEbvProviders(List<EbvProvider> ebvProviders) {
		this.ebvProviders = ebvProviders;
	}

	public EbvProvider addEbvProvider(EbvProvider ebvProvider) {
		getEbvProviders().add(ebvProvider);
		ebvProvider.setProvider(this);

		return ebvProvider;
	}

	public EbvProvider removeEbvProvider(EbvProvider ebvProvider) {
		getEbvProviders().remove(ebvProvider);
		ebvProvider.setProvider(null);

		return ebvProvider;
	}

	public List<FunctionProvider> getFunctionProviders() {
		return this.functionProviders;
	}

	public void setFunctionProviders(List<FunctionProvider> functionProviders) {
		this.functionProviders = functionProviders;
	}

	public FunctionProvider addFunctionProvider(FunctionProvider functionProvider) {
		getFunctionProviders().add(functionProvider);
		functionProvider.setProvider(this);

		return functionProvider;
	}

	public FunctionProvider removeFunctionProvider(FunctionProvider functionProvider) {
		getFunctionProviders().remove(functionProvider);
		functionProvider.setProvider(null);

		return functionProvider;
	}

	public ServiceType getServiceType() {
		return this.serviceType;
	}

	public void setServiceType(ServiceType serviceType) {
		this.serviceType = serviceType;
	}

	public List<Request> getRequests() {
		return this.requests;
	}

	public void setRequests(List<Request> requests) {
		this.requests = requests;
	}

	public Request addRequest(Request request) {
		getRequests().add(request);
		request.setProvider(this);

		return request;
	}

	public Request removeRequest(Request request) {
		getRequests().remove(request);
		request.setProvider(null);

		return request;
	}

}
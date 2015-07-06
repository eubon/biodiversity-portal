package com.ibm.gbs.eubon.ebp.core.jpa.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the FUNCTION_PROVIDER database table.
 * 
 */
@Entity
@Table(name="FUNCTION_PROVIDER")
@NamedQuery(name="FunctionProvider.findAll", query="SELECT f FROM FunctionProvider f")
public class FunctionProvider implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="FUNCTION_PROVIDER_ID_GENERATOR", sequenceName="FUNCTION_PROVIDER_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="FUNCTION_PROVIDER_ID_GENERATOR")
	private long id;

	//bi-directional many-to-one association to Function
	@ManyToOne
	@JoinColumn(name="ID_FUNCTION")
	private Function function;

	//bi-directional many-to-one association to Provider
	@ManyToOne
	@JoinColumn(name="ID_PROVIDER")
	private Provider provider;

	public FunctionProvider() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Function getFunction() {
		return this.function;
	}

	public void setFunction(Function function) {
		this.function = function;
	}

	public Provider getProvider() {
		return this.provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}

}
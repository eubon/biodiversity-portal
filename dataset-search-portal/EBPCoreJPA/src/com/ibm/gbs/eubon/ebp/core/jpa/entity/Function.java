package com.ibm.gbs.eubon.ebp.core.jpa.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the FUNCTIONS database table.
 * 
 */
@Entity
@Table(name="FUNCTIONS")
@NamedQuery(name="Function.findAll", query="SELECT f FROM Function f")
public class Function implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="FUNCTIONS_ID_GENERATOR", sequenceName="FUNCTIONS_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="FUNCTIONS_ID_GENERATOR")
	private long id;

	private String description;

	private String name;

	//bi-directional many-to-one association to FunctionProvider
	@OneToMany(mappedBy="function")
	private List<FunctionProvider> functionProviders;

	public Function() {
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

	public List<FunctionProvider> getFunctionProviders() {
		return this.functionProviders;
	}

	public void setFunctionProviders(List<FunctionProvider> functionProviders) {
		this.functionProviders = functionProviders;
	}

	public FunctionProvider addFunctionProvider(FunctionProvider functionProvider) {
		getFunctionProviders().add(functionProvider);
		functionProvider.setFunction(this);

		return functionProvider;
	}

	public FunctionProvider removeFunctionProvider(FunctionProvider functionProvider) {
		getFunctionProviders().remove(functionProvider);
		functionProvider.setFunction(null);

		return functionProvider;
	}

}
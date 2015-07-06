package com.ibm.gbs.eubon.ebp.core.dao;

import java.util.List;

import com.ibm.gbs.eubon.ebp.core.jpa.entity.Provider;
import com.ibm.gbs.tramitator.jpa.util.Criterio;

public interface ProviderDAO {

	
	Provider read(long id);

	List<Provider> listadoPaginacion(Criterio criterio);

	int countPaginacion(Criterio criterio);
	
	public List<Provider> getProviders();

}
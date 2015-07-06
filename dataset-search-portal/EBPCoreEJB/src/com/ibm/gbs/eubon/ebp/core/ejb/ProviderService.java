package com.ibm.gbs.eubon.ebp.core.ejb;

import java.util.List;

import com.ibm.gbs.eubon.ebp.core.jpa.entity.Content;
import com.ibm.gbs.eubon.ebp.core.jpa.entity.Provider;
import com.ibm.gbs.tramitator.jpa.util.Criterio;

public interface ProviderService {

	Provider read(long id);

	List<Provider> getProviders();

	List<Provider> listadoPaginacion(Criterio criterio);

	int countPaginacion(Criterio criterio);

	Provider guardar(Provider provider); 
	
	void delete(Provider provider);
}

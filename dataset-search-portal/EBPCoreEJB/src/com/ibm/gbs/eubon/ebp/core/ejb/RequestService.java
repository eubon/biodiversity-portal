package com.ibm.gbs.eubon.ebp.core.ejb;

import java.util.List;

import com.ibm.gbs.eubon.ebp.core.jpa.entity.Request;
import com.ibm.gbs.tramitator.jpa.util.Criterio;

public interface RequestService {

	Request read(long id);

	List<Request> getRequests();

	List<Request> listadoPaginacion(Criterio criterio);

	int countPaginacion(Criterio criterio);

	Request guardar(Request request); 
	

}

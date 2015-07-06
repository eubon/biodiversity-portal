package com.ibm.gbs.eubon.ebp.core.dao;

import java.util.List;

import com.ibm.gbs.eubon.ebp.core.jpa.entity.Request;
import com.ibm.gbs.tramitator.jpa.util.Criterio;

public interface RequestDAO {

	
	Request read(long id);

	List<Request> listadoPaginacion(Criterio criterio);

	int countPaginacion(Criterio criterio);
	
	public List<Request> getRequests();

}
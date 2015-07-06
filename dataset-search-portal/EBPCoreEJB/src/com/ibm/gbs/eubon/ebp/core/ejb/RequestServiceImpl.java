package com.ibm.gbs.eubon.ebp.core.ejb;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import org.apache.log4j.Logger;

import com.ibm.gbs.eubon.ebp.core.dao.RequestDAO;
import com.ibm.gbs.eubon.ebp.core.jpa.entity.Request;
import com.ibm.gbs.eubon.ebp.core.jpa.entity.UserRequest;
import com.ibm.gbs.tramitator.ejb.crud.ServicioCRUD;
import com.ibm.gbs.tramitator.jpa.util.Criterio;
import com.ibm.gbs.tramitator.util.log.InterceptorLog;

@Stateless
@Interceptors({ InterceptorLog.class })
public class RequestServiceImpl implements RequestService {

	Logger log = Logger.getLogger(RequestServiceImpl.class);

	@EJB
	RequestDAO dao;

	@EJB
	ServicioCRUD crud;
	
	@Override
	public List<Request> listadoPaginacion(Criterio criterio) {
		List<Request> listado = dao.listadoPaginacion(criterio);
		
		return listado;
	}

	@Override
	public int countPaginacion(Criterio criterio) {
		int countPaginacion = dao.countPaginacion(criterio);
		
		return countPaginacion;
	}

	@Override
	public Request guardar(Request request) 
	{
		if (request.getUserRequest() != null)
		{
			UserRequest ur = (UserRequest) crud.guardar(request.getUserRequest());
			request.setUserRequest(ur);
		}
		
		Request c = (Request) crud.guardar(request);
		
		return c;	
		
	}

	@Override
	public Request read(long id) {
		
		Request obj = (Request) dao.read(id);
		
		if (obj != null)
		{
		  log.debug("RequestServiceImpl.read - Request=" + obj.getId() + "-" );
		}
		else
		{
			log.debug("RequestServiceImpl.read - Not found-" );
		}
			
		return obj;
	}
	


	@Override
	public List<Request> getRequests() 
	{
		List<Request> lista = dao.getRequests();
		return lista;
	}

}

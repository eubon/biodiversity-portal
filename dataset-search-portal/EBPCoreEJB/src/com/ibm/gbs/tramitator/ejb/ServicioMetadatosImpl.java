package com.ibm.gbs.tramitator.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import org.apache.log4j.Logger;

import com.ibm.gbs.eubon.ebp.core.jpa.entity.Category;
import com.ibm.gbs.tramitator.dao.MetadatosDAO;
import com.ibm.gbs.tramitator.ejb.crud.ServicioCRUD;
import com.ibm.gbs.tramitator.util.Constantes;
import com.ibm.gbs.tramitator.util.cache.Cache;
import com.ibm.gbs.tramitator.util.log.InterceptorLog;

@Stateless
@Interceptors({ InterceptorLog.class })
public class ServicioMetadatosImpl implements ServicioMetadatos {

	Logger log = Logger.getLogger(ServicioMetadatosImpl.class);

	@EJB
	MetadatosDAO metadatosDAO;

	@EJB
	ServicioCRUD srvCrud;


	@SuppressWarnings("unchecked")
	@Override
	public List<Category> getCategories() {
		log.debug("ServicioMetadatosImpl.getCategories Inicio");
		
		List<Category> list = (List<Category>) Cache.getInstancia().get(Constantes.CACHE_KEY_LIST_CATEGORIES);
		if (list == null) 
		{
			initCategories();
			list = (List<Category>) Cache.getInstancia().get(Constantes.CACHE_KEY_LIST_CATEGORIES);
		}
		
		if (list == null) {
			list = new ArrayList<Category>();
		}
		log.debug("ServicioMetadatosImpl.getCategories Fin");
		return list;
	}
	
	

	@SuppressWarnings("unchecked")
	private synchronized void initCategories() {
		log.debug("ServicioMetadatosImpl.initCategories Inicio");
		
		List<Category> lista = metadatosDAO.getCategories();
		Cache.getInstancia().put(Constantes.CACHE_KEY_LIST_CATEGORIES, lista);
		
		log.debug("ServicioMetadatosImpl.initEstadosPonencia Fin");
	}
	
	

}

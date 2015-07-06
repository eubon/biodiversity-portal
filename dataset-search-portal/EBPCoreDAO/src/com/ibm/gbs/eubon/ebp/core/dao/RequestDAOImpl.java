package com.ibm.gbs.eubon.ebp.core.dao;

import java.util.List;



import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.ibm.gbs.eubon.ebp.core.jpa.entity.Category;
import com.ibm.gbs.eubon.ebp.core.jpa.entity.Request;
import com.ibm.gbs.tramitator.dao.UtilDAO;
import com.ibm.gbs.tramitator.jpa.util.Criterio;

@Stateless
public class RequestDAOImpl implements RequestDAO {

	private Logger log = Logger.getLogger(RequestDAOImpl.class);
	
	@EJB
	UtilDAO utilDao;


	@PersistenceContext(unitName = "EBPCoreJPA", type = PersistenceContextType.TRANSACTION)
	EntityManager manager;



	@Override
	public Request read(long id) {
		Request obj = utilDao.find(Request.class, id);
		return obj;
	}

	@Override
	public List<Request> listadoPaginacion(Criterio criterio) 
	{
		List<Request> listadoPaginacion = utilDao.listadoPaginacion(Request.class, criterio);
		return listadoPaginacion;
	}

	@Override
	public int countPaginacion(Criterio criterio) {
		return utilDao.countPaginacion(Request.class, criterio);
	}
	
	@Override
	public List<Request> getRequests() {
		log.debug("RequestDAOImpl.getCategories Inicio");
		Query query = manager.createQuery("SELECT t FROM Request t ORDER BY t.name");
		@SuppressWarnings("unchecked")
		List<Request> lista = query.getResultList();
		int size = 0;
		if (lista != null)
			size = lista.size();
		log.debug("RequestDAOImpl.getRequests Fin. Elementos: -" + size + "-");
		return lista;
	}

}
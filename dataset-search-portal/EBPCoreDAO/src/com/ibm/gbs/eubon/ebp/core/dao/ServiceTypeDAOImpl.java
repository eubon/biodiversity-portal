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
import com.ibm.gbs.eubon.ebp.core.jpa.entity.ServiceType;
import com.ibm.gbs.tramitator.dao.UtilDAO;
import com.ibm.gbs.tramitator.jpa.util.Criterio;

@Stateless
public class ServiceTypeDAOImpl implements ServiceTypeDAO {

	private Logger log = Logger.getLogger(ServiceTypeDAOImpl.class);
	
	@EJB
	UtilDAO utilDao;


	@PersistenceContext(unitName = "EBPCoreJPA", type = PersistenceContextType.TRANSACTION)
	EntityManager manager;



	@Override
	public ServiceType read(long id) {
		ServiceType obj = utilDao.find(ServiceType.class, id);
		return obj;
	}
	
	@Override
	public List<ServiceType> getServiceTypes() {
		log.debug("ServiceTypeDAOImpl.getServiceTypes Inicio");
		Query query = manager.createQuery("SELECT t FROM ServiceType t ORDER BY t.name");
		@SuppressWarnings("unchecked")
		List<ServiceType> lista = query.getResultList();
		int size = 0;
		if (lista != null)
			size = lista.size();
		log.debug("ServiceTypeDAOImpl.getServiceTypes Fin. Elementos: -" + size + "-");
		return lista;
	}

}
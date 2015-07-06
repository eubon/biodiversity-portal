package com.ibm.gbs.eubon.ebp.core.dao;

import java.util.Iterator;
import java.util.List;






import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;

import com.ibm.gbs.eubon.ebp.core.jpa.entity.Category;
import com.ibm.gbs.eubon.ebp.core.jpa.entity.Content;
import com.ibm.gbs.eubon.ebp.core.jpa.entity.FunctionProvider;
import com.ibm.gbs.eubon.ebp.core.jpa.entity.Provider;
import com.ibm.gbs.tramitator.dao.UtilDAO;
import com.ibm.gbs.tramitator.jpa.util.Criterio;

@Stateless
public class ProviderDAOImpl implements ProviderDAO {

	private Logger log = Logger.getLogger(ProviderDAOImpl.class);
	
	@EJB
	UtilDAO utilDao;

	@PersistenceContext(unitName = "EBPCoreJPA", type = PersistenceContextType.TRANSACTION)
	EntityManager manager;

	@Override
	public Provider read(long id) {
		Provider provider = utilDao.find(Provider.class, id);
		for (Iterator iterator = provider.getFunctionProviders().iterator(); iterator.hasNext();) {
			FunctionProvider fp = (FunctionProvider) iterator.next();
			Hibernate.initialize(fp.getId());
		}
		return provider;
	}

	@Override
	public List<Provider> listadoPaginacion(Criterio criterio) 
	{
		List<Provider> listadoPaginacion = utilDao.listadoPaginacion(Provider.class, criterio);
		
		for (Provider exp : listadoPaginacion) {
			exp.getName();
//			Hibernate.initialize(exp.getFunctionProviders());
		}
		return listadoPaginacion;
	}

	@Override
	public int countPaginacion(Criterio criterio) {
		return utilDao.countPaginacion(Provider.class, criterio);
	}
	
	@Override
	public List<Provider> getProviders() {
		log.debug("ProviderDAOImpl.getProviders Inicio");
		Query query = manager.createQuery("SELECT t FROM Provider t ORDER BY t.name");
		@SuppressWarnings("unchecked")
		List<Provider> lista = query.getResultList();
		int size = 0;
		if (lista != null)
			size = lista.size();
		log.debug("ProviderDAOImpl.getProviders Fin. Elementos: -" + size + "-");
		return lista;
	}

}
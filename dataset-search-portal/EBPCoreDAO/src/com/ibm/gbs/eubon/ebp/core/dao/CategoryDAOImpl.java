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
import com.ibm.gbs.tramitator.dao.UtilDAO;
import com.ibm.gbs.tramitator.jpa.util.Criterio;

@Stateless
public class CategoryDAOImpl implements CategoryDAO {

	private Logger log = Logger.getLogger(CategoryDAOImpl.class);
	
	@EJB
	UtilDAO utilDao;


	@PersistenceContext(unitName = "EBPCoreJPA", type = PersistenceContextType.TRANSACTION)
	EntityManager manager;



	@Override
	public Category read(long id) {
		Category category = utilDao.find(Category.class, id);
		return category;
	}

	@Override
	public List<Category> listadoPaginacion(Criterio criterio) 
	{
		List<Category> listadoPaginacion = utilDao.listadoPaginacion(Category.class, criterio);
		return listadoPaginacion;
	}

	@Override
	public int countPaginacion(Criterio criterio) {
		return utilDao.countPaginacion(Category.class, criterio);
	}
	
	@Override
	public List<Category> getCategories() {
		log.debug("CategoryDAOImpl.getCategories Inicio");
		Query query = manager.createQuery("SELECT t FROM Category t ORDER BY t.name");
		@SuppressWarnings("unchecked")
		List<Category> lista = query.getResultList();
		int size = 0;
		if (lista != null)
			size = lista.size();
		log.debug("CategoryDAOImpl.getCategories Fin. Elementos: -" + size + "-");
		return lista;
	}

}
package com.ibm.gbs.eubon.ebp.core.dao;

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
import com.ibm.gbs.tramitator.dao.UtilDAO;
import com.ibm.gbs.tramitator.jpa.util.Criterio;

@Stateless
public class ContentDAOImpl implements ContentDAO {
	
	private Logger log = Logger.getLogger(ContentDAOImpl.class);

	@PersistenceContext(unitName="EBPCoreJPA",type=PersistenceContextType.TRANSACTION)
	EntityManager manager;
	
	@EJB
	UtilDAO utilDao;
	
	@Override
	public Content read(long id) {
		Content content = utilDao.find(Content.class, id);
		return content;
	}

	@Override
	public List<Content> listadoPaginacion(Criterio criterio) {
		List<Content> listadoPaginacion = utilDao.listadoPaginacion(Content.class, criterio);
		// Fetch manual
		for (Content exp : listadoPaginacion) {
			exp.getDescription();
			Hibernate.initialize(exp.getCategory());
		}
		return listadoPaginacion;
	}

	@Override
	public int countPaginacion(Criterio criterio) {
		int countPaginacion = utilDao.countPaginacion(Content.class, criterio);
		return countPaginacion;
	}	

	@Override
	public Content update(Content content) {
		Content c = (Content)utilDao.update(content);
		
		return c;
	}
	
	
}

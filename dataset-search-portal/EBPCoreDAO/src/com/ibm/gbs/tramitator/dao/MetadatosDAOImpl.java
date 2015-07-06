package com.ibm.gbs.tramitator.dao;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.apache.log4j.Logger;

import com.ibm.gbs.eubon.ebp.core.dao.CategoryDAO;
import com.ibm.gbs.eubon.ebp.core.jpa.entity.Category;

@Stateless
public class MetadatosDAOImpl implements MetadatosDAO {

	private Logger log = Logger.getLogger(MetadatosDAOImpl.class);

	@PersistenceContext(unitName = "EBPCoreJPA", type = PersistenceContextType.TRANSACTION)
	EntityManager manager;

	@EJB
	UtilDAO utilDao;
	
	@EJB
	CategoryDAO categoryDAO;


	@Override
	public List<Category> getCategories() 
	{
		List<Category> lista = categoryDAO.getCategories();
		return lista;
	}
	

}

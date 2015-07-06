package com.ibm.gbs.eubon.ebp.core.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.ibm.gbs.eubon.ebp.core.dao.CategoryDAO;
import com.ibm.gbs.eubon.ebp.core.jpa.entity.Category;
import com.ibm.gbs.tramitator.ejb.crud.ServicioCRUD;
import com.ibm.gbs.tramitator.jpa.util.Criterio;
import com.ibm.gbs.tramitator.util.log.InterceptorLog;

@Stateless
@Interceptors({ InterceptorLog.class })
public class CategoryServiceImpl implements CategoryService {

	Logger log = Logger.getLogger(CategoryServiceImpl.class);

	@EJB
	CategoryDAO categoryDAO;

	@EJB
	ServicioCRUD crud;
	
	@Override
	public List<Category> listadoPaginacion(Criterio criterio) {
		List<Category> listado = categoryDAO.listadoPaginacion(criterio);
		
		return listado;
	}

	@Override
	public int countPaginacion(Criterio criterio) {
		int countPaginacion = categoryDAO.countPaginacion(criterio);
		
		return countPaginacion;
	}

	@Override
	public Category guardar(Category category) {
		
		Category c = (Category) crud.guardar(category);
		
		return c;	
		
	}
	
	@Override
	public void delete(Category category) 
	{		
		crud.delete(category);		
	}

	@Override
	public Category read(long id) {
		
		Category category = (Category) categoryDAO.read(id);
		
		if (category!= null)
		{
		  log.debug("CategoryServiceImpl.getCategory - Category=" + category.getId() + "-" + category.getName());
		}
		else
		{
			log.debug("CategoryServiceImpl.getCategory - Not found-" );
		}
			
		return category;
	}
	


	@Override
	public List<Category> getCategories() 
	{
		List<Category> lista = categoryDAO.getCategories();
		return lista;
	}

	
	

}

package com.ibm.gbs.eubon.ebp.core.dao;

import java.util.List;

import com.ibm.gbs.eubon.ebp.core.jpa.entity.Category;
import com.ibm.gbs.tramitator.jpa.util.Criterio;

public interface CategoryDAO {

	
	Category read(long id);

	List<Category> listadoPaginacion(Criterio criterio);

	int countPaginacion(Criterio criterio);
	
	public List<Category> getCategories();

}
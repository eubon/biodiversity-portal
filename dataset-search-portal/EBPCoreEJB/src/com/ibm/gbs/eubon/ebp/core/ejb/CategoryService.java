package com.ibm.gbs.eubon.ebp.core.ejb;

import java.util.List;

import com.ibm.gbs.eubon.ebp.core.jpa.entity.Category;
import com.ibm.gbs.tramitator.jpa.util.Criterio;

public interface CategoryService {

	Category read(long id);

	List<Category> getCategories();

	List<Category> listadoPaginacion(Criterio criterio);

	int countPaginacion(Criterio criterio);

	Category guardar(Category category); 
	
	void delete(Category category);
	

}

package com.ibm.gbs.tramitator.ejb;

import java.util.List;

import com.ibm.gbs.eubon.ebp.core.jpa.entity.Category;
import com.ibm.gbs.eubon.ebp.core.jpa.entity.Content;
import com.ibm.gbs.tramitator.jpa.util.Criterio;

public interface ServicioMetadatos {
	
	List<Category> getCategories();
	


}

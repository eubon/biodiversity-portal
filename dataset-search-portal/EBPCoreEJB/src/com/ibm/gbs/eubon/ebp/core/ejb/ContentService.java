package com.ibm.gbs.eubon.ebp.core.ejb;

import java.util.List;

import com.ibm.gbs.eubon.ebp.core.jpa.entity.Category;
import com.ibm.gbs.eubon.ebp.core.jpa.entity.Content;
import com.ibm.gbs.tramitator.jpa.util.Criterio;

public interface ContentService {

	Content read(long id);

	List<Content> listadoPaginacion(Criterio criterio);

	int countPaginacion(Criterio criterio);

	Content guardar(Content content); 
	
	void delete(Content content);

}

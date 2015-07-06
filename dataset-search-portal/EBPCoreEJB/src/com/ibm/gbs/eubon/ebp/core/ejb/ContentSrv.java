package com.ibm.gbs.eubon.ebp.core.ejb;

import java.util.List;
import com.ibm.gbs.eubon.ebp.core.jpa.entity.Content;
import com.ibm.gbs.tramitator.jpa.util.Criterio;


public interface ContentSrv {

	//List Methods
	List<Content> listadoPaginacion(Criterio criterio);
	int countPaginacion(Criterio criterio); 
	
	//CRUD
	Content read(long id);
	void update(Content content);
	void newInstance(Content content);
	
}

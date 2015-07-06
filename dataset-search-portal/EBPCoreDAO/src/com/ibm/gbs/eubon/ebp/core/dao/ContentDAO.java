package com.ibm.gbs.eubon.ebp.core.dao;

import java.util.List;

import com.ibm.gbs.eubon.ebp.core.jpa.entity.Content;
import com.ibm.gbs.tramitator.jpa.util.Criterio;

public interface ContentDAO {

	//list
	List<Content> listadoPaginacion(Criterio criterio);
	int countPaginacion(Criterio criterio);

	//CRUD
	Content read(long id);
	Content update(Content content);

}
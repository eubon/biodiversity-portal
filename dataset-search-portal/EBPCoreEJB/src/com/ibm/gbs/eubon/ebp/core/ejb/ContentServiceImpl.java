package com.ibm.gbs.eubon.ebp.core.ejb;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import org.apache.log4j.Logger;

import com.ibm.gbs.eubon.ebp.core.dao.ContentDAO;
import com.ibm.gbs.eubon.ebp.core.jpa.entity.Category;
import com.ibm.gbs.eubon.ebp.core.jpa.entity.Content;
import com.ibm.gbs.tramitator.ejb.crud.ServicioCRUD;
import com.ibm.gbs.tramitator.jpa.util.Criterio;
import com.ibm.gbs.tramitator.util.log.InterceptorLog;

@Stateless
@Interceptors({ InterceptorLog.class })
public class ContentServiceImpl implements ContentService {

	Logger log = Logger.getLogger(ContentServiceImpl.class);

	@EJB
	ContentDAO contentDAO;

	@EJB
	ServicioCRUD crud;
	
	@Override
	public List<Content> listadoPaginacion(Criterio criterio) {
		List<Content> listado = contentDAO.listadoPaginacion(criterio);
		
		return listado;
	}

	@Override
	public int countPaginacion(Criterio criterio) {
		int countPaginacion = contentDAO.countPaginacion(criterio);
		
		return countPaginacion;
	}

	@Override
	public Content guardar(Content content) {
		
		Content c = (Content) crud.guardar(content);
		
		return c;	
		
	}

	@Override
	public Content read(long id) {
		
		Content content = (Content) contentDAO.read(id);
		
		if (content!= null)
		{
		  log.debug("ContentServiceImpl.getContent - Content=" + content.getId() + "-" + content.getTitle());
		}
		else
		{
			log.debug("ContentServiceImpl.getContent - Not found-" );
		}
			
		return content;
	}
	
	@Override
	public void delete(Content content) 
	{		
		crud.delete(content);		
	}
	

}

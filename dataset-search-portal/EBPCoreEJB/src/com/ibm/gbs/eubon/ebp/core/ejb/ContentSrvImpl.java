package com.ibm.gbs.eubon.ebp.core.ejb;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import org.apache.log4j.Logger;

import com.ibm.gbs.eubon.ebp.core.dao.ContentDAO;
import com.ibm.gbs.eubon.ebp.core.jpa.entity.Content;
import com.ibm.gbs.tramitator.jpa.util.Criterio;
import com.ibm.gbs.tramitator.util.log.InterceptorLog;

@Stateless
@Interceptors({InterceptorLog.class})
public class ContentSrvImpl implements ContentSrv {

	Logger log = Logger.getLogger(ContentSrvImpl.class);

	@EJB
	ContentDAO dao; 

	
	@Override
	public void newInstance(Content content)
	{
		dao.update(content); 
	}

	@Override
	public Content read(long id) {
		Content content = dao.read(id);
		return content;
	}	
	

	@Override
	public List<Content> listadoPaginacion(Criterio criterio) {
		List<Content> listado = dao.listadoPaginacion(criterio);
		return listado;
	}

	@Override
	public int countPaginacion(Criterio criterio) {
		return dao.countPaginacion(criterio);
	}

	@Override
	public void update(Content content) {
		dao.update(content);		
	}


}

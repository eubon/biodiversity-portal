package com.ibm.gbs.tramitator.jsf.mbean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;

import com.ibm.gbs.eubon.ebp.core.ejb.CategoryService;
import com.ibm.gbs.eubon.ebp.core.ejb.ContentService;
import com.ibm.gbs.eubon.ebp.core.jpa.entity.Category;
import com.ibm.gbs.eubon.ebp.core.jpa.entity.Content;
import com.ibm.gbs.tramitator.ejb.ServicioMetadatos;
import com.ibm.gbs.tramitator.jpa.util.Criterio;

public class ContentDataModel extends PaginacionDataModel<Content> {
	

	@EJB
	private ContentService contentSrv;

	@EJB
	private ServicioMetadatos srvMetadatos;

	
	
	public ContentDataModel(ContentService contentSrv) {
		super(Content.class); 
		this.contentSrv = contentSrv;
	}
	

	List<Content> list;
	
	@Override
	protected List<Content> walk(Criterio criterio) {
		list = getContents(criterio);
		return list;
	}

	private List<Content> getContents(Criterio criterio) {
		List<Content> lista = new ArrayList<Content>();
		lista = contentSrv.listadoPaginacion(criterio);
		return lista;
	}

	@Override
	protected int getRowCountImpl(Criterio criterio) {
		int countPaginacion = 0;
		countPaginacion = contentSrv.countPaginacion(criterio);
		
		return countPaginacion;
	}

	@Override
	protected Content find(Object rowKey) {
		for (Content e : list){
			Long idRow = (Long) rowKey;
			Long idC = e.getId();
			if (idC.equals(idRow))
				return e;
		}
		return null;
	}

	@Override
	protected Object getId(Content t) {
		return t.getId();
	}

	Map<String, Object> filtros;
	Map<String, String> orders;


	@Override
	protected void initCriterio(Criterio criterio) {
		criterio.mergeFiltros(criteriosIniciales);
	}



	public ServicioMetadatos getSrvMetadatos() {
		return srvMetadatos;
	}

	public void setSrvMetadatos(ServicioMetadatos srvMetadatos) {
		this.srvMetadatos = srvMetadatos;
	}

	public ContentService getContentSrv() {
		return contentSrv;
	}

	public void setContentSrv(ContentService contentSrv) {
		this.contentSrv = contentSrv;
	}


}

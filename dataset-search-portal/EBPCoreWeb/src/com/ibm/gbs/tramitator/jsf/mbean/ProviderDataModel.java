package com.ibm.gbs.tramitator.jsf.mbean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;

import com.ibm.gbs.eubon.ebp.core.ejb.CategoryService;
import com.ibm.gbs.eubon.ebp.core.ejb.ProviderService;
import com.ibm.gbs.eubon.ebp.core.jpa.entity.Category;
import com.ibm.gbs.eubon.ebp.core.jpa.entity.Provider;
import com.ibm.gbs.tramitator.ejb.ServicioMetadatos;
import com.ibm.gbs.tramitator.jpa.util.Criterio;

public class ProviderDataModel extends PaginacionDataModel<Provider> {
	

	@EJB
	private ProviderService providerSrv;

	@EJB
	private ServicioMetadatos srvMetadatos;

	
	
	public ProviderDataModel(ProviderService srv) {
		super(Provider.class); 
		this.providerSrv = srv;
	}
	

	List<Provider> list;
	
	@Override
	protected List<Provider> walk(Criterio criterio) {
		list = getProviders(criterio);
		return list;
	}

	private List<Provider> getProviders(Criterio criterio) {
		List<Provider> lista = new ArrayList<Provider>();
		lista = providerSrv.listadoPaginacion(criterio);
		return lista;
	}

	@Override
	protected int getRowCountImpl(Criterio criterio) {
		int countPaginacion = 0;
		countPaginacion = providerSrv.countPaginacion(criterio);
		
		return countPaginacion;
	}

	@Override
	protected Provider find(Object rowKey) {
		for (Provider e : list){
			Long idRow = (Long) rowKey;
			Long idC = e.getId();
			if (idC.equals(idRow))
				return e;
		}
		return null;
	}

	@Override
	protected Object getId(Provider t) {
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

	public ProviderService getProviderSrv() {
		return providerSrv;
	}

	public void setProviderSrv(ProviderService srv) {
		this.providerSrv = srv;
	}
}

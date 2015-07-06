package com.ibm.gbs.tramitator.jsf.mbean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;

import com.ibm.gbs.eubon.ebp.core.ejb.CategoryService;
import com.ibm.gbs.eubon.ebp.core.jpa.entity.Category;
import com.ibm.gbs.tramitator.ejb.ServicioMetadatos;
import com.ibm.gbs.tramitator.jpa.util.Criterio;

public class CategoryDataModel extends PaginacionDataModel<Category> {
	

	@EJB
	private CategoryService categorySrv;

	@EJB
	private ServicioMetadatos srvMetadatos;

	
	
	public CategoryDataModel(CategoryService categorySrv) {
		super(Category.class); 
		this.categorySrv = categorySrv;
	}
	

	List<Category> list;
	
	@Override
	protected List<Category> walk(Criterio criterio) {
		list = getCategories(criterio);
		return list;
	}

	private List<Category> getCategories(Criterio criterio) {
		List<Category> lista = new ArrayList<Category>();
		lista = categorySrv.listadoPaginacion(criterio);
		return lista;
	}

	@Override
	protected int getRowCountImpl(Criterio criterio) {
		int countPaginacion = 0;
		countPaginacion = categorySrv.countPaginacion(criterio);
		
		return countPaginacion;
	}

	@Override
	protected Category find(Object rowKey) {
		for (Category e : list){
			Long idRow = (Long) rowKey;
			Long idC = e.getId();
			if (idC.equals(idRow))
				return e;
		}
		return null;
	}

	@Override
	protected Object getId(Category t) {
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

	public CategoryService getCategorySrv() {
		return categorySrv;
	}

	public void setCategorySrv(CategoryService categorySrv) {
		this.categorySrv = categorySrv;
	}
}

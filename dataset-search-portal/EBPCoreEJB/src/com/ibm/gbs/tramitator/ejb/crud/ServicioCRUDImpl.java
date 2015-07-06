package com.ibm.gbs.tramitator.ejb.crud;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.ibm.gbs.tramitator.dao.crud.CRUDDAO;

@Stateless
public class ServicioCRUDImpl implements ServicioCRUD{
	
	@EJB
	CRUDDAO dao;
	
	public Object obtenerObjeto(Class<?> class1, Object clave){
		return dao.obtenerObjeto(class1, clave);
	}

	@Override
	public List listado(Class<?> class1) {
		return dao.listado(class1);
	}

	@Override
	public Object guardar(Object objeto) 
	{
		return dao.guardar(objeto);
	}
	
	@Override
	public void delete(Object objeto) 
	{		
		dao.delete(objeto);		
	}
	
	@Override
	public <T extends Object> List<T> listadoOrdenado(Class<T> class1, String orden) {
		List<T> listadoOrdenado = dao.listadoOrdenado(class1, orden);
		return listadoOrdenado;
	}
	
	@Override
	public <T extends Object> List<T> listadoFiltroOrdenado(Class<T> class1, String where, String orden) {
		List<T> listado = dao.listadoFiltroOrdenado(class1, where, orden);
		return listado;
	}
	
}	

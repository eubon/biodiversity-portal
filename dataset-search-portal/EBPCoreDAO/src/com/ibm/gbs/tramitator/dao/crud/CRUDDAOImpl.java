package com.ibm.gbs.tramitator.dao.crud;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.apache.log4j.Logger;

@Stateless
public class CRUDDAOImpl implements CRUDDAO{

	private Logger log = Logger.getLogger(CRUDDAOImpl.class);

	@PersistenceContext(unitName="EBPCoreJPA",type=PersistenceContextType.TRANSACTION)
	EntityManager manager;
	
	@Override
	public List listado(Class<?> class1) {
		return manager.createQuery("FROM " + class1.getName()).getResultList();
	}

	@Override
	public Object obtenerObjeto(Class<?> class1, Object clave) {
		return manager.find(class1, clave);
	}

	@Override
	public Object guardar(Object objeto) {
		return manager.merge(objeto);
	}
	
	@Override
	public void delete(Object objeto) 
	{
		Object o = manager.merge(objeto);
		
		manager.remove(o);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T extends Object> List<T> listadoOrdenado(Class<T> class1, String orden) {
		List<T> listado =  manager.createQuery("FROM " + class1.getName() + " ORDER by " + orden).getResultList();
		return listado;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T extends Object> List<T> listadoFiltroOrdenado(Class<T> class1, String where, String orden) 
	{
		String select = "";
		if (where != null && !where.equals(""))
		{
			select = select + " WHERE " + where;			
		}
		if (orden != null && !orden.equals(""))
		{
			select = select + " ORDER by " + orden;			
		}

		List<T> listado =  manager.createQuery("FROM " + class1.getName() + select ).getResultList();
		return listado;
	}

}

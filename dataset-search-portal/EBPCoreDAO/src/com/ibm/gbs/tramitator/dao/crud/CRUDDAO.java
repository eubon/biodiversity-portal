package com.ibm.gbs.tramitator.dao.crud;

import java.util.List;


public interface CRUDDAO {

	Object obtenerObjeto(Class<?> class1, Object clave);
	
	List listado(Class<?> class1);

	Object guardar(Object objeto);
	
	<T extends Object> List<T> listadoOrdenado(Class<T> class1, String orden);
	
	<T extends Object> List<T> listadoFiltroOrdenado(Class<T> class1, String where, String orden);

	void delete(Object objeto);
}

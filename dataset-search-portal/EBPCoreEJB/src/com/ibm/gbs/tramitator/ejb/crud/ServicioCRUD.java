package com.ibm.gbs.tramitator.ejb.crud;

import java.util.List;


public interface ServicioCRUD {
	
	Object obtenerObjeto(Class<?> class1, Object clave);

	Object guardar(Object objeto);

	List listado(Class<?> class1);
	
	<T extends Object> List<T> listadoOrdenado(Class<T> class1, String orden);
	
	<T extends Object> List<T> listadoFiltroOrdenado(Class<T> class1, String where, String orden);
	
	void delete(Object objeto);

}

package com.ibm.gbs.tramitator.dao;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.ibm.gbs.tramitator.jpa.util.Criterio;

public interface UtilDAO {

	<T extends Object> List<T> findAll(Class<T> transferObjectClass,
			String sortColumn, boolean ascending);

	<T extends Object> List<T> findAll(Class<T> transferObjectClass);

	<T> T find(Class<T> transferObjectClass, Object id);

	<T> T find(Class<T> transferObjectClass, Long id);

	<T> List<T> findAllAndFilterLike(Class<T> transferObjectClass,
			Map<String, Object> params, String sortColumn, boolean ascending);

	<T> List<T> findAllByQuery(String query, Map<String, Object> params);

	<T> T findSingleByQuery(String query, Map<String, Object> params);

	<T> T find_s(Class<T> transferObjectClass, String id);

	<T> void updateCollection(Collection<T> obj);

	Object update(Object obj);

	<T> void remove(Class<T> tipo, Object id);

	<T> List<T> listadoPaginacion(Class<T> clase, Criterio criterio);

	<T extends Object> int countPaginacion(Class<T> clase, Criterio criterio);

	<T extends Object> int countPaginacionJPQL(Class<T> clase, Criterio criterio);

}
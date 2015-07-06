package com.ibm.gbs.tramitator.jpa.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import com.ibm.gbs.eubon.ebp.core.util.UtilCadenas;

public class Criterio {
	private int primero;
	private int num = Integer.MAX_VALUE;
	private Map<String, Object> filtros = new HashMap<String, Object>();
	private Map<String, Object> filtrosNe = new HashMap<String, Object>();
	private Map<String, Object> filtrosLike = new HashMap<String, Object>();
	private Map<String, Object> filtrosGe = new HashMap<String, Object>();
	private Map<String, Object> filtrosLe = new HashMap<String, Object>();
	private Map<String, String> orders = new LinkedHashMap<String, String>();
	private List<String> condiciones = new ArrayList<String>();

	public int getPrimero() {
		return primero;
	}

	public void setPrimero(int primero) {
		this.primero = primero;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public Map<String, Object> getFiltros() {
		return filtros;
	}

	public Map<String, Object> getFiltrosNe() {
		return filtrosNe;
	}

	public Map<String, Object> getFiltrosLike() {
		return filtrosLike;
	}

	public Map<String, Object> getFiltrosGe() {
		return filtrosGe;
	}

	public Map<String, Object> getFiltrosLe() {
		return filtrosLe;
	}

	public Map<String, String> getOrders() {
		return orders;
	}

	public List<String> getCondiciones() {
		return condiciones;
	}

	public void clear() {
		primero = num = 0;
		filtros.clear();
		filtrosNe.clear();
		filtrosLike.clear();
		filtrosGe.clear();
		filtrosLe.clear();
		orders.clear();
		condiciones.clear();
	}

	public void addFiltroLike(String key, Object value) {
		filtrosLike.put(key, value);
	}

	public void addFiltro(String key, Object value) {
		filtros.put(key, value);
	}

	public void addFiltroNe(String key, Object value) {
		filtrosNe.put(key, value);
	}

	public void addOrder(String key, String value) {
		orders.put(key, value);
	}

	public void addCondicion(String condicion) {
		condiciones.add(condicion);
	}

	public void addFiltroGe(String key, Object value) {
		filtrosGe.put(key, value);
	}

	public void addFiltroLe(String key, Object value) {
		filtrosLe.put(key, value);
	}

	public void mergeFiltros(Criterio criterio) {
		if (criterio == null) {
			return;
		}
		this.filtros.putAll(new HashMap<String, Object>(criterio.filtros));
		this.filtrosNe.putAll(new HashMap<String, Object>(criterio.filtrosNe));
		this.filtrosLike.putAll(new HashMap<String, Object>(
				criterio.filtrosLike));
		this.filtrosGe.putAll(new HashMap<String, Object>(criterio.filtrosGe));
		this.filtrosLe.putAll(new HashMap<String, Object>(criterio.filtrosLe));
		this.condiciones.addAll(new ArrayList<String>(criterio.condiciones));
	}

	public void convertirFilter2Filtro(Set<Entry<String, String>> filtros) {
		for (Entry<String, String> entry : filtros) {
			Object value = entry.getValue();
			if (value == null
					|| (value instanceof String && UtilCadenas.toStringNoNull(
							(String) value).equals("")))
				continue;
			Object objeto = value;
			if (objeto instanceof Date) {
				Date fecha = (Date) objeto;
				addFiltro(entry.getKey(), fecha);
				continue;
			}
			addFiltro(entry.getKey(), value);
		}
	}

	public void convertirFilter2FiltroGe(Set<Entry<String, String>> filtros) {
		for (Entry<String, String> entry : filtros) {
			Object value = entry.getValue();
			if (value == null
					|| (value instanceof String && UtilCadenas.toStringNoNull(
							(String) value).equals("")))
				continue;
			Object objeto = entry.getValue();
			if (objeto instanceof Date) {
				Date fecha = (Date) objeto;
				addFiltroGe(entry.getKey(), fecha);
				continue;
			}
			addFiltroGe(entry.getKey(), entry.getValue());
		}
	}

	public void convertirFilter2FiltroLe(Set<Entry<String, String>> filtros) {
		for (Entry<String, String> entry : filtros) {
			Object value = entry.getValue();
			if (value == null
					|| (value instanceof String && UtilCadenas.toStringNoNull(
							(String) value).equals("")))
				continue;
			Object objeto = entry.getValue();
			if (objeto instanceof Date) {
				Date fecha = (Date) objeto;
				addFiltroLe(entry.getKey(), fecha);
				continue;
			}
			addFiltroLe(entry.getKey(), entry.getValue());
		}
	}

	public void convertirFilter2FiltroLike(Set<Entry<String, String>> filtros) {
		for (Entry<String, String> entry : filtros) {
			Object value = entry.getValue();
			if (value == null
					|| (value instanceof String && UtilCadenas.toStringNoNull(
							(String) value).equals("")))
				continue;
			Object objeto = entry.getValue();
			if (objeto instanceof Date) {
				Date fecha = (Date) objeto;
				addFiltroLike(entry.getKey(), fecha);
				continue;
			}
			addFiltroLike(entry.getKey(), entry.getValue());
		}
	}

	public void convertirFilter2FiltroNe(Set<Entry<String, String>> filtros) {
		for (Entry<String, String> entry : filtros) {
			Object value = entry.getValue();
			if (value == null
					|| (value instanceof String && UtilCadenas.toStringNoNull(
							(String) value).equals("")))
				continue;
			Object objeto = entry.getValue();
			if (objeto instanceof Date) {
				Date fecha = (Date) objeto;
				addFiltroNe(entry.getKey(), fecha);
				continue;
			}
			addFiltroNe(entry.getKey(), entry.getValue());
		}
	}

	public boolean isEmpty() {
		if (getFiltros().size() > 0 || getFiltrosGe().size() > 0
				|| getFiltrosLe().size() > 0 || getFiltrosLike().size() > 0
				|| getFiltrosNe().size() > 0 || getCondiciones().size() > 0) {
			return false;
		} else {
			return true;
		}
	}

}

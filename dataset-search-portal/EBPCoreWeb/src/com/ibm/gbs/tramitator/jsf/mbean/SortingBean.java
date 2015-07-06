package com.ibm.gbs.tramitator.jsf.mbean;

import java.io.Serializable;
import java.util.HashMap;

import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import org.apache.log4j.Logger;
import org.richfaces.component.SortOrder;

public class SortingBean implements Serializable {

	private static final long serialVersionUID = -8558555001328399625L;
	
	private HashMap<String, SortOrder> orders = new HashMap<String, SortOrder>();
	private HashMap<String, String> filters = new HashMap<String, String>();
	private HashMap<String, String> filtersLe = new HashMap<String, String>();
	private HashMap<String, String> filtersGe = new HashMap<String, String>();
	
	private String campo;
	
	Logger logger = Logger.getLogger("com.ibm.gbs");

	
	public SortOrder orderField(String campo){
		SortOrder sortOrder = orders.get(campo);
		return sortOrder;
	}

	
	public void sortBy() {

		String campo = this.campo;//FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("campo");
		if (campo == null) {
			campo = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("campo");
		}
		logger.debug("SortingBean.sortBy():campo="+campo+"-");
		SortOrder temp = orders.get(campo);
		temp = SortOrder.ascending.equals(temp) ? SortOrder.descending : SortOrder.ascending;
		sortBy(campo, temp);
	}
	
	public void sortBy(AjaxBehaviorEvent param) {

		String campo = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("campo");
		logger.debug("SortingBean.sortBy():campo="+campo+"-");
		SortOrder temp = orders.get(campo);
		temp = SortOrder.ascending.equals(temp) ? SortOrder.descending : SortOrder.ascending;
		sortBy(campo, temp);
	}


	public void sortBy(String campo, SortOrder order) {
		unSort();
		orders.put(campo, order);
	}


	public String orderArrow(String campo){
		SortOrder sortOrder = orders.get(campo);
		String arrow;
		if (SortOrder.descending.equals(sortOrder))
			arrow = "↓";
		else if (SortOrder.ascending.equals(sortOrder))
			arrow = "↑";
		else
			arrow = "";
		return arrow;
	}

	private void unSort(){
		for (String k : orders.keySet()){
			orders.put(k, SortOrder.unsorted);
		}
	}


	public HashMap<String, String> getFilters() {
		return filters;
	}
	
	public HashMap<String, String> getFiltersLe() {
		return filtersLe;
	}


	public HashMap<String, String> getFiltersGe() {
		return filtersGe;
	}


	public String getCampo() {
		return campo;
	}


	public void setCampo(String campo) {
		this.campo = campo;
	}

}
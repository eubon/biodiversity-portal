package com.ibm.gbs.tramitator.jsf.mbean.crud;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.ibm.gbs.tramitator.ejb.crud.ServicioCRUD;
import com.ibm.gbs.tramitator.jpa.entity.Configuracion;

@ManagedBean(name = "ConfiguracionCRUDBean")
@SessionScoped
public class ConfiguracionCRUDBean {
	
	private List<Configuracion> lista; 
	private Configuracion objeto;

	@EJB
	ServicioCRUD crud;
	
	public String listado(){
		lista = crud.listado(Configuracion.class);
		return "CRUDlistadoConfiguracions";
	}

	public String modificar(){
		return "CRUDmodificarConfiguracion";
	}
	
	public String ver(){
		return "CRUDconsultaConfiguracion";
	}
	
	public String consulta(){
		String idStr = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
		int id = Integer.parseInt(idStr);
		
		objeto = (Configuracion) crud.obtenerObjeto(Configuracion.class, id);
		return "CRUDconsultaConfiguracion";
	}
	
	public String guardar(){
		crud.guardar(objeto);
		return "CRUDconsultaConfiguracion";
	}

	public List<Configuracion> getLista() {
		return lista;
	}

	public Configuracion getObjeto() {
		return objeto;
	}

	public void setObjeto(Configuracion objeto) {
		this.objeto = objeto;
	}
	
}

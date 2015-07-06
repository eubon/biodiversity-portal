package com.ibm.gbs.tramitator.jsf.mbean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import com.ibm.gbs.tramitator.ejb.ServicioConfiguracion;
import com.ibm.gbs.tramitator.jpa.entity.Configuracion;
import com.ibm.gbs.tramitator.jsf.mbean.propiedades.UtilPropiedades;
import com.ibm.gbs.tramitator.util.cache.Cache;

@ManagedBean(name = "adminBean") 
@SessionScoped
public class AdminBean implements Serializable{
	@EJB
	ServicioConfiguracion srvConfig;
	
	Logger log = Logger.getLogger(AdminBean.class);
	
	private List<Configuracion> configuracion;
	private String nuevaPropiedadClave;
	private String nuevaPropiedadDescripcion;
	
	public List<Configuracion> getConfiguracion() {
		return configuracion;
	}

	public void setConfiguracion(List<Configuracion> configuracion) {
		this.configuracion = configuracion;
	}

	public String variablesConfiguracion(){
		try{
			configuracion = srvConfig.leer();
			/*
			if (configuracion.size()>0)
			{
				Configuracion conf0 = configuracion.get(0);
				if (conf0.getClave().equals(Constantes.CFG_MODIFICACION))
				{
					configuracion.remove(0);
				}
			}
			*/
		}catch(Exception e){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error en la lectura de la configuración", null));
		}
		return "variablesConfiguracion";
	}

	public void establecerParametros(){
		try{
			/*
			Configuracion modif = new Configuracion();
			modif.setIdConfiguracion(0L);
			modif.setClave(Constantes.CFG_MODIFICACION);
			modif.setDescripcion(Constantes.CFG_MODIFICACION);
			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			modif.setValor(df.format(new Date()));
			configuracion.add(modif);
			*/
			srvConfig.actualizar(configuracion);
			//configuracion.remove(configuracion.size()-1);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Actualización realizada correctamente", null));
		}catch(Exception e){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de escritura de la configuración", null));
		}
	}
	
	public void crearNuevaPropiedad() {
		
		List<Configuracion> nuevaLista = new ArrayList<Configuracion>(1);
		Configuracion config = new Configuracion();
		config.setClave(nuevaPropiedadClave);
		config.setDescripcion(nuevaPropiedadDescripcion);
		nuevaLista.add(config);
			
		try{
			srvConfig.actualizar(nuevaLista);
		}catch(Exception e){
			log.warn("Error al escribir la nueva propiedad " + e);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de escritura de la configuración", null));
		}
		
		variablesConfiguracion();
	}
	
	int propiedadABorrar;
	
	public int getPropiedadABorrar() {
		return propiedadABorrar;
	}

	public void setPropiedadABorrar(int propiedadABorrar) {
		this.propiedadABorrar = propiedadABorrar;
	}

	public void seleccionarBorrarPropiedad() {
//		String row = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("row");
//		propiedadABorrar = Integer.parseInt(row);
	}

	public void borrarPropiedad() {
		try{
			Configuracion eliminada = configuracion.remove(propiedadABorrar);
			srvConfig.eliminar(eliminada.getIdConfiguracion());
		}catch(Exception e){
			log.warn("Error al borrar la propiedad " + e);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al borrar la propiedad de configuración", null));
		}
	}
	
	public String getNuevaPropiedadClave() {
		return nuevaPropiedadClave;
	}

	public void setNuevaPropiedadClave(String nuevaPropiedadClave) {
		this.nuevaPropiedadClave = nuevaPropiedadClave;
	}

	public String getNuevaPropiedadDescripcion() {
		return nuevaPropiedadDescripcion;
	}

	public void setNuevaPropiedadDescripcion(String nuevaPropiedadDescripcion) {
		this.nuevaPropiedadDescripcion = nuevaPropiedadDescripcion;
	}
	
	
	public String panelControl(){
		return "panelControl";
	}

	public String home(){
		return "home";
	}

	public void limpiarCache(){
		Cache.getInstancia().clear();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Caché eliminada correctamente.", null));
	}
	
	public void recargarPropiedades(){
		try {
			UtilPropiedades.initProps(srvConfig);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Propiedades cargadas correctamente.", null));
		} catch (NamingException e) {
			log.error("AdminBean.recargarPropiedades() - Error al cargar las propiedades " + e);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al cargar las propiedades.", null));
		} catch (IOException e) {
			log.error("AdminBean.recargarPropiedades() - Error al cargar las propiedades " + e);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al cargar las propiedades.", null));
		}
	}
	

}

package com.ibm.gbs.tramitator.jpa.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "CONFIGURACION")
@SequenceGenerator(name="sq_configuracion",sequenceName="SEQ_CONFIGURACION",allocationSize=1,initialValue=1)
public class Configuracion implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="sq_configuracion")
	@Column(name = "ID" )
	private Long idConfiguracion;
	
	public Long getIdConfiguracion() {
		return idConfiguracion;
	}

	public void setIdConfiguracion(Long idConfiguracion) {
		this.idConfiguracion = idConfiguracion;
	}

	@Column(name = "CLAVE")
	private String clave;

	@Column(name = "DESCRIPCION")
	private String descripcion;
	
	@Column(name = "VALOR")
	private String valor;

	
	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}
	
	public static Map<String,String> listConfiguracionAMap(List<Configuracion> list){
		HashMap<String, String> hmCfg = new HashMap<String, String>(); 
		for (Configuracion prop : list){
			hmCfg.put(prop.getClave(), prop.getValor());
		}
		return hmCfg;
	}
}

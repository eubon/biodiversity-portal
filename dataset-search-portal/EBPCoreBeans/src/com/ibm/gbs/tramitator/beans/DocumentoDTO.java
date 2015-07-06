package com.ibm.gbs.tramitator.beans;


public class DocumentoDTO {
	private String nombreFichero;
	private byte[] datos;
	
	public String getNombreFichero() {
		return nombreFichero;
	}
	public void setNombreFichero(String nombreFichero) {
		this.nombreFichero = nombreFichero;
	}
	public byte[] getDatos() {
		return datos;
	}
	public void setDatos(byte[] datos) {
		this.datos = datos;
	}
	
}

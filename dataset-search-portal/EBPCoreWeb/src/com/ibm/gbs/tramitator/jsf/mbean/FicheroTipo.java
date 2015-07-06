package com.ibm.gbs.tramitator.jsf.mbean;

import org.richfaces.model.UploadedFile;

public class FicheroTipo {
	
	private UploadedFile fichero;
	private int tipo;
	
	public UploadedFile getFichero() {
		return fichero;
	}
	public void setFichero(UploadedFile fichero) {
		this.fichero = fichero;
	}
	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
}

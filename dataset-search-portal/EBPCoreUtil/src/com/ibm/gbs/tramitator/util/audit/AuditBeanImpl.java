
package com.ibm.gbs.tramitator.util.audit;


public class AuditBeanImpl implements AuditBean {
	
	private String idCanal = null;
	private String idApplicacion = null;
	private String idOperacion = null;
	private String idUsuario = null;
	private String ipUsuario = null;
	private String idSesion = null;
	private String txtEntrada = null;
	private String idServidor = null;
	private String txtRespuesta = null;
	private long duracion = 0;	

	
	public String getIdCanal() {
		return idCanal;
	}

	public void setIdCanal(String idCanal) {
		this.idCanal = idCanal;
	}

	public String getIdApplicacion() {
		return idApplicacion;
	}

	public void setIdApplicacion(String idApplicacion) {
		this.idApplicacion = idApplicacion;
	}

	public String getIdOperacion() {
		return idOperacion;
	}

	public void setIdOperacion(String idOperacion) {
		this.idOperacion = idOperacion;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getIpUsuario() {
		return ipUsuario;
	}

	public void setIpUsuario(String ipUsuario) {
		this.ipUsuario = ipUsuario;
	}

	public String getIdSesion() {
		return idSesion;
	}

	public void setIdSesion(String idSesion) {
		this.idSesion = idSesion;
	}

	public String getTxtEntrada() {
		return txtEntrada;
	}

	public void setTxtEntrada(String txtEntrada) {
		this.txtEntrada = txtEntrada;
	}

	public String getIdServidor() {
		return idServidor;
	}

	public void setIdServidor(String idServidor) {
		this.idServidor = idServidor;
	}

	public String getTxtRespuesta() {
		return txtRespuesta;
	}

	public void setTxtRespuesta(String txtRespuesta) {
		this.txtRespuesta = txtRespuesta;
	}

	public long getDuracion() {
		return duracion;
	}

	public void setDuracion(long duracion) {
		this.duracion = duracion;
	}

	public String registro()
	{
		StringBuilder sb = new StringBuilder(200);
		sb.append("idApplicacion=");
		sb.append(idApplicacion);
		sb.append(";idOperacion=");
		sb.append(idOperacion);
		sb.append(";idUsuario=");
		sb.append(idUsuario);
		sb.append(";ipUsuario=");
		sb.append(ipUsuario);
		sb.append(";idSesion=");
		sb.append(idSesion);
		sb.append(";idServidor=");
		sb.append(idServidor);
		sb.append(";idCanal=");
		sb.append(idCanal);
		sb.append(";txtEntrada=");
		sb.append(txtEntrada);
		sb.append(";txtRespuesta=");
		sb.append(txtRespuesta);
		sb.append(";duracion=");
		sb.append(duracion);
				
		return sb.toString();
	}

	

}

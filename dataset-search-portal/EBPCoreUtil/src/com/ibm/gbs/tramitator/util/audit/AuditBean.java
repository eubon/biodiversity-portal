package com.ibm.gbs.tramitator.util.audit;

public interface AuditBean {

	public static final String Web = "WEB";
	public static final String WS = "WS";

	public abstract String getIdCanal();

	public abstract void setIdCanal(String idCanal);

	public abstract String getIdApplicacion();

	public abstract void setIdApplicacion(String idApplicacion);

	public abstract String getIdOperacion();

	public abstract void setIdOperacion(String idOperacion);

	public abstract String getIdUsuario();

	public abstract void setIdUsuario(String idUsuario);

	public abstract String getIpUsuario();

	public abstract void setIpUsuario(String ipUsuario);

	public abstract String getIdSesion();

	public abstract void setIdSesion(String idSesion);

	public abstract String getTxtEntrada();

	public abstract void setTxtEntrada(String txtEntrada);

	public abstract String getIdServidor();

	public abstract void setIdServidor(String idServidor);

	public abstract String getTxtRespuesta();

	public abstract void setTxtRespuesta(String txtRespuesta);

	public abstract long getDuracion();

	public abstract void setDuracion(long duracion);
	
	public abstract String registro();

}
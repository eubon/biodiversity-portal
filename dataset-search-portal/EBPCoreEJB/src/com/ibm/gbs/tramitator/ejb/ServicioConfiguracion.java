package com.ibm.gbs.tramitator.ejb;


import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import com.ibm.gbs.tramitator.jpa.entity.Configuracion;


@Local
public interface ServicioConfiguracion {
	List<Configuracion> leer();
	void actualizar(Collection<Configuracion> configuracion);
	void eliminar(Long idConfiguracion);
	Date obtenerFechaModificacion();
}

package com.ibm.gbs.tramitator.ejb;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import com.ibm.gbs.tramitator.dao.UtilDAO;
import com.ibm.gbs.tramitator.jpa.entity.Configuracion;

@Stateless
public class ServicioConfiguracionImpl implements ServicioConfiguracion{
	
	private static final String FORMATO_FECHA_MODIFICACION = "dd/MM/yyyy HH:mm:ss";

	Logger log = Logger.getLogger(ServicioConfiguracionImpl.class);

	@EJB
	UtilDAO dao;
	
	@Override
	public void actualizar(Collection<Configuracion> configuracion) {
		dao.updateCollection(configuracion);
		actualizarFechaModificacion();
	}

	public Date obtenerFechaModificacion() {
		Configuracion tiempo = dao.find(Configuracion.class, 0L);
		String valor = tiempo.getValor();
	
		Date fecha = null;
		if (valor!=null && !valor.equals("")){
			SimpleDateFormat df = new SimpleDateFormat(FORMATO_FECHA_MODIFICACION);
			try {
				fecha = df.parse(valor);
			} catch (ParseException e) {
				log.warn("EJBPropertiesUpdater.execute() Fecha incorrecta en bbdd: " + valor);
			}
		}
		return fecha;
	}
	
	private void actualizarFechaModificacion() {
		Configuracion modificacion = dao.find(Configuracion.class, new Long(0L));
		if (modificacion!=null){
			SimpleDateFormat df = new SimpleDateFormat(FORMATO_FECHA_MODIFICACION);
			modificacion.setValor(df.format(new Date()));
			dao.update(modificacion);
		}
	}

	@Override
	public void eliminar(Long idConfiguracion) {
		dao.remove(Configuracion.class, idConfiguracion);
		actualizarFechaModificacion();		
	}

	@Override
	public List<Configuracion> leer() {
		List<Configuracion> listado = dao.findAll(Configuracion.class, "idConfiguracion", true);
		for (int i=0;i<listado.size();i++){
			if (listado.get(i).getClave().equals("modificacion")){
				listado.remove(i);
				break;
			}
		}
		return listado;
	}

}

package com.ibm.gbs.tramitator.ejb.timers;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.apache.log4j.Logger;

import com.ibm.gbs.tramitator.ejb.ServicioConfiguracion;
import com.ibm.gbs.tramitator.jpa.entity.Configuracion;
import com.ibm.gbs.tramitator.util.Constantes;
import com.ibm.gbs.tramitator.util.configuracion.Propiedades;

@Stateless
public class TimerPropertiesUpdaterImpl implements TimerPropertiesUpdater {
	
	@EJB
	ServicioConfiguracion srvConfig;
	
	//Inyeccion del TimerService
	@Resource TimerService timerService;

	Logger log = Logger.getLogger(TimerPropertiesUpdaterImpl.class);
	
	/**
	 * 
	 */
	public void shutDownTimer() {
		
		log.debug("EJBPropertiesUpdater.shutDownTimer():inicio()");
		
		Collection<Timer> timers = timerService.getTimers();   
        log.debug("EJBPropertiesUpdater.shutDownTimer - existing timers? " + timers+"-");   
        if (timers != null)   
        {   
            for (Iterator iterator = timers.iterator(); iterator.hasNext();) {   
                Timer t = (Timer) iterator.next();   
                t.cancel();   
                log.debug("EJBPropertiesUpdater.shutDownTimer - timer \""+t+"\" canceled.");   
            }   
        }   
        log.debug("EJBPropertiesUpdater.shutDownTimer():final()");
		
	}
	
	/**
	 * 
	 */
	public void startUpTimer() {
		log.debug("EJBPropertiesUpdater.startUpTimer():inicio()");
		
		Collection<Timer> timers = timerService.getTimers();   
		log.debug("EJBPropertiesUpdater.startUpTimer():- existing timers? " + timers+"-");   
        if (timers != null)   
        {   
            for (Iterator<Timer> iterator = timers.iterator(); iterator.hasNext();) {   
                Timer t = (Timer) iterator.next();   
                t.cancel();
                log.debug("EJBPropertiesUpdater.startUpTimer():timer ="+t+" cancelado-");
            }   
        }
		
		intervalDuration = 10 * 60 * 1000;
		try {
	        String inter = Propiedades.getPropiedad(Constantes.CFG_INTERVALO_ACTUALIZACION_PROPIEDADES);
	        intervalDuration = Long.parseLong(inter);
		} catch (Exception e){
			log.warn("EJBPropertiesUpdater.startUpTimer():- intervalo actualización incorrecto, usando " + intervalDuration + " ms");   
		}
		
		 timerService.createTimer(intervalDuration,intervalDuration,"EJBPropertiesUpdater");
		log.debug("EJBPropertiesUpdater.startUpTimer():fin()");
	}
	
	private static Date ultimaActualizacion;
	private static long intervalDuration;
	
	@Timeout
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public void execute(Timer timer)
	{
		log.trace("EJBPropertiesUpdater.execute() : inicio");
		
		Date fecha = srvConfig.obtenerFechaModificacion();
		
		if (ultimaActualizacion==null || fecha==null || ultimaActualizacion.before(fecha)){
			List<Configuracion> cfg = srvConfig.leer();
			Map<String, String> mapCfg = Configuracion.listConfiguracionAMap(cfg);
			Propiedades.inicializa(mapCfg);
			ultimaActualizacion = fecha;
			
			//Comprobar el intervalo actualización, si ha cambiado destruir el timer y crear de nuevo
	        String inter = Propiedades.getPropiedad(Constantes.CFG_INTERVALO_ACTUALIZACION_PROPIEDADES);
	        long newIntervalDuration;
	        try {
	        	newIntervalDuration = Long.parseLong(inter);
			} catch (NumberFormatException e) {
				log.warn("EJBPropertiesUpdater.execute() Intervalo de actualización incorrecto: " + inter + " usando " + intervalDuration);
				newIntervalDuration = intervalDuration;
			}	        
	        if (newIntervalDuration!=intervalDuration){

	        	Collection<Timer> timers = timerService.getTimers();   
	            if (timers != null)   
	            {   
	                for (Iterator<Timer> iterator = timers.iterator(); iterator.hasNext();) {   
	                    Timer t = (Timer) iterator.next();   
	                    t.cancel();
	                }   
	            }
	            intervalDuration = newIntervalDuration;
	            timerService.createTimer(intervalDuration,intervalDuration,"EJBPropertiesUpdater");
	        }
		}
		log.trace("EJBPropertiesUpdater.execute:fin() " );
	}

}

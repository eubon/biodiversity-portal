package com.ibm.gbs.tramitator.util.mail;

import org.apache.log4j.Logger;

import com.ibm.gbs.tramitator.util.Constantes;
import com.ibm.gbs.tramitator.util.configuracion.Propiedades;

public class EnvioMail {

	private static Logger log = Logger.getLogger(EnvioMail.class.getName());
	
	public static void enviarMail(String asunto, String cuerpo, String destino)
	{
		log.debug("EnvioMail.enviarMail():inicio-");
		
		if (Constantes.si.equalsIgnoreCase(Propiedades.getPropiedad(Constantes.CFG_MAIL_ACTIVO)))
		{
			JmsProducer.sendMessage(new String[]{cuerpo, asunto, destino}, "ConnectionFactory", "/queue/mailQueue");
			log.debug("EnvioMail.enviarMail():encolado Mail: asunto=" + asunto + "- destino=" + destino + "-");
		}
		log.debug("EnvioMail.enviarMail():fin-");
	}
}

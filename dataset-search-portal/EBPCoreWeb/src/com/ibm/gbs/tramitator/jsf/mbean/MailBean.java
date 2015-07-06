package com.ibm.gbs.tramitator.jsf.mbean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import com.ibm.gbs.tramitator.util.Constantes;
import com.ibm.gbs.tramitator.util.configuracion.Propiedades;
import com.ibm.gbs.tramitator.util.mail.EnvioMail;


@ManagedBean(name = "mailBean")
@SessionScoped
public class MailBean implements Serializable{
	
	private static Logger log = Logger.getLogger(MailBean.class.getName());
	
	public String envio()
	{
		log.debug("MailBean.envio():inicio-");
		String mensaje = "Email encolado correctamente.";
		if (Constantes.si.equalsIgnoreCase(Propiedades.getPropiedad(Constantes.CFG_MAIL_ACTIVO)))
		{
		   EnvioMail.enviarMail("BlueGov Test", "Mensaje de prueba", Propiedades.getPropiedad("destinoMail"));
		   mensaje = "Email encolado correctamente.";
		}
		else
		{
			mensaje = "Configuración de envío de mail no activo.";
		}

		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, mensaje, null));
		log.debug("MailBean.envio():fin-");
		return "mensaje";
	}
 
}

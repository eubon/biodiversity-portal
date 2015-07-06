package com.ibm.gbs.tramitator.mdb;

import java.io.Serializable;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.apache.log4j.Logger;

import com.ibm.gbs.tramitator.util.configuracion.Propiedades;
import com.ibm.gbs.tramitator.util.mail.Mail;

@MessageDriven(name="MailMDB", activationConfig = {
	    @ActivationConfigProperty(propertyName="destinationType", propertyValue="javax.jms.Queue"),
	    @ActivationConfigProperty(propertyName="destination", propertyValue="/queue/mailQueue")

	})
public class MailMDB implements MessageListener {

	private static Logger log = Logger.getLogger(MailMDB.class.getName());

	@Override
	public void onMessage(Message arg0) 
	{
		log.debug("MailMDB.onMessage():inicio-");
		try {
			Serializable object = ((ObjectMessage) arg0).getObject();
			String[] lista = (String[]) object;
			Mail.enviar(lista[2], lista[0], lista[1]);
		}
		/*catch (AddressException e) {
			log.error("MailMDB.onMessage() : Error al enviar el mail : " + e);
		} catch (MessagingException e) {
			log.error("MailMDB.onMessage() : Error al enviar el mail : " + e);
		} catch (JMSException e) {
			log.error("MailMDB.onMessage() : Error al enviar el mail : " + e);
			
		} 
		*/
		catch (Exception e) {
			log.error("MailMDB.onMessage() : Error al enviar el mail : " + e);
		}
		log.debug("MailMDB.onMessage():fin-");
	}

}

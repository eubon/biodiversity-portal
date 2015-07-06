package com.ibm.gbs.tramitator.util.mail;

import java.util.Properties;
/*
import ∫.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
*/
//import org.apache.log4j.Logger;

import com.ibm.gbs.tramitator.util.configuracion.Propiedades;

public class Mail {
	
	//private static Logger log = Logger.getLogger(Mail.class.getName());

	//public static void enviar (String direccion, String contenidoMail, String asunto) throws AddressException, MessagingException {
		
		public static void enviar (String direccion, String contenidoMail, String asunto)  {
/*
		log.debug("Mail.enviar():direccion="+direccion+"-asunto="+asunto+"-");

		Properties props = new Properties();
		// Nombre del host de correo, es smtp.gmail.com
		//props.setProperty("mail.smtp.host", "smtp.gmail.com");
		props.setProperty("mail.smtp.host", Propiedades.getPropiedad("Host"));
		// TLS si est√° disponible
		//props.setProperty("mail.smtp.starttls.enable", "true");
		props.setProperty("mail.smtp.starttls.enable",Propiedades.getPropiedad("TLS"));
		// Puerto de gmail para envio de correos
		//props.setProperty("mail.smtp.port","587");
		props.setProperty("mail.smtp.port",Propiedades.getPropiedad("Puerto"));
		// Nombre del usuario
		//props.setProperty("mail.smtp.user", "sistemaCES@gmail.com");
		props.setProperty("mail.smtp.user", Propiedades.getPropiedad("Usuario"));
		// Si requiere o no usuario y password para conectarse.
		//props.setProperty("mail.smtp.auth", "true");
		props.setProperty("mail.smtp.auth", Propiedades.getPropiedad("Autentificacion"));

		log.debug("Mail.enviar():direccion= props:"+props+"-");
		//Obtenemos la instacia de la sesion
		Session session = Session.getInstance(props);
		session.setDebug(true);

		//Ahora construimos el mensaje a enviar
		MimeMessage message = new MimeMessage(session);		

		
		String address= Propiedades.getPropiedad("Usuario") + "@" + Propiedades.getPropiedad("Direccion");
		log.debug("Mail.enviar():direccion= address:"+address+"-");
		// Quien envia el correo
		message.setFrom(new InternetAddress(address));

		// A quien va dirigido
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(direccion));

		//Construimos el mensaje
		message.setSubject(asunto);
		message.setText(contenidoMail);

		//Enviamos el mensaje
		Transport t = session.getTransport("smtp");

		//Establecemos la conexion
		t.connect(Propiedades.getPropiedad("Usuario"),Propiedades.getPropiedad("Password"));

		//Mandamos el mensaje
		t.sendMessage(message,message.getAllRecipients());

		//Cerramos la conexion
		t.close();

		log.debug("Mail.enviar() fin");
		*/
	}
	
}

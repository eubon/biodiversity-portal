package com.ibm.gbs.tramitator.util.mail;

import java.io.Serializable;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.apache.log4j.Logger;

public class JmsProducer {

	private static Logger log = Logger.getLogger(JmsProducer.class.getName());
	
    private JmsProducer() {}
    public static void sendMessage(Serializable payload, String connectionFactoryJndiName, 
        String destinationJndiName)
    {
        try {
            ConnectionFactory connectionFactory = null;
            Connection connection = null;
            Session session = null;
            Destination destination = null;
            MessageProducer messageProducer = null;
            ObjectMessage message = null;
            connectionFactory = ServiceLocator.getJmsConnectionFactory(connectionFactoryJndiName);

            connection = connectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            destination = ServiceLocator.getJmsDestination(destinationJndiName);
            messageProducer = session.createProducer(destination);
            message = session.createObjectMessage(payload);
            messageProducer.send(message);
            log.debug("JmsProducer.sendMessage() : Mensaje enviado");
            messageProducer.close();
            session.close();
            connection.close();
        } catch (JMSException je) {
            throw new RuntimeException(je);
        }
    }
}

package com.ibm.gbs.tramitator.util.mail;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ServiceLocator {
	   private ServiceLocator() {}
	     public static ConnectionFactory getJmsConnectionFactory(String jmsConnectionFactoryJndiName)
	             {
	        ConnectionFactory jmsConnectionFactory = null;
	        try {
	            Context ctx = new InitialContext();
	            jmsConnectionFactory = (ConnectionFactory) ctx.lookup(jmsConnectionFactoryJndiName);
	        } catch (ClassCastException cce) {
	            throw new RuntimeException(cce);
	        } catch (NamingException ne) {
	            throw new RuntimeException(ne);
	        }
	        return jmsConnectionFactory;
	    }
	    public static Destination getJmsDestination(String jmsDestinationJndiName) {
	        Destination jmsDestination = null;
	        try {
	            Context ctx = new InitialContext();
	            jmsDestination = (Destination) ctx.lookup(jmsDestinationJndiName);
	        } catch (ClassCastException cce) {
	            throw new RuntimeException(cce);
	        } catch (NamingException ne) {
	            throw new RuntimeException(ne);
	        }
	        return jmsDestination;
	    }
	}

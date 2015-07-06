package com.ibm.gbs.tramitator.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.FactoryConfigurationError;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import com.ibm.gbs.tramitator.ejb.ServicioConfiguracion;
import com.ibm.gbs.tramitator.ejb.timers.TimerPropertiesUpdater;
import com.ibm.gbs.tramitator.jsf.mbean.propiedades.UtilPropiedades;
import com.ibm.gbs.tramitator.util.Constantes;
import com.ibm.gbs.tramitator.util.cache.Cache;

/**
 * Servlet implementation class InitServlet
 */
@WebServlet(value="/InitServlet",loadOnStartup=1)
public class InitServlet extends HttpServlet {
	@EJB
	ServicioConfiguracion srvConfig;
	
	@EJB
	TimerPropertiesUpdater propertiesUpdaterTimer;
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InitServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	@Override
	public void init() throws ServletException {

		initLog();
		Logger logger = Logger.getLogger("com.ibm.gbs");
		
		logger.info("InitServlet.init() - Log  EBP configurado");
		
		if (logger.isDebugEnabled())
		{
		   logger.info("InitServlet.init() - Log  debug enabled");

		   logger.debug("InitServlet.init() - Log  debug ");
		}

		if (logger.isInfoEnabled())
		{
		   logger.info("InitServlet.init() - Log  info enabled");
		}
		


		initProps();
		logger.info("InitServlet.init() - Propiedades cargadas");
		
		propertiesUpdaterTimer.startUpTimer();
		logger.info("InitServlet.init() - Actualizador propiedades inicializado");
		
		initCache();
		logger.info("InitServlet.init() - Caché configurado");
		
		logger.info("InitServlet.init() - App EBP Iniciada---------------------------------------------------------");
		logger.info("InitServlet.init() - Applicación=" + Constantes.appName +"-");
		logger.info("InitServlet.init() - Versión=" + Constantes.version +"-");
		
		logger.info("InitServlet.init() - -------------------------------------------------------------------------");
		
		super.init();
	}

	private void initCache() {
		final int timeToLive = 1800000; 
		final int cacheAccessTimeout = 1800000; 
		final int maxEntries = 100; 
		final int timerInterval = 300000;
		Cache.init(timeToLive, cacheAccessTimeout, maxEntries, timerInterval);
	}

	private void initProps() throws ServletException{
		try {
			UtilPropiedades.initProps(srvConfig);
		} catch (NamingException e) {
			throw new ServletException(e);
		} catch (IOException e) {
			throw new ServletException(e);
		}
	}

	private void initLog() throws ServletException, FactoryConfigurationError {
		String rutaLog;
		try {
			rutaLog = (String) InitialContext.doLookup("java:comp/env/logConfigPath");
			System.out.println("InitServlet.initLog():rutaLog=" + rutaLog+"-");
		} catch (NamingException e) {
			throw new ServletException(e);
		}
		//rutaLog = "C:/usr/local/ebp/log4jExample.xml";
		DOMConfigurator.configureAndWatch(rutaLog);
	}
}

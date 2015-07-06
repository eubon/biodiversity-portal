package com.ibm.gbs.tramitator.util.audit;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;

import org.apache.log4j.Logger;



public class Audit {
	
   static Logger log = Logger.getLogger(Audit.class);
	
	private static String ID_APLICACION = "";
	private static String ID_SERVIDOR = "";

	public boolean auditar(AuditBean audit)
	{
		boolean res = false;
		
		log.info("RegistroAudit:"+audit.registro());
		
		res = true;
		return res;		
	}
	
	public AuditBean getAuditBean()
	{
		AuditBean auditBean = new AuditBeanImpl();
		
		auditBean.setIdApplicacion(ID_APLICACION);
		auditBean.setIdServidor(ID_SERVIDOR);
		return auditBean;
	}
	
	public void initAudit(String idAplicacion, String idServidor)
	{
		ID_APLICACION = idAplicacion;
		ID_SERVIDOR = idServidor;
	}
}

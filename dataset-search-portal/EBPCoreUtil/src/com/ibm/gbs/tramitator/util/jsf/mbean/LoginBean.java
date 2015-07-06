package com.ibm.gbs.tramitator.util.jsf.mbean;

import java.io.IOException;
import java.io.Serializable;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.jboss.security.SecurityContext;
import org.jboss.security.SecurityContextAssociation;
import org.jboss.security.SimplePrincipal;

import sun.security.x509.X500Name;

import com.ibm.gbs.tramitator.util.Constantes;
import com.ibm.gbs.tramitator.util.audit.Audit;
import com.ibm.gbs.tramitator.util.audit.AuditBean;
import com.ibm.gbs.tramitator.util.configuracion.Propiedades;
import com.ibm.gbs.tramitator.util.jsf.JSFUtil;
import com.ibm.gbs.tramitator.util.rendimiento.LogTime;

@ManagedBean(name = "loginBean") 
@SessionScoped
public class LoginBean implements Serializable{
	
	private String loginInfo;
	private String ejbLoginInfo;
	private Logger log = Logger.getLogger(LoginBean.class);
	
	private String userIp;
	
	public String getEjbLoginInfo() {
		return ejbLoginInfo;
	}

	public String getLoginInfo() {
		return loginInfo;
	}
	
	public String getUserIp()
	{
		if (userIp == null)
		{
			HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();  
			userIp = httpServletRequest.getRemoteAddr();  		
		}
		return userIp;
	}
//	public X509Certificate getUser(){
//		X509Certificate cert = null;
//		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
//		X509Certificate[] certs = (X509Certificate[]) request.getAttribute("javax.servlet.request.X509Certificate");
//		if (certs!=null && certs.length>0)
//			cert = certs[0];
//		return cert;
//	}

	private Principal user;
	public Principal getUser()
	{
				
		if (user==null)
		{
			log.debug("LoginBean.getUser() - inicio recuperando el usuario-");
			LogTime logTime = new LogTime();

			SecurityContext securityContext = SecurityContextAssociation.getSecurityContext();
			if (securityContext.getUtil().getSubject()!=null && securityContext.getUtil().getSubject().getPrincipals()!=null){
				Iterator<Principal> iterator = securityContext.getUtil().getSubject().getPrincipals().iterator();
				while (iterator.hasNext() && user==null)
				{
					Principal p = iterator.next();
					if (p instanceof X500Name || p.getClass().equals(SimplePrincipal.class) && !"guest".equals(p.toString()))
					{
						user = p;
					} 
				}
			}
			
			Audit audit = new Audit();
			
			AuditBean auditBean = audit.getAuditBean();
			auditBean.setIdOperacion(Constantes.AUDIT_LOGIN_BEAN_GETUSER);
			auditBean.setIdCanal(AuditBean.Web);
			auditBean.setIpUsuario(getUserIp());
			
			if (user != null)
			{
			auditBean.setIdUsuario(getNif());
			auditBean.setTxtEntrada("Obteniendo el usuario:nif="+getNif()+"cif="+getCif());
			}
			else
			{
				auditBean.setIdUsuario("null");
				auditBean.setTxtEntrada("usuario: null=");
			}
			
			auditBean.setDuracion(logTime.getInterval());
			audit.auditar(auditBean);
			
			log.debug("LoginBean.getUser() - fin t="+logTime.getInterval()+"-");
		}

		return user;
	}	
	
	public String getNif(){
		String commonName = "";
		try {
			Principal u = getUser();
			if (u !=null){
				if (u instanceof X500Name)
					commonName = ((X500Name) u).getCommonName();
				else 
					commonName = u.toString();
			}
		} catch (IOException e) {
		}
		return commonName;
	}
	
	public String getCif(){
		String organization = "";
		try {
			Principal u = getUser();
			if (u !=null && u instanceof X500Name)
				organization = ((X500Name) u).getOrganization();
		} catch (IOException e) {
		}
		return organization;
	}
	
	public String getNombre(){
		String organization = "";
		try {
			Principal u = getUser();
			if (u !=null && u instanceof X500Name)
				organization = ((X500Name) u).getGivenName();
		} catch (IOException e) {
		}
		return organization;
	}		
	
	public String getApellidos(){
		String organization = "";
		try {
			Principal u = getUser();
			if (u !=null && u instanceof X500Name)
				organization = ((X500Name) u).getSurname();
		} catch (IOException e) {
		}
		return organization;
	}		

	public String informacion(){
		ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
		loginInfo = 
			"UserPrincipal: " + ctx.getUserPrincipal().toString() + "\r\n" + 
			"isUserInRole 'usuario_privilegiado': " + ctx.isUserInRole("usuario_privilegiado") + "\r\n" + 
			"isUserInRole 'administrador': " + ctx.isUserInRole("administrador") + "\r\n" +
			"isUserInRole 'rol_inventado': " + ctx.isUserInRole("rol_inventado") + "\r\n" +
			"sessionId: " + ((HttpSession)ctx.getSession(false)).getId() + "\r\n" 
			;
//		ejbLoginInfo = srvExpedientes.obtenerInfoLogin();
		return "seguridad";
	}
	
	public String logout(){
		ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
		HttpSession session = (HttpSession) ctx.getSession(false);
		session.invalidate();
		return "home";
	}

	public String getUrlInicioSSL() {
		ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
		return "https://" + ctx.getRequestServerName() + ":8443" + ctx.getRequestContextPath();
	}
	
	public String getFechaHora()
	{
		String res = "";		
		
		SimpleDateFormat df = new SimpleDateFormat(Constantes.FORMATO_FECHAHORA);
		res = df.format(new Date());
		
		//log.debug("LoginBean.getFechaHora() - obteniendo fecha hora="+res+"-");
		return res;
	}
	
	public String getMilisegundos()
	{
		String res = Long.toString(System.currentTimeMillis());		
		
		//log.debug("LoginBean.getMilisegundos() - obteniendo milisegundos="+res+"-");
		return res;
	}
	
	public boolean isUsuarioPersonaJuridica() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		boolean userInRole = externalContext.isUserInRole("usuario_operador_sede");
		return userInRole;
	}
	
	public boolean validarUsuarioPersonaJuridica() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		boolean userInRole = externalContext.isUserInRole("usuario_operador_sede");
		if (!userInRole){
			try {
				externalContext.responseSendError(403, "");
			} catch (IOException e) {
				log.error("LoginBean.validarUsuarioPersonaJuridica()", e);
			}
			return false;
		} else {
			return true;
		}
	}
	
	public static LoginBean getSessionLoginBean(){
		return (LoginBean) JSFUtil.getValue("#{loginBean}");
	}
	
	public boolean isFirmaDummy(){ 
		return Propiedades.getPropiedad(Constantes.CFG_AFIRMA_DUMMY).equalsIgnoreCase("si");
	}
}

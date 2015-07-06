/**
 * License Agreement.
 *
 * Rich Faces - Natural Ajax for Java Server Faces (JSF)
 *
 * Copyright (C) 2007 Exadel, Inc.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License version 2.1 as published by the Free Software Foundation.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301  USA
 */



package com.ibm.gbs.tramitator.jsf.mbean;

import java.io.Serializable;
import java.security.Principal;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@ManagedBean(name = "loginBean") 
@SessionScoped
public class LoginBean implements Serializable{
	
//	@EJB
//	private ServicioExpedientes srvExpedientes;

	
	private String loginInfo;
	private String ejbLoginInfo;
	
	public String getEjbLoginInfo() {
		return ejbLoginInfo;
	}

	public String getLoginInfo() {
		return loginInfo;
	}
	
	public String getUser(){
		ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
		Principal userPrincipal = ctx.getUserPrincipal();
		return userPrincipal!=null?userPrincipal.toString():null;
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
		return "homePublic";
	}

	public String getUrlInicioSSL() {
		ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
		return "https://" + ctx.getRequestServerName() + ":8443" + ctx.getRequestContextPath() + "/secure/home.jsf";
	}
}

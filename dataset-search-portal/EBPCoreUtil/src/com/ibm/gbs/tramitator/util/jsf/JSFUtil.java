package com.ibm.gbs.tramitator.util.jsf;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.MethodExpression;
import javax.faces.application.Application;
import javax.faces.context.FacesContext;
import javax.faces.el.MethodBinding;
import javax.faces.model.SelectItem;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import com.ibm.gbs.tramitator.beans.DocumentoDTO;

public class JSFUtil {

	private static Logger log = Logger.getLogger(JSFUtil.class);

	public static MethodExpression createMethodExpression(String expression) {
		Application app = FacesContext.getCurrentInstance().getApplication();
		ExpressionFactory elFactory = app.getExpressionFactory();
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		MethodExpression methodExpression = elFactory.createMethodExpression(elContext, expression, null, new Class[0]);
		return methodExpression;
	}
	
	public static MethodBinding createMethodBinding(String expression) {
		Application app = FacesContext.getCurrentInstance().getApplication();
		MethodBinding mb = app.createMethodBinding( expression, new Class[0]);
		return mb;
	}
	 
	
	public static Object executeMethodExpression(String expression) {
		MethodExpression exp = createMethodExpression(expression);
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		Object ret = exp.invoke(elContext, null);
		return ret;
	}
	
	public static Object getValue(String elExpression) {
		FacesContext fc = FacesContext.getCurrentInstance();
		Object obj = fc.getApplication().evaluateExpressionGet(fc, elExpression, Object.class);
		return obj;
	}	
	
	public static void descargar(DocumentoDTO doc) {
		if (doc != null){
			FacesContext fc = FacesContext.getCurrentInstance();
			HttpServletResponse response = (HttpServletResponse) fc.getExternalContext().getResponse();
			ServletOutputStream outputStream = null;
			try {
				outputStream = response.getOutputStream();
				response.reset();
				response.setHeader("Content-disposition", "attachment; filename=\"" + doc.getNombreFichero() + "\"");
				response.setContentType("application/x-download");
				outputStream.write(doc.getDatos());
				
				fc.responseComplete();
				
				
				
			} catch (IOException e) {
				log.error("JSFUtil.descargar() : se ha producido un error " + e,e);
			} finally {
				if (outputStream!=null)
					try {
						outputStream.close();
					} catch (IOException e) {
						log.error("JSFUtil.descargarEscritura() : se ha producido un error " + e,e);
					}
			}
		}
	}

	public static void descargar(byte[] bs) {
		descargar(bs, true, "fichero.dat", "application/x-download");
	}
	
	public static void descargar(byte[] bs, String nombreFichero, String extensionFichero) {
		descargar(bs, true, nombreFichero+"."+extensionFichero, "application/x-download");
	}
	
	public static void descargar(byte[] bs, boolean attachment, String filename, String contentType) {
		if (bs != null){
			FacesContext fc = FacesContext.getCurrentInstance();
			HttpServletResponse response = (HttpServletResponse) fc.getExternalContext().getResponse();
			ServletOutputStream outputStream = null;
			try {
				outputStream = response.getOutputStream();
				response.reset();
				if (attachment)
					response.setHeader("Content-disposition", "attachment; filename=\""+filename+"\"");
				response.setContentType(contentType);
				outputStream.write(bs);
				fc.responseComplete();
			} catch (IOException e) {
				log.error("ExpedienteBean.descargar() : se ha producido un error " + e,e);
			} finally {
				if (outputStream!=null)
					try {
						outputStream.close();
					} catch (IOException e) {
						log.error("ExpedienteBean.descargar() : se ha producido un error " + e,e);
					}
			}
		} else {
			log.error("ExpedienteBean.descargar() : el documento es null");
		}
	}
	
	
	public static <T> List<SelectItem> selectItemListfromBeanList(List<T> beanList, String idField, String desField, String allLabel) {
		ArrayList<SelectItem> selectItemList = new ArrayList<SelectItem>();
		if (allLabel!=null){
			SelectItem allItem = new SelectItem(null, allLabel);
			selectItemList.add(allItem);
		}
		try {
			for (T bean : beanList){
				String id;
				id = BeanUtils.getProperty(bean, idField);

				String des = BeanUtils.getProperty(bean, desField);
				selectItemList.add(new SelectItem(id, des));
			}
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			throw new RuntimeException(e);
		} catch (NoSuchMethodException e) {
			throw new RuntimeException(e);
		}
		return selectItemList;
	}
}

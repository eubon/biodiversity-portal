package com.ibm.gbs.tramitator.jsf.mbean;

import java.io.Serializable;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.MethodExpression;
import javax.faces.application.Application;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.FacesEvent;

import org.richfaces.event.FileUploadEvent;

@ManagedBean(name = "fileUploadListener") 
@SessionScoped
public class FileUploadListener implements Serializable {
	
	private String listener;
	
	private FileUploadEvent tmpEvent;
	
	public void setListener(String listener) {
		tmpEvent = null;
		this.listener = listener;
	}
	

	public void listener(FileUploadEvent event) throws Exception {
		tmpEvent = event;
	}
	
	public void asignarFichero(){
		if (tmpEvent!=null){
			MethodExpression metodo = createListenerMethodExpression("#{" + listener + "}");
			metodo.invoke(FacesContext.getCurrentInstance().getELContext(), new Object[]{tmpEvent});
		}
	}
	
	public void asignarListener() {
	}
	
	private static MethodExpression createListenerMethodExpression(String expression) {
		Application app = FacesContext.getCurrentInstance().getApplication();
		ExpressionFactory elFactory = app.getExpressionFactory();
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		MethodExpression methodExpression = elFactory.createMethodExpression(elContext, expression, null, new Class[]{FileUploadEvent.class});
		return methodExpression;
	}
}

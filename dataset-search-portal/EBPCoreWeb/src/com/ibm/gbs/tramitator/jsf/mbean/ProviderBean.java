package com.ibm.gbs.tramitator.jsf.mbean;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.richfaces.component.SortOrder;

import com.ibm.gbs.eubon.ebp.core.ejb.ProviderService;
import com.ibm.gbs.eubon.ebp.core.jpa.entity.Function;
import com.ibm.gbs.eubon.ebp.core.jpa.entity.FunctionProvider;
import com.ibm.gbs.eubon.ebp.core.jpa.entity.Provider;
import com.ibm.gbs.eubon.ebp.core.jpa.entity.ServiceType;
import com.ibm.gbs.tramitator.ejb.ServicioMetadatos;
import com.ibm.gbs.tramitator.ejb.crud.ServicioCRUD;
import com.ibm.gbs.tramitator.util.Constantes;

@ManagedBean(name = "providerBean")
@SessionScoped
public class ProviderBean extends MantoBean implements Serializable 
{

	private static final long serialVersionUID = 708679843476943945L;

	Logger logger = Logger.getLogger("com.ibm.gbs");
	
	@EJB
	private ServicioMetadatos srvMetadatos;

	@EJB 
	private ProviderService providerSrv;
	
	@EJB
	private ServicioCRUD crud;
	
	private ProviderDataModel dataModel;
	private SortingBean sortingBean; 
	private Collection<Object> selection;
	
	private List<ServiceType> serviceTypeList; 
	private String serviceType;
	
	private List<Function> functionsList; 
	private String functionSelected;
	
    private Provider provider;
    
    
	
	public ProviderBean(ServicioMetadatos srvMetadatos) {
		super();
		this.srvMetadatos = srvMetadatos;
	}

	public ProviderBean() {
	}

	@PostConstruct
	public void create() {
		dataModel = new ProviderDataModel(providerSrv);
		sortingBean = new SortingBean();
		sortingBean.sortBy("id", SortOrder.ascending);
	}

	
	public String listProviders() {
		
		serviceTypeList = crud.listadoOrdenado(ServiceType.class, " NAME " );
		
		return "listProviders";
	}
	
	public String consult() {
		provider = readProvider();
		
		if (provider == null)
		{
			return "";
		}
		else
			return "consultProvider";
	}
	
	public String view(){
		return "consultProvider";
	}

	private Provider readProvider() 
	{
		Provider prov = null;
		String idStr = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
				 
		logger.debug("ProviderBean.getProvider - id=" + idStr + "-" );
		Long l = new Long(idStr);
		prov = providerSrv.read(l.longValue()); 
					
		if (prov != null)
		{
			logger.debug("ProviderBean.getProvider - Provider=" + prov.getId() + "-" + prov.getName());
		}				
		
		return prov;
	}
	
	
	public String deleteFunction()
	{		
		String idStr = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
		long id = Long.parseLong(idStr);
	
		// Busco el objeto ProviderFuncione que hay que borrar
		for (Iterator iterator = provider.getFunctionProviders().iterator(); iterator.hasNext();) 
		{
			FunctionProvider fp = (FunctionProvider) iterator.next();
			
			if (fp.getFunction().getId() == id)
			{
				provider.getFunctionProviders().remove(fp);
				break;
			}
		}

		return "";
	}
	
	
	public String delete() 
	{
		if (provider == null)
		{
			return "";
		}
		
		logger.debug("ProviderBean.delete(): id " + provider.getId());
		
		try {
			providerSrv.delete(provider);
			provider = null;
		} catch (Exception e) {
			if (e != null )
			{
				logger.info("ProviderBean.delete Content:" + provider.getId() + " could not be deleted -" + e +"-" +e.getMessage()+"-");

				FacesMessage message = new FacesMessage( " Provider could not be deleted.", " Provider could not be deleted.");
				message.setSeverity( FacesMessage.SEVERITY_ERROR);
				
				FacesContext.getCurrentInstance().addMessage(null, message);
				return Constantes.cadena_vacia;
			}			
		}
			
		return "listProviders";
	}
	
	public String asignFunction(){		

		FunctionProvider fp = new FunctionProvider();
		
		boolean asignarFuncion = true;
		
		long fs = Long.parseLong(functionSelected);
		
		// Compruebo que no esté ya en la lista
		for (Iterator iterator = provider.getFunctionProviders().iterator(); iterator.hasNext();) 
		{
			FunctionProvider fpTest  = (FunctionProvider) iterator.next();
			if (fpTest.getFunction().getId() == fs)
			{
				asignarFuncion = false;
			}
		}

		if (asignarFuncion)
		{
		   Function fun = (Function) crud.obtenerObjeto(Function.class, fs);

		   fp.setFunction(fun);
		   fp.setProvider(provider);		   
		
	  	   provider.addFunctionProvider(fp);
		}		
		return "";
	}
	
	public String edit() 
	{
		if (provider == null)
		{
			return "";
		}
		
		if (provider.getServiceType() == null)
		{
			serviceType = "0";
		}
		else
		{
		   serviceType = String.valueOf(provider.getServiceType().getId());
		}
		
		functionsList = crud.listadoOrdenado(Function.class, " NAME " );
			
		return "editProvider";
	}
	
	public String newBean() {
		
		provider = new Provider();
		
		return "newProvider";
	}
		
	
	public String saveBean()
	{		
		logger.debug("ProviderBean.saveBean - Provider=" + provider.getId() + "-" + provider.getName() + "-");
		
		Long l = new Long(serviceType); 
		
		ServiceType st = (ServiceType) crud.obtenerObjeto(ServiceType.class, l.longValue());
		
		
		if (st == null || st.getId() == 0 )
		{			
			FacesMessage message = new FacesMessage("You have to select a Service Type for the Provider.",	"You have to select a Service Type for the Provider.");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);

			FacesContext.getCurrentInstance().addMessage(null, message);
			return Constantes.cadena_vacia;
		}
		else
		{
			provider.setServiceType(st);
		}
		
				   
	    provider = providerSrv.guardar(provider);	

	    clearCache();
	    
        return "consultProvider";
	}

	public Collection<Object> getSelection() {
		return selection;
	}

	public void setSelection(Collection<Object> selection) {
		this.selection = selection;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}

	public Provider getProvider() {
		return provider;
	}

	public SortingBean getSortingBean() {
		return sortingBean;
	}

	public void setSortingBean(SortingBean sortingBean) {
		this.sortingBean = sortingBean;
	}

	public ProviderDataModel getDataModel() {
		return dataModel;
	}

	public void setDataModel(ProviderDataModel dataModel) {
		this.dataModel = dataModel;
	}

	public List<ServiceType> getServiceTypeList() {
		return serviceTypeList;
	}

	public void setServiceTypeList(List<ServiceType> serviceTypeList) {
		this.serviceTypeList = serviceTypeList;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public List<Function> getFunctionsList() {
		return functionsList;
	}

	public void setFunctionsList(List<Function> functionsList) {
		this.functionsList = functionsList;
	}

	public String getFunctionSelected() {
		return functionSelected;
	}

	public void setFunctionSelected(String functionSelected) {
		this.functionSelected = functionSelected;
	}	
	
}

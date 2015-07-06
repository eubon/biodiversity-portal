package com.ibm.gbs.tramitator.jsf.mbean.taxo;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

//import oracle.jdbc.driver.DatabaseError;

import org.apache.log4j.Logger;

import com.ibm.gbs.ebp.core.exception.NotFoundException;
import com.ibm.gbs.ebp.core.taxonomic.TaxService;
import com.ibm.gbs.ebp.core.taxonomic.TaxServiceImpl;
import com.ibm.gbs.ebp.core.taxonomic.provider.TaxProvider;
import com.ibm.gbs.eubon.ebp.core.dao.RequestDAO;
import com.ibm.gbs.eubon.ebp.core.dao.ServiceTypeDAO;
import com.ibm.gbs.eubon.ebp.core.ejb.RequestService;
import com.ibm.gbs.eubon.ebp.core.jpa.entity.Provider;
import com.ibm.gbs.eubon.ebp.core.jpa.entity.Request;
import com.ibm.gbs.eubon.ebp.core.jpa.entity.ServiceType;
import com.ibm.gbs.eubon.ebp.core.jpa.entity.UserRequest;
import com.ibm.gbs.eubon.taxonomic.TaxRecord;
import com.ibm.gbs.eubon.taxonomic.TaxVernacular;
import com.ibm.gbs.tramitator.ejb.ServicioMetadatos;
import com.ibm.gbs.tramitator.ejb.crud.ServicioCRUD;
import com.ibm.gbs.tramitator.util.Constantes;
import com.ibm.gbs.tramitator.util.configuracion.Propiedades;

@ManagedBean(name = "taxoBean")
@SessionScoped
public class TaxoBean implements Serializable {

	private static final long serialVersionUID = 708679843476943945L;

	Logger logger = Logger.getLogger("com.ibm.gbs");
	
	@EJB
	private ServicioMetadatos srvMetadatos;
	
	@EJB
	private ServicioCRUD crud;

	@EJB
	private ServiceTypeDAO stDAO;
	
	@EJB
	private RequestService requestService;

	private TaxService taxService;
    
    private String scientificName;
    
    private String name;
    
    private boolean like;
    
    private String GUID;
    
    private List<TaxRecord> recordList;
    
    private List<TaxVernacular> vernacularList;
    
    private TaxRecord record; 
    
    private String result;
    
    private List<Provider> providerList; 
    
    private int provider;
	
	public TaxoBean(ServicioMetadatos srvMetadatos) {
		super();
		this.srvMetadatos = srvMetadatos;
	}

	public TaxoBean() 
	{
		//initProxy();
	}

	@PostConstruct
	public void create() {
		taxService = new TaxServiceImpl(crud);	
	}
	
	public String startSearch() {
	
		scientificName = "";
		like = false;
		GUID = "";
		
		providerList = crud.listadoFiltroOrdenado(Provider.class," service_type_id = " + 
		          Propiedades.getPropiedad(Constantes.CFG_TAXONOMIC_PROVIDER_ID), " NAME " );
		
		return "taxSearch";
	}
	

	public String taxSearch() 
	{
		
        logger.debug("TaxoBean.taxSearch(): scientificName=" + name + "- like=" + like + "-");
         
        
        try {
        	
        	TaxProvider taxProvider = taxService.getProvider(provider);
        	
        	// Guardar la Request y la UserRequest
        	Request request = new Request();
            request.setProvider(taxProvider.getProvider());
            Date reqDate = Calendar.getInstance().getTime();
            request.setReqDate(reqDate);
            String reqText = "Taxonomic.taxSearch:name=" + name +"-";
            request.setReqText(reqText);
            
            UserRequest userRequest = new UserRequest();
            userRequest.setInterfaceType(Constantes.INTERFACE_WEB);
            userRequest.setReqDate(reqDate);
            userRequest.setReqText(reqText);
            
            request.setUserRequest(userRequest);
            
            request = requestService.guardar(request);
            logger.info("TaxoBean.taxSearch(): request=" + request.getId()+ "-");
            // Fin Guardar la Request y la UserRequest

        	try {
				GUID = taxService.getGUID(name, taxProvider);
			} catch (NotFoundException e) {
				
				GUID = null;
			}
        	
			if (GUID == null || GUID.equals(Constantes.cadena_vacia))
			{
				FacesMessage message = new FacesMessage(name + " Not Found.", name + " Not Found.");
				message.setSeverity( FacesMessage.SEVERITY_ERROR);
				
				FacesContext.getCurrentInstance().addMessage(null, message);
				return Constantes.cadena_vacia;

			}
			
			
		} catch (RemoteException e) {
			
			e.printStackTrace();
			FacesMessage message = new FacesMessage("Techcnical Problems connecting with taxonomic provider.", "Techcnical Problems connecting with taxonomic provider.");
			message.setSeverity( FacesMessage.SEVERITY_ERROR);
			
			FacesContext.getCurrentInstance().addMessage(null, message);
			return Constantes.cadena_vacia;

		} 
        
        logger.debug("TaxoBean.taxSearch(): gUID=" + GUID + "-");

        
		return "taxResult";
	}
	
	public String searchNameByGUID() 
	{
		
        logger.debug("TaxoBean.searchNameByGUID(): GUID=" + GUID + "-");
        
        try {
        	TaxProvider taxProvider = taxService.getProvider(provider);
        	
        	// Guardar la Request y la UserRequest
        	Request request = new Request();
            request.setProvider(taxProvider.getProvider());
            Date reqDate = Calendar.getInstance().getTime();
            request.setReqDate(reqDate);
            String reqText = "Taxonomic.searchNameByGUID:GUID=" + GUID +"-";
            request.setReqText(reqText);
            
            UserRequest userRequest = new UserRequest();
            userRequest.setInterfaceType(Constantes.INTERFACE_WEB);
            userRequest.setReqDate(reqDate);
            userRequest.setReqText(reqText);
            
            request.setUserRequest(userRequest);
            
            request = requestService.guardar(request);
            logger.info("TaxoBean.searchNameByGUID(): request=" + request.getId()+ "-");
            // Fin Guardar la Request y la UserRequest

        	try {
				name = taxService.getNameByID(GUID, taxProvider);
			} catch (NotFoundException e) {
				
				name = null;
			}
        	
			if (name == null || name.equals(Constantes.cadena_vacia))
			{
				FacesMessage message = new FacesMessage(GUID + " Not Found.", GUID + " Not Found.");
				message.setSeverity( FacesMessage.SEVERITY_ERROR);
				
				FacesContext.getCurrentInstance().addMessage(null, message);
				return Constantes.cadena_vacia;
			}			
		} catch (RemoteException e) {
			
			e.printStackTrace();
			FacesMessage message = new FacesMessage("Techcnical Problems connecting with taxonomic provider.", "Techcnical Problems connecting with taxonomic provider.");
			message.setSeverity( FacesMessage.SEVERITY_ERROR);
			
			FacesContext.getCurrentInstance().addMessage(null, message);
			return Constantes.cadena_vacia;

		} 
        
        logger.debug("TaxoBean.searchNameByGUID(): name=" + name + "-");

        
		return "taxResult";
	}
	
	public String searchTaxRecords() 
	{
		
        logger.debug("TaxoBean.searchTaxRecords(): scientificName=" + name + "- like=" + like + "-");
          
        try {
        	
        	TaxProvider taxProvider = taxService.getProvider(provider);
        	
        	// Guardar la Request y la UserRequest
	        	Request request = new Request();
	            request.setProvider(taxProvider.getProvider());
	            Date reqDate = Calendar.getInstance().getTime();
	            request.setReqDate(reqDate);
	            String reqText = "Taxonomic.searchTaxRecords:name=" + name +"-" + like + "-";
	            request.setReqText(reqText);
	            
	            UserRequest userRequest = new UserRequest();
	            userRequest.setInterfaceType(Constantes.INTERFACE_WEB);
	            userRequest.setReqDate(reqDate);
	            userRequest.setReqText(reqText);
	            
	            request.setUserRequest(userRequest);
	            
	            request = requestService.guardar(request);
                logger.info("TaxoBean.searchTaxRecords(): request=" + request.getId()+ "-");
        	// Fin Guardar la Request y la UserRequest
            
        	try {
				recordList = taxService.getRecords(name, like, taxProvider);
			} catch (NotFoundException e) {
				recordList = null;
			}
			
        	if (recordList == null || recordList.size() == 0)
			{
				FacesMessage message = new FacesMessage(name + " Not Found.", name + " Not Found.");
				message.setSeverity( FacesMessage.SEVERITY_ERROR);
				
				FacesContext.getCurrentInstance().addMessage(null, message);
				return Constantes.cadena_vacia;
			}	
						
		} catch (RemoteException e) {
			
			e.printStackTrace();
			FacesMessage message = new FacesMessage("Techcnical Problems connecting with taxonomic provider.", "Techcnical Problems connecting with taxonomic provider.");
			message.setSeverity( FacesMessage.SEVERITY_ERROR);
			
			FacesContext.getCurrentInstance().addMessage(null, message);
			return Constantes.cadena_vacia;

		} 
        
        logger.debug("TaxoBean.searchTaxRecords(): result=" + recordList + "-");

        
		return "taxRecords";
	}
	
	public String searchTaxRecordByGUID() 
	{
		
        logger.debug("TaxoBean.searchTaxRecordsByGUID(): GUID=" + GUID + "-");
          
        try {
        	
        	TaxProvider taxProvider = taxService.getProvider(provider);
        	
        	// Guardar la Request y la UserRequest
        	Request request = new Request();
            request.setProvider(taxProvider.getProvider());
            Date reqDate = Calendar.getInstance().getTime();
            request.setReqDate(reqDate);
            String reqText = "Taxonomic.searchTaxRecordByGUID:GUID=" + GUID +"-";
            request.setReqText(reqText);
            
            UserRequest userRequest = new UserRequest();
            userRequest.setInterfaceType(Constantes.INTERFACE_WEB);
            userRequest.setReqDate(reqDate);
            userRequest.setReqText(reqText);
            
            request.setUserRequest(userRequest);
            
            request = requestService.guardar(request);
            logger.info("TaxoBean.searchRecordsByGUID(): request=" + request.getId()+ "-");
    	    // Fin Guardar la Request y la UserRequest
            
        	try {
				record = taxService.getRecordByGUID(GUID, taxProvider);
				recordList = new ArrayList<TaxRecord>();
				recordList.add(record);
			} catch (NotFoundException e) {
				recordList = null;
			}
			
        	if (recordList == null || recordList.size() == 0)
			{
				FacesMessage message = new FacesMessage(GUID + " Not Found.", GUID + " Not Found.");
				message.setSeverity( FacesMessage.SEVERITY_ERROR);
				
				FacesContext.getCurrentInstance().addMessage(null, message);
				return Constantes.cadena_vacia;
			}	
						
		} catch (RemoteException e) {
			
			e.printStackTrace();
			FacesMessage message = new FacesMessage("Techcnical Problems connecting with taxonomic provider.", "Techcnical Problems connecting with taxonomic provider.");
			message.setSeverity( FacesMessage.SEVERITY_ERROR);
			
			FacesContext.getCurrentInstance().addMessage(null, message);
			return Constantes.cadena_vacia;

		} 
        
        logger.debug("TaxoBean.searchTaxRecordsByGUID(): result=" + recordList + "-");

        
		return "taxRecords";
	}
	
	public String searchTaxRecordsSynonimsByGUID() 
	{
		
        logger.debug("TaxoBean.searchTaxRecordsSynonimsByGUID(): GUID=" + GUID + "-");

        try 
        {        	
        	TaxProvider taxProvider = taxService.getProvider(provider);
        	
        	// Guardar la Request y la UserRequest
        	Request request = new Request();
            request.setProvider(taxProvider.getProvider());
            Date reqDate = Calendar.getInstance().getTime();
            request.setReqDate(reqDate);
            String reqText = "Taxonomic.searchTaxRecordsSynonimsByGUID:GUID=" + GUID +"-";
            request.setReqText(reqText);
            
            UserRequest userRequest = new UserRequest();
            userRequest.setInterfaceType(Constantes.INTERFACE_WEB);
            userRequest.setReqDate(reqDate);
            userRequest.setReqText(reqText);
            
            request.setUserRequest(userRequest);
            
            request = requestService.guardar(request);
            logger.info("TaxoBean.searchTaxRecordsSynonimsByGUID(): request=" + request.getId()+ "-");
            // Fin Guardar la Request y la UserRequest
            
        	try {
				recordList = taxService.getSynonymsByGUID(GUID, taxProvider);
			} catch (NotFoundException e) {
				recordList = null;
			}
			
        	if (recordList == null || recordList.size() == 0)
			{
				FacesMessage message = new FacesMessage("Synonyms Not Found for: " + GUID +".", "Synonyms Not Found for: " + GUID +".");
				message.setSeverity( FacesMessage.SEVERITY_ERROR);
				
				FacesContext.getCurrentInstance().addMessage(null, message);
				return Constantes.cadena_vacia;
			}	
						
		} catch (RemoteException e) {
			
			e.printStackTrace();
			FacesMessage message = new FacesMessage("Techcnical Problems connecting with taxonomic provider.", "Techcnical Problems connecting with taxonomic provider.");
			message.setSeverity( FacesMessage.SEVERITY_ERROR);
			
			FacesContext.getCurrentInstance().addMessage(null, message);
			return Constantes.cadena_vacia;

		} 
        
        logger.debug("TaxoBean.searchTaxRecordsSynonimsByGUID(): result=" + recordList + "-");

        
		return "taxRecords";
	}
	
	public String searchTaxVernacularsByGUID() 
	{
		
        logger.debug("TaxoBean.searchTaxVernacularsByGUID(): GUID=" + GUID + "-");
          
        try {
        	
        	TaxProvider taxProvider = taxService.getProvider(provider);
        	
        	// Guardar la Request y la UserRequest
        	Request request = new Request();
            request.setProvider(taxProvider.getProvider());
            Date reqDate = Calendar.getInstance().getTime();
            request.setReqDate(reqDate);
            String reqText = "Taxonomic.searchTaxVernacularsByGUID:GUID=" + GUID +"-";
            request.setReqText(reqText);
            
            UserRequest userRequest = new UserRequest();
            userRequest.setInterfaceType(Constantes.INTERFACE_WEB);
            userRequest.setReqDate(reqDate);
            userRequest.setReqText(reqText);
            
            request.setUserRequest(userRequest);
            
            request = requestService.guardar(request);
            logger.info("TaxoBean.searchTaxVernacularsByGUID(): request=" + request.getId()+ "-");
            // Fin Guardar la Request y la UserRequest
            
            
        	try {
				vernacularList = taxService.getVernacularsByGUID(GUID, taxProvider);
				
			} catch (NotFoundException e) {
				vernacularList = null;
			}
			
        	if (vernacularList == null || vernacularList.size() == 0)
			{
				FacesMessage message = new FacesMessage(name + " Not Found.", name + " Not Found.");
				message.setSeverity( FacesMessage.SEVERITY_ERROR);
				
				FacesContext.getCurrentInstance().addMessage(null, message);
				return Constantes.cadena_vacia;
			}	
						
		} catch (RemoteException e) {
			
			e.printStackTrace();
			FacesMessage message = new FacesMessage("Techcnical Problems connecting with taxonomic provider.", "Techcnical Problems connecting with taxonomic provider.");
			message.setSeverity( FacesMessage.SEVERITY_ERROR);
			
			FacesContext.getCurrentInstance().addMessage(null, message);
			return Constantes.cadena_vacia;

		} 
        
        logger.debug("TaxoBean.searchTaxVernacularsByGUID(): result=" + vernacularList + "-");

        
		return "taxVernaculars";
	}
	
	public String searchTaxRecordsByVernacular() 
	{
		
        logger.debug("TaxoBean.searchTaxRecordsByVernacular(): name=" + name + "- like=" + like + "-");
         
        try {
        	
        	TaxProvider taxProvider = taxService.getProvider(provider);
        	
        	// Guardar la Request y la UserRequest
        	Request request = new Request();
            request.setProvider(taxProvider.getProvider());
            Date reqDate = Calendar.getInstance().getTime();
            request.setReqDate(reqDate);
            String reqText = "Taxonomic.searchTaxRecordsByVernacular: vernacular=" + name +"-";
            request.setReqText(reqText);
            
            UserRequest userRequest = new UserRequest();
            userRequest.setInterfaceType(Constantes.INTERFACE_WEB);
            userRequest.setReqDate(reqDate);
            userRequest.setReqText(reqText);
            
            request.setUserRequest(userRequest);
            
            request = requestService.guardar(request);
            logger.info("TaxoBean.searchTaxRecordsByVernacular(): request=" + request.getId()+ "-");
    	    // Fin Guardar la Request y la UserRequest
            
            
        	try {
				recordList = taxService.getRecordsByVernacular(name, taxProvider);
			} catch (NotFoundException e) {
				recordList = null;
			}
			
        	if (recordList == null || recordList.size() == 0)
			{
				FacesMessage message = new FacesMessage(name + " Not Found.", name + " Not Found.");
				message.setSeverity( FacesMessage.SEVERITY_ERROR);
				
				FacesContext.getCurrentInstance().addMessage(null, message);
				return Constantes.cadena_vacia;
			}
			
		} catch (RemoteException e) {
			
			e.printStackTrace();
			FacesMessage message = new FacesMessage("Techcnical Problems connecting with taxonomic provider.", "Techcnical Problems connecting with taxonomic provider.");
			message.setSeverity( FacesMessage.SEVERITY_ERROR);
			
			FacesContext.getCurrentInstance().addMessage(null, message);
			return Constantes.cadena_vacia;

		} 
        
        logger.debug("TaxoBean.searchTaxRecordsByVernacular(): result=" + recordList + "-");

        
		return "taxRecords";
	}

	public String getScientificName() {
		return scientificName;
	}

	public void setScientificName(String scientificName) {
		this.scientificName = scientificName;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public boolean isLike() {
		return like;
	}

	public void setLike(boolean like) {
		this.like = like;
	}

	public TaxRecord getRecord() {
		return record;
	}

	public void setRecord(TaxRecord record) {
		this.record = record;
	}

	public List<TaxRecord> getRecordList() {
		return recordList;
	}

	public void setRecordList(List<TaxRecord> recordList) {
		this.recordList = recordList;
	}

	public String getGUID() {
		return GUID;
	}

	public void setGUID(String gUID) {
		GUID = gUID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Provider> getProviderList() {
		return providerList;
	}

	public void setProviderList(List<Provider> providerList) {
		this.providerList = providerList;
	}

	public int getProvider() {
		return provider;
	}

	public void setProvider(int provider) {
		this.provider = provider;
	}

	public List<TaxVernacular> getVernacularList() {
		return vernacularList;
	}

	public void setVernacularList(List<TaxVernacular> vernacularList) {
		this.vernacularList = vernacularList;
	}

	
	
}


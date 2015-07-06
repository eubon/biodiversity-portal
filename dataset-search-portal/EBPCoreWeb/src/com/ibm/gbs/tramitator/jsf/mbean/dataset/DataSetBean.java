package com.ibm.gbs.tramitator.jsf.mbean.dataset;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import com.ibm.gbs.csw.client.data.CSWRecord;
import com.ibm.gbs.csw.client.data.CSWSearchResult;
import com.ibm.gbs.ebp.core.dataset.DataSetService;
import com.ibm.gbs.ebp.core.dataset.DataSetServiceImpl;
import com.ibm.gbs.ebp.core.dataset.provider.CSWDataSetProvider;

import com.ibm.gbs.ebp.core.dataset.provider.GbifDataSetProvider;
import com.ibm.gbs.ebp.core.exception.ConnectionException;
import com.ibm.gbs.ebp.core.exception.NotFoundException;
import com.ibm.gbs.eubon.ebp.core.ejb.ProviderService;
import com.ibm.gbs.eubon.ebp.core.ejb.RequestService;
import com.ibm.gbs.eubon.ebp.core.jpa.entity.Country;
import com.ibm.gbs.eubon.ebp.core.jpa.entity.Provider;
import com.ibm.gbs.eubon.ebp.core.jpa.entity.Request;
import com.ibm.gbs.eubon.ebp.core.jpa.entity.UserRequest;
import com.ibm.gbs.eubon.filters.DataSetFilter;
import com.ibm.gbs.gbif.client.GbifClient;
import com.ibm.gbs.gbif.client.data.DataSet;
import com.ibm.gbs.gbif.client.data.DataSetRow;
import com.ibm.gbs.gbif.client.data.Organization;
import com.ibm.gbs.gbif.client.data.GbifSearchResult;
import com.ibm.gbs.tramitator.ejb.ServicioMetadatos;
import com.ibm.gbs.tramitator.ejb.crud.ServicioCRUD;
import com.ibm.gbs.tramitator.util.Constantes;
import com.ibm.gbs.tramitator.util.configuracion.Propiedades;

@ManagedBean(name = "dataSetBean")
@SessionScoped
public class DataSetBean implements Serializable {

	private static final long serialVersionUID = 708679843476943945L;

	Logger logger = Logger.getLogger("com.ibm.gbs");
	
	@EJB
	private ServicioMetadatos srvMetadatos;
	
	@EJB
	private ServicioCRUD crud;
	
	@EJB
	private RequestService requestService;
	
	@EJB 
	private ProviderService providerService;

	
	private GbifClient gbifClient;
    
	private DataSetFilter dataSetFilter;    
    
    private GbifSearchResult searchResult;
    private CSWSearchResult cswSearchResult;
    
    private DataSetRow row;
    private CSWRecord cswRow;
    
    private DataSet dataSet;
    
    private Organization organization;
    
    private List<Country> countryList;
    
    private DataSetService dataSetService;
    
    private List<Provider> providerList; 
    
    private int provider;
    
	public DataSetBean(ServicioMetadatos srvMetadatos) {
		super();
		this.srvMetadatos = srvMetadatos;
	}

	public DataSetBean() 
	{
		//initProxy();
	}

	@PostConstruct
	public void create() {
		dataSetService = new DataSetServiceImpl(crud);	
	}
	
	public String startSearch() {
	
		dataSetFilter = new DataSetFilter();
		
		countryList = crud.listadoOrdenado(Country.class, " NAME " );
		
		providerList = crud.listadoFiltroOrdenado(Provider.class," service_type_id = " + 
		          Propiedades.getPropiedad(Constantes.CFG_DATASET_PROVIDER_ID), " NAME " );
		
		searchResult = null;
		cswSearchResult = null;
		
		return "dataSetSearch";
	}
	

	public String dataSetSearch() 
	{
		
        logger.info("DataSetBean.dataSetSearch(): q=" + dataSetFilter.getQ() + "- publishingCountr=" + dataSetFilter.getPublishingCountry() + "-");
         
        String idProvider = String.valueOf(provider);
        if (idProvider.equals(Propiedades.getPropiedad(Constantes.CFG_GBIFN_ID)))
		{
			return dataSetSearchGbif();			
		}
		
		if (idProvider.equals(Propiedades.getPropiedad(Constantes.CFG_LTER_ID)))
		{
			return dataSetSearchCSW();		
		}
        
        
/*        DataSetProvider dataSetProvider = dataSetService.getProvider(provider);
        
     // Guardar la Request y la UserRequest
    	Request request = new Request();
        request.setProvider(dataSetProvider.getProvider());
        Date reqDate = Calendar.getInstance().getTime();
        request.setReqDate(reqDate);
        String reqText = "DataSet.dataSetSearch:q=" + dataSetFilter.getQ() + "- publishingCountr=" + dataSetFilter.getPublishingCountry() + "-";
        request.setReqText(reqText);
        
        UserRequest userRequest = new UserRequest();
        userRequest.setInterfaceType(Constantes.INTERFACE_WEB);
        userRequest.setReqDate(reqDate);
        userRequest.setReqText(reqText);
        
        request.setUserRequest(userRequest);
        
        request = requestService.guardar(request);
        logger.info("DataSet.dataSetSearch(): request=" + request.getId()+ "-");
        // Fin Guardar la Request y la UserRequest
        
        try {
        	        	        	
        	searchResult = dataSetProvider.getDataSets(dataSetFilter);
        	
			if (searchResult == null )
			{
				FacesMessage message = new FacesMessage( " Not Found.", " Not Found.");
				message.setSeverity( FacesMessage.SEVERITY_ERROR);
				
				FacesContext.getCurrentInstance().addMessage(null, message);
				return Constantes.cadena_vacia;
			}			
			
		} catch (NotFoundException e) {
			
			e.printStackTrace();
			FacesMessage message = new FacesMessage("Not Found.", "Not Found.");
			message.setSeverity( FacesMessage.SEVERITY_ERROR);
			
			FacesContext.getCurrentInstance().addMessage(null, message);
			return Constantes.cadena_vacia;

		} catch (ConnectionException e) {
			
			e.printStackTrace();
			FacesMessage message = new FacesMessage("Techcnical Problems connecting with dataset provider.", "Techcnical Problems connecting with dataset provider.");
			message.setSeverity( FacesMessage.SEVERITY_ERROR);
			
			FacesContext.getCurrentInstance().addMessage(null, message);
			return Constantes.cadena_vacia;
		} 
        
        logger.debug("DataSetBean.dataSetSearch(): -");

        */
		return "dataSetResult";
	}
	
	public String dataSetSearchGbif() 
	{
		
        logger.info("DataSetBean.dataSetSearch(): q=" + dataSetFilter.getQ() + "- publishingCountr=" + dataSetFilter.getPublishingCountry() + "-");
                
        
        String idProvider = String.valueOf(provider);
		
        long id = provider;
		Provider prov = (Provider)crud.obtenerObjeto(Provider.class, id);		
        
		GbifDataSetProvider dataSetProvider = new GbifDataSetProvider(prov);	
        
     // Guardar la Request y la UserRequest
    	Request request = new Request();
        request.setProvider(dataSetProvider.getProvider());
        Date reqDate = Calendar.getInstance().getTime();
        request.setReqDate(reqDate);
        String reqText = "DataSet.dataSetSearchGbif:q=" + dataSetFilter.getQ() + "- publishingCountr=" + dataSetFilter.getPublishingCountry() + "-";
        request.setReqText(reqText);
        
        UserRequest userRequest = new UserRequest();
        userRequest.setInterfaceType(Constantes.INTERFACE_WEB);
        userRequest.setReqDate(reqDate);
        userRequest.setReqText(reqText);
        
        request.setUserRequest(userRequest);
        
        request = requestService.guardar(request);
        logger.info("DataSet.dataSetSearch(): request=" + request.getId()+ "-");
        // Fin Guardar la Request y la UserRequest
        
        try {
        	        	        	
        	searchResult = dataSetProvider.getDataSets(dataSetFilter);
        	
			if (searchResult == null )
			{
				FacesMessage message = new FacesMessage( " Not Found.", " Not Found.");
				message.setSeverity( FacesMessage.SEVERITY_ERROR);
				
				FacesContext.getCurrentInstance().addMessage(null, message);
				return Constantes.cadena_vacia;
			}			
			
		} catch (NotFoundException e) {
			
			e.printStackTrace();
			FacesMessage message = new FacesMessage("Not Found.", "Not Found.");
			message.setSeverity( FacesMessage.SEVERITY_ERROR);
			
			FacesContext.getCurrentInstance().addMessage(null, message);
			return Constantes.cadena_vacia;

		} catch (ConnectionException e) {
			
			e.printStackTrace();
			FacesMessage message = new FacesMessage("Techcnical Problems connecting with dataset provider.", "Techcnical Problems connecting with dataset provider.");
			message.setSeverity( FacesMessage.SEVERITY_ERROR);
			
			FacesContext.getCurrentInstance().addMessage(null, message);
			return Constantes.cadena_vacia;
		} 
        
        logger.debug("DataSetBean.dataSetSearch(): -");

        
		return "dataSetResult";
	}
	

	public String dataSetSearchCSW() 
	{
		
        logger.info("DataSetBean.dataSetSearchCSW(): q=" + dataSetFilter.getQ() + "- publishingCountr=" + dataSetFilter.getPublishingCountry() + "-");
                
        String idProvider = String.valueOf(provider);
		
        long id = provider;
		Provider prov = (Provider)crud.obtenerObjeto(Provider.class, id);		
        
		CSWDataSetProvider dataSetProvider = new CSWDataSetProvider(prov);			
		
        
     // Guardar la Request y la UserRequest
    	Request request = new Request();
        request.setProvider(dataSetProvider.getProvider());
        Date reqDate = Calendar.getInstance().getTime();
        request.setReqDate(reqDate);
        String reqText = "DataSet.dataSetSearch:q=" + dataSetFilter.getQ() + "- publishingCountr=" + dataSetFilter.getPublishingCountry() + "-";
        request.setReqText(reqText);
        
        UserRequest userRequest = new UserRequest();
        userRequest.setInterfaceType(Constantes.INTERFACE_WEB);
        userRequest.setReqDate(reqDate);
        userRequest.setReqText(reqText);
        
        request.setUserRequest(userRequest);
        
        request = requestService.guardar(request);
        logger.info("DataSet.dataSetSearch(): request=" + request.getId()+ "-");
        // Fin Guardar la Request y la UserRequest
        
        try {
        	        	        	
        	cswSearchResult = dataSetProvider.getDataSets(dataSetFilter);
        	
			if (cswSearchResult == null )
			{
				FacesMessage message = new FacesMessage( " Not Found.", " Not Found.");
				message.setSeverity( FacesMessage.SEVERITY_ERROR);
				
				FacesContext.getCurrentInstance().addMessage(null, message);
				return Constantes.cadena_vacia;
			}			
			
		} catch (NotFoundException e) {
			
			e.printStackTrace();
			FacesMessage message = new FacesMessage("Not Found.", "Not Found.");
			message.setSeverity( FacesMessage.SEVERITY_ERROR);
			
			FacesContext.getCurrentInstance().addMessage(null, message);
			return Constantes.cadena_vacia;

		} catch (ConnectionException e) {
			
			e.printStackTrace();
			FacesMessage message = new FacesMessage("Techcnical Problems connecting with dataset provider.", "Techcnical Problems connecting with dataset provider.");
			message.setSeverity( FacesMessage.SEVERITY_ERROR);
			
			FacesContext.getCurrentInstance().addMessage(null, message);
			return Constantes.cadena_vacia;
		} 
        
        logger.debug("DataSetBean.dataSetSearch(): -");

        
		return "dataSetResult";
	}
	
	
	public String dataSetSearchNext() 
	{		
        logger.debug("DataSetBean.dataSetSearchNext(): Filter=" + dataSetFilter + "-");    

        if (searchResult == null || searchResult.isEndOfRecords() || searchResult.getCount() == searchResult.getLimit())
        {
        	FacesMessage message = new FacesMessage( "End of Records.", "End of Records.");
			message.setSeverity( FacesMessage.SEVERITY_ERROR);
			
			FacesContext.getCurrentInstance().addMessage(null, message);
			return Constantes.cadena_vacia;
        }
        
        dataSetFilter.setOffset(searchResult.getOffset() + searchResult.getLimit());
        
        return dataSetSearch();
        
   	}
	
	public String dataSetSearchPrev() 
	{		
        logger.debug("DataSetBean.dataSetSearchPrev(): Filter=" + dataSetFilter + "-");    

        if (searchResult == null || dataSetFilter.getOffset() == 0)
        {
        	FacesMessage message = new FacesMessage( "End of Records.", "End of Records.");
			message.setSeverity( FacesMessage.SEVERITY_ERROR);
			
			FacesContext.getCurrentInstance().addMessage(null, message);
			return Constantes.cadena_vacia;
        	
        }
        
        dataSetFilter.setOffset(searchResult.getOffset() - searchResult.getLimit());
        
        
        if (dataSetFilter.getOffset() < 0)
        {
        	dataSetFilter.setOffset(0);
        }
        
        return dataSetSearch();
        
   	}
	

	public String dataSetCSWSearchNext() 
	{		
        logger.debug("DataSetBean.dataSetCSWSearchNext(): Filter=" + dataSetFilter + "-");    

        if (cswSearchResult == null || cswSearchResult.isEndOfRecords() )
        {
        	FacesMessage message = new FacesMessage( "End of Records.", "End of Records.");
			message.setSeverity( FacesMessage.SEVERITY_ERROR);
			
			FacesContext.getCurrentInstance().addMessage(null, message);
			return Constantes.cadena_vacia;
        }
        
        dataSetFilter.setOffset(cswSearchResult.getNextRecord());
        
        return dataSetSearch();
        
   	}
	
	public String dataSetCSWSearchPrev() 
	{		
        logger.debug("DataSetBean.dataSetCSWSearchPrev(): Filter=" + dataSetFilter + "-");    

        if (cswSearchResult == null || dataSetFilter.getOffset() == 0)
        {
        	FacesMessage message = new FacesMessage( "End of Records.", "End of Records.");
			message.setSeverity( FacesMessage.SEVERITY_ERROR);
			
			FacesContext.getCurrentInstance().addMessage(null, message);
			return Constantes.cadena_vacia;
        	
        }
        
        dataSetFilter.setOffset(cswSearchResult.getNextRecord() - cswSearchResult.getNumberOfRecordsReturned());
        
        
        if (dataSetFilter.getOffset() < 0)
        {
        	dataSetFilter.setOffset(0);
        }
        
        return dataSetSearch();
        
   	}
	
	public String consultDataSetRow() 
	{
		
        logger.debug("DataSetBean.consultDataSetRow(): -");
        
        row = null;
        
        String keyStr = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("key");
         
        if (searchResult == null)
        {
			FacesMessage message = new FacesMessage("Not Found.", "Not Found.");
			message.setSeverity( FacesMessage.SEVERITY_ERROR);
			
			FacesContext.getCurrentInstance().addMessage(null, message);
			return Constantes.cadena_vacia;
        }
        else
        {
        	
        	for (Iterator iterator = searchResult.getDataSets().iterator(); iterator.hasNext();) {
				DataSetRow dsrow = (DataSetRow) iterator.next();
				
				if (dsrow.getKey().equals(keyStr))
				{
					row = dsrow;
					break;
				}
				
			}
        }
        
        logger.debug("DataSetBean.consultDataSetRow(): -");

        
		return "dataSetResultRow";
	}
	
	public String consultCSWDataSetRow() 
	{
		
        logger.debug("DataSetBean.consultCSWDataSetRow(): -");
        
        cswRow = null;
        
        String keyStr = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("key");
         
        if (cswSearchResult == null)
        {
			FacesMessage message = new FacesMessage("Not Found.", "Not Found.");
			message.setSeverity( FacesMessage.SEVERITY_ERROR);
			
			FacesContext.getCurrentInstance().addMessage(null, message);
			return Constantes.cadena_vacia;
        }
        else
        {
        	
        	for (Iterator iterator = cswSearchResult.getRecords().iterator(); iterator.hasNext();) {
        		CSWRecord dsrow = (CSWRecord) iterator.next();
				
				if (dsrow.getIdentifier().equals(keyStr))
				{
					cswRow = dsrow;
					break;
				}
				
			}
        }
        
        logger.debug("DataSetBean.consultCSWDataSetRow(): -");

        
		return "dataSetCSWResultRow";
	}
	
	public String backToDataSetResult()
	{
		return "dataSetResult";
	}

	public String backToDataSetDetail()
	{
		return "dataSetDetail";
	}

	public String consultDataSet() 
	{
		
        logger.debug("DataSetBean.consultDataSet(): -");
         
        long gbifId = Long.parseLong(Propiedades.getPropiedad(Constantes.CFG_GBIFN_ID));
        Provider prov = (Provider)crud.obtenerObjeto(Provider.class, gbifId);
        
		String key = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("key");
				 
		logger.debug("DataSetBean.consultDataSet(): - key=" + key + "-" );
        
        try {
        	
        	gbifClient = new GbifClient(prov.getServiceUrl());
        	
        	dataSet = gbifClient.getDataSet(key);
        	
			if (dataSet == null )
			{
				FacesMessage message = new FacesMessage( " Not Found.", " Not Found.");
				message.setSeverity( FacesMessage.SEVERITY_ERROR);
				
				FacesContext.getCurrentInstance().addMessage(null, message);
				return Constantes.cadena_vacia;
			}
			else
			{
				String org = dataSet.getPublishingOrganizationKey();
				organization = gbifClient.getOrganization(org);
			}
			
		} catch (NotFoundException e) {
			
			e.printStackTrace();
			FacesMessage message = new FacesMessage("Not Found.", "Not Found.");
			message.setSeverity( FacesMessage.SEVERITY_ERROR);
			
			FacesContext.getCurrentInstance().addMessage(null, message);
			return Constantes.cadena_vacia;

		} 
        catch (ConnectionException e) {
			e.printStackTrace();
			FacesMessage message = new FacesMessage("Techcnical Problems connecting with dataset provider.", "Techcnical Problems connecting with dataset provider.");
			message.setSeverity( FacesMessage.SEVERITY_ERROR);
			
			FacesContext.getCurrentInstance().addMessage(null, message);
			return Constantes.cadena_vacia;
		} 
        
        logger.debug("DataSetBean.dataConsultSearch(): -");

        
		return "dataSetDetail";
	}
	
	public String consultOrganization() 
	{
		
        logger.debug("DataSetBean.consultOrganization(): -");
         
        long gbifId = Long.parseLong(Propiedades.getPropiedad(Constantes.CFG_GBIFN_ID));
        Provider prov = (Provider)crud.obtenerObjeto(Provider.class, gbifId);

		String key = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("key");
				 
		logger.debug("DataSetBean.consultOrganization(): - key=" + key + "-" );
        
        try {
        	
        	gbifClient = new GbifClient(prov.getServiceUrl());
        	
        	organization = gbifClient.getOrganization(key);
        	
			if (organization == null )
			{
				FacesMessage message = new FacesMessage( " Not Found.", " Not Found.");
				message.setSeverity( FacesMessage.SEVERITY_ERROR);
				
				FacesContext.getCurrentInstance().addMessage(null, message);
				return Constantes.cadena_vacia;
			}
			
		} catch (NotFoundException e) {
			
			e.printStackTrace();
			FacesMessage message = new FacesMessage("Not Found.", "Not Found.");
			message.setSeverity( FacesMessage.SEVERITY_ERROR);
			
			FacesContext.getCurrentInstance().addMessage(null, message);
			return Constantes.cadena_vacia;

		} 
        catch (ConnectionException e) {
			e.printStackTrace();
			FacesMessage message = new FacesMessage("Techcnical Problems connecting with dataset provider.", "Techcnical Problems connecting with dataset provider.");
			message.setSeverity( FacesMessage.SEVERITY_ERROR);
			
			FacesContext.getCurrentInstance().addMessage(null, message);
			return Constantes.cadena_vacia;
		} 
        
        logger.debug("DataSetBean.consultOrganization(): -");

        
		return "dataSetOrganizationDetail";
	}

	

	public GbifSearchResult getSearchResult() {
		return searchResult;
	}

	public void setSearchResult(GbifSearchResult searchResult) {
		this.searchResult = searchResult;
	}



	public DataSetRow getRow() {
		return row;
	}

	public void setRow(DataSetRow row) {
		this.row = row;
	}

	public DataSet getDataSet() {
		return dataSet;
	}

	public void setDataSet(DataSet dataSet) {
		this.dataSet = dataSet;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public DataSetFilter getDataSetFilter() {
		return dataSetFilter;
	}

	public void setDataSetFilter(DataSetFilter dataSetFilter) {
		this.dataSetFilter = dataSetFilter;
	}

	public List<Country> getCountryList() {
		return countryList;
	}

	public void setCountryList(List<Country> countryList) {
		this.countryList = countryList;
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

	public CSWSearchResult getCswSearchResult() {
		return cswSearchResult;
	}

	public void setCswSearchResult(CSWSearchResult cswSearchResult) {
		this.cswSearchResult = cswSearchResult;
	}

	public CSWRecord getCswRow() {
		return cswRow;
	}

	public void setCswRow(CSWRecord cswRow) {
		this.cswRow = cswRow;
	}
	
	

	
	
}

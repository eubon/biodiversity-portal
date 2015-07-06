package com.ibm.gbs.tramitator.jsf.mbean.species;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import com.ibm.gbs.ebp.core.exception.ConnectionException;
import com.ibm.gbs.ebp.core.exception.NotFoundException;
import com.ibm.gbs.eubon.beans.GeoPosition;
import com.ibm.gbs.eubon.ebp.core.jpa.entity.Country;
import com.ibm.gbs.eubon.ebp.core.jpa.entity.Provider;
import com.ibm.gbs.eubon.filters.OcurrencesFilter;
import com.ibm.gbs.eubon.filters.SpeciesFilter;
import com.ibm.gbs.gbif.client.GbifClient;
import com.ibm.gbs.gbif.client.data.DataSet;
import com.ibm.gbs.gbif.client.data.OcurrenceRow;
import com.ibm.gbs.gbif.client.data.OcurrencesResult;
import com.ibm.gbs.gbif.client.data.Organization;
import com.ibm.gbs.gbif.client.data.SpecieRow;
import com.ibm.gbs.gbif.client.data.SpeciesResult;
import com.ibm.gbs.tramitator.ejb.ServicioMetadatos;
import com.ibm.gbs.tramitator.ejb.crud.ServicioCRUD;
import com.ibm.gbs.tramitator.util.Constantes;
import com.ibm.gbs.tramitator.util.configuracion.Propiedades;

@ManagedBean(name = "speciesBean")
@SessionScoped
public class SpeciesBean implements Serializable {

	private static final long serialVersionUID = 708679843476943945L;

	Logger logger = Logger.getLogger("com.ibm.gbs");
	
	@EJB
	private ServicioMetadatos srvMetadatos;
	
	@EJB
	private ServicioCRUD crud;
	
	private GbifClient gbifClient;
    
	private SpeciesFilter speciesFilter;    
    
    private SpeciesResult speciesResult;
    
    private SpecieRow row;

	private DataSet dataSet;
    
    
	public SpeciesBean(ServicioMetadatos srvMetadatos) {
		super();
		this.srvMetadatos = srvMetadatos;
	}

	public SpeciesBean() 
	{
		//initProxy();
	}

	@PostConstruct
	public void create() {
	
	}	


	public String startSearch() 
	{	
		speciesFilter = new SpeciesFilter();
		
		speciesResult = new SpeciesResult();	
		
		return "speciesSearch";
	}
	

	public String speciesSearch() 
	{		
        logger.debug("SpeciesBean.speciesSearch(): speciesFilter=" + speciesFilter + "-");

        try {
        	
        	long gbifId = Long.parseLong(Propiedades.getPropiedad(Constantes.CFG_GBIFN_ID));
            Provider prov = (Provider)crud.obtenerObjeto(Provider.class, gbifId);
             
        	gbifClient = new GbifClient(prov.getServiceUrl());
        	
        	if (speciesFilter.getLimit() == 0)
        	{
        		String limite = Propiedades.getPropiedad(Constantes.CFG_DEFAULT_SPECIES_LIMIT);
        		long lm = 100;
        		try {
        			lm = Long.parseLong(limite);	
				} catch (Exception e) {
					
				}
        		speciesFilter.setLimit(lm);
        	}
        	

        	speciesResult = gbifClient.speciesSearch(speciesFilter);
        	
			if (speciesResult == null )
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
        
        logger.debug("SpeciesBean.speciesSearch(): -");


		return "speciesResult";
	}
	
	public String speciesSearchNext() 
	{		
        logger.debug("speciesBean.speciesSearchNext(): speciesFilter=" + speciesFilter + "-");    

        if (speciesResult.isEndOfRecords())
        {
        	FacesMessage message = new FacesMessage( "End of Records.", "End of Records.");
			message.setSeverity( FacesMessage.SEVERITY_ERROR);
			
			FacesContext.getCurrentInstance().addMessage(null, message);
			return Constantes.cadena_vacia;
        	
        }
        
        speciesFilter.setOffset(speciesResult.getOffset() + speciesResult.getLimit());
        
        return speciesSearch();
        
   	}
	
	
	public String speciesSearchByName() 
	{		
        logger.debug("speciesBean.speciesSearchByName(): speciesFilter=" + speciesFilter + "-");    

        String keyStr = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("key");

        if (keyStr == null || keyStr.equals(Constantes.cadena_vacia))
        {
        	FacesMessage message = new FacesMessage( "Not Found.", "Not Found.");
			message.setSeverity( FacesMessage.SEVERITY_ERROR);
			
			FacesContext.getCurrentInstance().addMessage(null, message);
			return Constantes.cadena_vacia;
        	
        }
        
        speciesFilter = new SpeciesFilter();
        speciesFilter.setName(keyStr.toLowerCase());
        
        return speciesSearch();
        
   	}
	
	public String speciesSearchPrev() 
	{		
        logger.debug("speciesBean.speciesSearchPrev(): speciesFilter=" + speciesFilter + "-");    

        if (speciesResult.getOffset() == 0)
        {
        	FacesMessage message = new FacesMessage( "End of Records.", "End of Records.");
			message.setSeverity( FacesMessage.SEVERITY_ERROR);
			
			FacesContext.getCurrentInstance().addMessage(null, message);
			return Constantes.cadena_vacia;
        	
        }
        
        speciesFilter.setOffset(speciesResult.getOffset() - speciesResult.getLimit());
        
        if (speciesFilter.getOffset() < 0)
        {
        	speciesFilter.setOffset(0);
        }
        
        return speciesSearch();
        
   	}


	public String consultSpeciesRow() 
	{
		
        logger.debug("SpeciesBean.consultSpeciesRow(): -");
        
        row = null;
        
        String keyStr = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("key");
         
        if (speciesResult == null)
        {
			FacesMessage message = new FacesMessage("Not Found.", "Not Found.");
			message.setSeverity( FacesMessage.SEVERITY_ERROR);
			
			FacesContext.getCurrentInstance().addMessage(null, message);
			return Constantes.cadena_vacia;
        }
        else
        {
        	for (Iterator iterator = speciesResult.getSpecies().iterator(); iterator.hasNext();) {
				SpecieRow ocurow = (SpecieRow) iterator.next();
				
				if (ocurow.getKey().equals(keyStr))
				{
					row = ocurow;
					break;
				}		
			}
        }
        
        logger.debug("SpeciesBean.getSpeciesRow(): -");

        
		return "speciesDetail";
	}
	
	public String backToSpeciesResult()
	{
		return "speciesResult";
	}

	public String backToSpeciesDetail()
	{
		return "speciesDetail";
	}	
		
	
	public String consultDataSet() 
	{
		
        logger.debug("SpeciesBean.consultDataSet(): -");
         
		String key = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("key");
				 
		logger.debug("SpeciesBean.consultDataSet(): - key=" + key + "-" );
        
        try {
        
        	long gbifId = Long.parseLong(Propiedades.getPropiedad(Constantes.CFG_GBIFN_ID));
            Provider prov = (Provider)crud.obtenerObjeto(Provider.class, gbifId);
             
        	gbifClient = new GbifClient(prov.getServiceUrl());
        	
        	
        	dataSet = gbifClient.getDataSet(key);
        	
			if (dataSet == null )
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
        
        logger.debug("SpeciesBean.consultDataSet(): -");

        
		return "speciesDataSetDetail";
	}


	public DataSet getDataSet() {
		return dataSet;
	}

	public void setDataSet(DataSet dataSet) {
		this.dataSet = dataSet;
	}

	public SpeciesFilter getSpeciesFilter() {
		return speciesFilter;
	}

	public void setSpeciesFilter(SpeciesFilter speciesFilter) {
		this.speciesFilter = speciesFilter;
	}

	public SpeciesResult getSpeciesResult() {
		return speciesResult;
	}

	public void setSpeciesResult(SpeciesResult speciesResult) {
		this.speciesResult = speciesResult;
	}

	public SpecieRow getRow() {
		return row;
	}

	public void setRow(SpecieRow row) {
		this.row = row;
	}

	
}

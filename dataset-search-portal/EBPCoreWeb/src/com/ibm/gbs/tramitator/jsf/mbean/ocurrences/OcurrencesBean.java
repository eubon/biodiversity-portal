package com.ibm.gbs.tramitator.jsf.mbean.ocurrences;

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
import com.ibm.gbs.tramitator.ejb.ServicioMetadatos;
import com.ibm.gbs.tramitator.ejb.crud.ServicioCRUD;
import com.ibm.gbs.tramitator.util.Constantes;
import com.ibm.gbs.tramitator.util.configuracion.Propiedades;

@ManagedBean(name = "ocurrencesBean")
@SessionScoped
public class OcurrencesBean implements Serializable {

	private static final long serialVersionUID = 708679843476943945L;

	Logger logger = Logger.getLogger("com.ibm.gbs");
	
	@EJB
	private ServicioMetadatos srvMetadatos;
	
	@EJB
	private ServicioCRUD crud;
	
	private GbifClient gbifClient;
    
	private OcurrencesFilter ocurrencesFilter;    
    
    private OcurrencesResult ocurrencesResult;
    
    private OcurrenceRow row;
    
    private Organization organization;
    
    private List<Country> countryList;
    
	private DataSet dataSet;
    
	private long kingdom;
    
	public OcurrencesBean(ServicioMetadatos srvMetadatos) {
		super();
		this.srvMetadatos = srvMetadatos;
	}

	public OcurrencesBean() 
	{
		//initProxy();
	}

	@PostConstruct
	public void create() {
	
	}
	
	public String getMarkers()
	{
		StringBuffer sb = new StringBuffer();
		
		int i = 1;
		for (Iterator iterator = ocurrencesResult.getOcurrences().iterator(); iterator.hasNext();) {
			OcurrenceRow row = (OcurrenceRow) iterator.next();
			
			sb.append("var marker");
			sb.append(i);
			sb.append("= new google.maps.Marker({position: new google.maps.LatLng(");
			sb.append(row.getDecimalLatitude());
			sb.append(",");
			sb.append(row.getDecimalLongitude());
			sb.append("), map: map, title: ");
			sb.append(row.getKey());
			sb.append("});");
			sb.append("\n");
			i++;
		}
		String m = sb.toString();
		//logger.debug("OcurrencesBean.createMarkers(): markers=" + m + "-");
		return m;
	}
	
	public String getLocations()
	{
		StringBuffer sb = new StringBuffer();
		
		for (Iterator iterator = ocurrencesResult.getOcurrences().iterator(); iterator.hasNext();) 
		{
			OcurrenceRow row = (OcurrenceRow) iterator.next();
			
			if (!(row.getDecimalLatitude() == 0.0) || !(row.getDecimalLongitude() == 0.0))
			{
			
//				logger.info("OcurrencesBean.getLocations(): row.getKey=" + row.getKey() + "-");
//				if (row.getKey().equals("931140725"))
//				{
//					logger.info("OcurrencesBean.getLocations(): row.getKey=" + row.getKey() + "-");					
//				}
				
				sb.append("['");
				sb.append(row.getKey());
				sb.append(" - ");
				if (row.getTaxon() != null && row.getTaxon().getScientificName() != null)
				{
					sb.append(row.getTaxon().getScientificName().replace("'", "-"));
				}
				sb.append("', ");
				sb.append(row.getDecimalLatitude());
				sb.append(", ");
				sb.append(row.getDecimalLongitude());
				sb.append("]");
				if (iterator.hasNext())
				{
					sb.append(",");
				}
				
				sb.append("\n");
			}
		}
		String m = sb.toString();
		//logger.debug("OcurrencesBean.getLocations(): locations=" + m + "-");
		return m;
	}
	
	public String getFilterGeometry()
	{
		String res = "";
		
		 long gbifId = Long.parseLong(Propiedades.getPropiedad(Constantes.CFG_GBIFN_ID));
	        Provider prov = (Provider)crud.obtenerObjeto(Provider.class, gbifId);
	        
		gbifClient = new GbifClient(prov.getServiceUrl());
		
		res = gbifClient.getFilterGeometry(ocurrencesFilter);		
		
		return res;
	}
	
	public String getLatitude()
	{
		String res = "0.0";
		
		if (ocurrencesResult != null && ocurrencesResult.getOcurrences() != null && !ocurrencesResult.getOcurrences().isEmpty())
		{
			for (Iterator iterator = ocurrencesResult.getOcurrences().iterator(); iterator.hasNext();) 
			{
				OcurrenceRow row = (OcurrenceRow) iterator.next();
				
				if (!(row.getDecimalLatitude() == 0.0))
				{
					res = new Double(row.getDecimalLatitude()).toString();
					break;
				}
			}
		}
		
		return res;
	}

	public String getLongitude()
	{
		String res = "0.0";
		
		if (ocurrencesResult != null && ocurrencesResult.getOcurrences() != null && !ocurrencesResult.getOcurrences().isEmpty())
		{
			for (Iterator iterator = ocurrencesResult.getOcurrences().iterator(); iterator.hasNext();) 
			{
				OcurrenceRow row = (OcurrenceRow) iterator.next();
				
				if (!(row.getDecimalLatitude() == 0.0))
				{
					res = new Double(row.getDecimalLongitude()).toString();
					break;
				}
			}			
		}
		
		return res;
	}

	public String startSearch() {
	
		ocurrencesFilter = new OcurrencesFilter();
		
		ocurrencesResult = new OcurrencesResult();
		
		GeoPosition point = new GeoPosition();
		point.setLatitude(0);
		point.setLongitude(0);
		ocurrencesFilter.setPoint(point);
		
		
		GeoPosition pos1 = new GeoPosition();
		pos1.setLatitude(00);
		pos1.setLongitude(0);
		ocurrencesFilter.setPos1(pos1);
		GeoPosition pos2 = new GeoPosition();
		pos2.setLatitude(0);
		pos2.setLongitude(0);
		ocurrencesFilter.setPos2(pos2);
		GeoPosition pos3 = new GeoPosition();
		pos3.setLatitude(0);
		pos3.setLongitude(0);
		ocurrencesFilter.setPos3(pos3);
		GeoPosition pos4 = new GeoPosition();
		pos4.setLatitude(0);
		pos4.setLongitude(0);
		ocurrencesFilter.setPos4(pos4);
		
		countryList = crud.listadoOrdenado(Country.class, " NAME " );
		
		return "ocurrencesSearch";
	}
	

	public String ocurrencesSearch() 
	{		
        logger.debug("OcurrencesBean.ocurrencesSearch(): ocurrencesFilter=" + ocurrencesFilter + "-");

        try {
        	
        	 long gbifId = Long.parseLong(Propiedades.getPropiedad(Constantes.CFG_GBIFN_ID));
             Provider prov = (Provider)crud.obtenerObjeto(Provider.class, gbifId);
        	
        	gbifClient = new GbifClient(prov.getServiceUrl());
        	
        	if (ocurrencesFilter.getLimit() == 0)
        	{
        		String limite = Propiedades.getPropiedad(Constantes.CFG_DEFAULT_OCURRENCES_LIMIT);
        		long lm = 100;
        		try {
        			lm = Long.parseLong(limite);	
				} catch (Exception e) {
					
				}
        		ocurrencesFilter.setLimit(lm);
        	}
        	
        	if (ocurrencesFilter.getTaxonKey().equals(Constantes.cadena_vacia))
        	{
        		if (kingdom > 0)
        		{
        			ocurrencesFilter.setTaxonKey(Long.toString(kingdom));
        		}
        	}

        	ocurrencesResult = gbifClient.ocurrenceSearch(ocurrencesFilter);
        	
			if (ocurrencesResult == null )
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
        
        logger.debug("OcurrencesBean.ocurrencesSearch(): -");


		return "ocurrencesResult";
	}
	
	public String ocurrencesSearchNext() 
	{		
        logger.debug("OcurrencesBean.ocurrencesSearchNext(): ocurrencesFilter=" + ocurrencesFilter + "-");    

        if (ocurrencesResult.isEndOfRecords())
        {
        	FacesMessage message = new FacesMessage( "End of Records.", "End of Records.");
			message.setSeverity( FacesMessage.SEVERITY_ERROR);
			
			FacesContext.getCurrentInstance().addMessage(null, message);
			return Constantes.cadena_vacia;
        	
        }
        
        ocurrencesFilter.setOffset(ocurrencesResult.getOffset() + ocurrencesResult.getLimit());
        
        return ocurrencesSearch();
        
   	}
	
	public String ocurrencesSearchByKey() 
	{		
        logger.debug("OcurrencesBean.ocurrencesSearchByKey(): ocurrencesFilter=" + ocurrencesFilter + "-");    

        String keyStr = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("key");

        if (keyStr == null || keyStr.equals(Constantes.cadena_vacia))
        {
        	FacesMessage message = new FacesMessage( "Not Found.", "Not Found.");
			message.setSeverity( FacesMessage.SEVERITY_ERROR);
			
			FacesContext.getCurrentInstance().addMessage(null, message);
			return Constantes.cadena_vacia;
        	
        }
        
        ocurrencesFilter = new OcurrencesFilter();
        ocurrencesFilter.setTaxonKey(keyStr);
        
        ocurrencesResult = new OcurrencesResult();
		
		GeoPosition point = new GeoPosition();
		point.setLatitude(0);
		point.setLongitude(0);
		ocurrencesFilter.setPoint(point);
		
		
		GeoPosition pos1 = new GeoPosition();
		pos1.setLatitude(00);
		pos1.setLongitude(0);
		ocurrencesFilter.setPos1(pos1);
		GeoPosition pos2 = new GeoPosition();
		pos2.setLatitude(0);
		pos2.setLongitude(0);
		ocurrencesFilter.setPos2(pos2);
		GeoPosition pos3 = new GeoPosition();
		pos3.setLatitude(0);
		pos3.setLongitude(0);
		ocurrencesFilter.setPos3(pos3);
		GeoPosition pos4 = new GeoPosition();
		pos4.setLatitude(0);
		pos4.setLongitude(0);
		ocurrencesFilter.setPos4(pos4);
		
        
        return ocurrencesSearch();
        
   	}
	
	public String ocurrencesSearchPrev() 
	{		
        logger.debug("OcurrencesBean.ocurrencesSearchPrev(): ocurrencesFilter=" + ocurrencesFilter + "-");    

        if (ocurrencesResult.getOffset() == 0)
        {
        	FacesMessage message = new FacesMessage( "End of Records.", "End of Records.");
			message.setSeverity( FacesMessage.SEVERITY_ERROR);
			
			FacesContext.getCurrentInstance().addMessage(null, message);
			return Constantes.cadena_vacia;
        	
        }
        
        ocurrencesFilter.setOffset(ocurrencesResult.getOffset() - ocurrencesResult.getLimit());
        
        if (ocurrencesFilter.getOffset() < 0)
        {
        	ocurrencesFilter.setOffset(0);
        }
        
        return ocurrencesSearch();
        
   	}


	public String consultOcurrencesRow() 
	{
		
        logger.debug("OcurrencesBean.consultOcurrencesRow(): -");
        
        row = null;
        
        String keyStr = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("key");
         
        if (ocurrencesResult == null)
        {
			FacesMessage message = new FacesMessage("Not Found.", "Not Found.");
			message.setSeverity( FacesMessage.SEVERITY_ERROR);
			
			FacesContext.getCurrentInstance().addMessage(null, message);
			return Constantes.cadena_vacia;
        }
        else
        {
        	for (Iterator iterator = ocurrencesResult.getOcurrences().iterator(); iterator.hasNext();) {
				OcurrenceRow ocurow = (OcurrenceRow) iterator.next();
				
				if (ocurow.getKey().equals(keyStr))
				{
					row = ocurow;
					break;
				}		
			}
        	
        	
			if (row != null)
			{
			   String org = row.getPublishingOrganizationKey();
			   try {
				organization = gbifClient.getOrganization(org);
				row.setPublishingOrganizationTitle(organization.getTitle());
			} catch (NotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ConnectionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
			
        }
        
        logger.debug("OcurrencesBean.getOcurrencesRow(): -");

        
		return "ocurrencesDetail";
	}
	
	public String backToOcurrencesResult()
	{
		return "ocurrencesResult";
	}

	public String backToOcurrencesDetail()
	{
		return "ocurrencesDetail";
	}

	
	
	public String consultOrganization() 
	{
		
        logger.debug("OcurrencesBean.consultOrganization(): -");
         
		String key = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("key");
				 
		logger.debug("OcurrencesBean.consultOrganization(): - key=" + key + "-" );
        
        try {
        	 long gbifId = Long.parseLong(Propiedades.getPropiedad(Constantes.CFG_GBIFN_ID));
             Provider prov = (Provider)crud.obtenerObjeto(Provider.class, gbifId);
             
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
        
        logger.debug("OcurrencesBean.consultOrganization(): -");

        
		return "ocurrencesOrganizationDetail";
	}
	
	
	public String consultDataSet() 
	{
		
        logger.debug("OcurrencesBean.consultDataSet(): -");
         
		String key = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("key");
				 
		logger.debug("OcurrencesBean.consultDataSet(): - key=" + key + "-" );
        
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
        
        logger.debug("OcurrencesBean.consultDataSet(): -");

        
		return "ocurrencesDataSetDetail";
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public List<Country> getCountryList() {
		return countryList;
	}

	public void setCountryList(List<Country> countryList) {
		this.countryList = countryList;
	}

	public OcurrencesFilter getOcurrencesFilter() {
		return ocurrencesFilter;
	}

	public void setOcurrencesFilter(OcurrencesFilter ocurrencesFilter) {
		this.ocurrencesFilter = ocurrencesFilter;
	}

	public OcurrencesResult getOcurrencesResult() {
		return ocurrencesResult;
	}

	public void setOcurrencesResult(OcurrencesResult ocurrencesResult) {
		this.ocurrencesResult = ocurrencesResult;
	}

	public OcurrenceRow getRow() {
		return row;
	}

	public void setRow(OcurrenceRow row) {
		this.row = row;
	}

	public DataSet getDataSet() {
		return dataSet;
	}

	public void setDataSet(DataSet dataSet) {
		this.dataSet = dataSet;
	}

	public long getKingdom() {
		return kingdom;
	}
	
	public String getKingdomString() 
	{
		String kingdomString = "";
		
		int k = (int)kingdom;
        
		switch (k) {
            case 0:  kingdomString = "Any";
                     break;
            case 1:  kingdomString = "Animalia";
                     break;
            case 5:  kingdomString = "Fungi";
                     break;
            case 6:  kingdomString = "Plantae";
                     break;
        }
		
		return kingdomString;
	}

	public void setKingdom(long kingdom) {
		this.kingdom = kingdom;
	}
	
	

	
	
}

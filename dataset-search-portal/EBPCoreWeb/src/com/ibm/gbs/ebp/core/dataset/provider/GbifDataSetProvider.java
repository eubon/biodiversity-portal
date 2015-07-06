package com.ibm.gbs.ebp.core.dataset.provider;

import org.apache.log4j.Logger;

import com.ibm.gbs.ebp.core.exception.ConnectionException;
import com.ibm.gbs.ebp.core.exception.NotFoundException;
import com.ibm.gbs.eubon.ebp.core.jpa.entity.Provider;
import com.ibm.gbs.eubon.filters.DataSetFilter;
import com.ibm.gbs.gbif.client.GbifClient;
import com.ibm.gbs.gbif.client.data.GbifSearchResult;
import com.ibm.gbs.tramitator.util.Constantes;
import com.ibm.gbs.tramitator.util.configuracion.Propiedades;


public class GbifDataSetProvider 
{

    Logger logger = Logger.getLogger("com.ibm.gbs");
    
	private Provider provider;
    
	public GbifDataSetProvider(Provider prov)
	{
		provider = prov;
	
	}

	
	public Provider getProvider() {
		
		return provider;
	}

	
	public GbifSearchResult getDataSets(DataSetFilter dataSetFilter) throws NotFoundException, ConnectionException 
	{        	
        	GbifClient gbifClient = new GbifClient(provider.getServiceUrl());
        	
        	if (dataSetFilter.getLimit() == 0)
        	{
        		String limite = Propiedades.getPropiedad(Constantes.CFG_DEFAULT_DATASET_LIMIT);
        		long lm = 100;
        		try {
        			lm = Long.parseLong(limite);	
				} catch (Exception e) {
					lm = 100;
				}
        		dataSetFilter.setLimit(lm);
        	}
        	        	
        	GbifSearchResult searchResult = gbifClient.registrySearch(dataSetFilter);
		return searchResult;
	}
	
}

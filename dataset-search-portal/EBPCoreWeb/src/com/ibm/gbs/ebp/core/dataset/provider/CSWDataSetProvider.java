package com.ibm.gbs.ebp.core.dataset.provider;

import org.apache.log4j.Logger;

import com.ibm.gbs.csw.client.CSWClient;
import com.ibm.gbs.csw.client.data.CSWSearchResult;
import com.ibm.gbs.ebp.core.exception.ConnectionException;
import com.ibm.gbs.ebp.core.exception.NotFoundException;
import com.ibm.gbs.eubon.ebp.core.jpa.entity.Provider;
import com.ibm.gbs.eubon.filters.DataSetFilter;
import com.ibm.gbs.gbif.client.GbifClient;
import com.ibm.gbs.gbif.client.data.GbifSearchResult;
import com.ibm.gbs.tramitator.util.Constantes;
import com.ibm.gbs.tramitator.util.configuracion.Propiedades;


public class CSWDataSetProvider 
{

    Logger logger = Logger.getLogger("com.ibm.gbs");
    
	private Provider provider;
    
	public CSWDataSetProvider(Provider prov)
	{
		provider = prov;
	
	}


	public Provider getProvider() {
		
		return provider;
	}

	
	public CSWSearchResult getDataSets(DataSetFilter dataSetFilter) throws NotFoundException, ConnectionException 
	{        	
        	CSWClient cswClient = new CSWClient(provider.getServiceUrl());
        	
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
        	        	
        	CSWSearchResult cswSearchResult = cswClient.registrySearch(dataSetFilter);
        	logger.info("CSWDAtaSetProvider.getDataSets(): cswSearchResult=" + cswSearchResult + "-");
		return cswSearchResult;
	}
	
}

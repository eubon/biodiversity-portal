package com.ibm.gbs.csw.client;

import org.apache.log4j.Logger;

import com.ibm.gbs.csw.client.data.CSWSearchResult;
import com.ibm.gbs.ebp.core.exception.ConnectionException;
import com.ibm.gbs.ebp.core.exception.NotFoundException;
import com.ibm.gbs.eubon.filters.DataSetFilter;
import com.ibm.gbs.gbif.client.GbifParser;
import com.ibm.gbs.gbif.client.data.DataSet;
import com.ibm.gbs.gbif.client.data.GbifSearchResult;
import com.ibm.gbs.rest.RestClient;
import com.ibm.gbs.tramitator.util.Constantes;

public class CSWClient {
	
	public CSWClient(String url) {
		super();
		this.cswUrl= url;
	}
	
	Logger logger = Logger.getLogger("com.ibm.gbs");
	
	private String cswUrl = "";

	
	private String doSearch(DataSetFilter filter) throws ConnectionException, NotFoundException
	{
		
		String res = "";
		
		String paramBusqueda = getParametrosBusqueda(filter).replace(" ", "%20");

		
		String uri = cswUrl + "geonetwork/srv/eng/csw?service=CSW&request=GetRecords&version=2.0.2&typenames=csw:Record&constraintlanguage=CQL_TEXT&constraint_language_version=1.1.0&resulttype=results&elementsetname=full"
							+ paramBusqueda; 

		logger.info("CSWClient.doSearch(): uri=" + uri + "-");
		
		RestClient restClient = new RestClient();
		
		res = restClient.doGet(uri);
		
		logger.info("CSWClient.doSearch(): Response csw=" + res + "-");
		
		return res;
		
	}

	private String getParametrosBusqueda(DataSetFilter filter) 
	{
		String res = "";
		int i = 0;
		if (filter.getQ() != null && !filter.getQ().equals(Constantes.cadena_vacia))
		{
			res = res + "constraint=csw:AnyText+like+%27%25" + filter.getQ() + "%25%27" ;
			i++;
		}
		
		if (filter.getPublishingCountry() != null && !filter.getPublishingCountry().equals(Constantes.cadena_vacia))
		{
			if (i>0)
			{
				res = res + "&";
			}
			i++;
			res = res + "publishingCountry=" + filter.getPublishingCountry();
		}
		
		if (filter.getContinent() != null && !filter.getContinent().equals(Constantes.cadena_vacia))
		{
			if (i>0)
			{
				res = res + "&";
			}
			i++;
			res = res + "continent=" + filter.getContinent();
		}
		
		if (filter.getCountry() != null && !filter.getCountry().equals(Constantes.cadena_vacia))
		{
			if (i>0)
			{
				res = res + "&";
			}
			i++;
			res = res + "country=" + filter.getCountry();
		}

		if (filter.getDecade() != null && !filter.getDecade().equals(Constantes.cadena_vacia))
		{
			if (i>0)
			{
				res = res + "&";
			}
			i++;
			res = res + "decade=" + filter.getDecade();
		}

		if (filter.getHostingOrg() != null && !filter.getHostingOrg().equals(Constantes.cadena_vacia))
		{
			if (i>0)
			{
				res = res + "&";
			}
			i++;
			res = res + "hostingOrg=" + filter.getHostingOrg();
		}
		
		if (filter.getKeyword() != null && !filter.getKeyword().equals(Constantes.cadena_vacia))
		{
			if (i>0)
			{
				res = res + "&";
			}
			i++;
			res = res + "keyword=" + filter.getKeyword();
		}

		if (filter.getPublishingOrg() != null && !filter.getPublishingOrg().equals(Constantes.cadena_vacia))
		{
			if (i>0)
			{
				res = res + "&";
			}
			i++;
			res = res + "publishingOrg=" + filter.getPublishingOrg();
		}

		
		if (filter.getLimit() > 0)
		{
			if (i>0)
			{
				res = res + "&";
			}
			i++;
			res = res + "maxRecords=" + filter.getLimit();
		}
		
		if (filter.getOffset() > 0)
		{
			if (i>0)
			{
				res = res + "&";
			}
			i++;
			res = res + "offset=" + filter.getOffset();
		}

		
		if (!res.equals(Constantes.cadena_vacia))
		{
			res = "&" + res;
		}

		return res;
	}
	
	public CSWSearchResult registrySearch(DataSetFilter filter) throws NotFoundException, ConnectionException
	{
		CSWSearchResult sr = null;
		try {
		
		   String respuesta = doSearch(filter);
		
		   CSWParser cp = new CSWParser();
		
			sr = cp.parseSearchResult(respuesta);
		}
		catch (ConnectionException e) {
			e.printStackTrace();
			throw new ConnectionException();
		} 
		catch (Exception e) {
			e.printStackTrace();
			throw new NotFoundException();
		}
		
		
		return sr;
	}
}

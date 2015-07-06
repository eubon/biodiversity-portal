package com.ibm.gbs.gbif.client;

import org.apache.log4j.Logger;

import com.ibm.gbs.ebp.core.exception.ConnectionException;
import com.ibm.gbs.ebp.core.exception.NotFoundException;
import com.ibm.gbs.eubon.filters.DataSetFilter;
import com.ibm.gbs.eubon.filters.OcurrencesFilter;
import com.ibm.gbs.eubon.filters.SpeciesFilter;
import com.ibm.gbs.gbif.client.data.DataSet;
import com.ibm.gbs.gbif.client.data.OcurrencesResult;
import com.ibm.gbs.gbif.client.data.Organization;
import com.ibm.gbs.gbif.client.data.GbifSearchResult;
import com.ibm.gbs.gbif.client.data.SpeciesResult;
import com.ibm.gbs.rest.RestClient;
import com.ibm.gbs.tramitator.util.Constantes;
import com.ibm.gbs.tramitator.util.configuracion.Propiedades;

public class GbifClient 
{
	
	public GbifClient(String url) {
		super();
		this.gbifUrl= url;
	}

	Logger logger = Logger.getLogger("com.ibm.gbs");
	
	private String gbifUrl = "";
	
	public GbifSearchResult registrySearch(DataSetFilter filter) throws NotFoundException, ConnectionException
	{
		GbifSearchResult sr = null;
		try {
		
		   String respuestaGbif = doSearch(filter);
		
		   GbifParser gp = new GbifParser();
		
			sr = gp.parseSearchResult(respuestaGbif);
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
	
	public OcurrencesResult ocurrenceSearch(OcurrencesFilter filter) throws NotFoundException, ConnectionException
	{
		OcurrencesResult or = null;
		try {
		
		   String respuestaGbif = doSearchOcurrences(filter);
		
		   GbifParser gp = new GbifParser();
		
			or = gp.parseOcurrencesResult(respuestaGbif);
		}
		catch (ConnectionException e) {
			e.printStackTrace();
			throw new ConnectionException();
		} 
		catch (Exception e) {
			e.printStackTrace();
			throw new NotFoundException();
		}
		
		
		return or;
	}
	
	public SpeciesResult speciesSearch(SpeciesFilter filter) throws NotFoundException, ConnectionException
	{
		SpeciesResult or = null;
		try {
		
		   String respuestaGbif = doSearchSpecies(filter);
		
		   GbifParser gp = new GbifParser();
		
			or = gp.parseSpeciesResult(respuestaGbif);
		}
		catch (ConnectionException e) {
			e.printStackTrace();
			throw new ConnectionException();
		} 
		catch (Exception e) {
			e.printStackTrace();
			throw new NotFoundException();
		}
		
		
		return or;
	}
	
	private String doSearch(DataSetFilter filter) throws ConnectionException, NotFoundException
	{
		
		String res = "";
		
		String paramBusqueda = getParametrosBusqueda(filter);
		
		String search = Propiedades.getPropiedad(Constantes.CFG_GBIF_DATASET_SEARCH);
		
		String uri = gbifUrl + search + paramBusqueda; 

		logger.info("GbifClient.doSearch(): uri=" + uri + "-");
		
		RestClient restClient = new RestClient();
		
		res = restClient.doGet(uri);
		
		logger.info("GbifClient.doSearch(): Response Gbif=" + res + "-");
		
		return res;
		
	}
	
	private String doSearchOcurrences(OcurrencesFilter filter) throws ConnectionException, NotFoundException
	{
		
		String res = "";
		
		String paramBusqueda = getParametrosBusqueda(filter);
		
		String search = Propiedades.getPropiedad(Constantes.CFG_GBIF_OCCURRENCE_SEARCH);
		
		String uri = gbifUrl + search + paramBusqueda;  

		logger.info("GbifClient.doSearchOcurrences(): uri=" + uri + "-");
		
		RestClient restClient = new RestClient();

		res = restClient.doGet(uri);
		
		logger.info("GbifClient.doSearchOcurrences(): response Gbif=" + res + "-");
		
		return res;
		
	}
	
	private String doSearchSpecies(SpeciesFilter filter) throws ConnectionException, NotFoundException
	{
		
		String res = "";
		
		String paramBusqueda = getParametrosBusqueda(filter);
		
		String search = Propiedades.getPropiedad(Constantes.CFG_GBIF_SPECIES_SEARCH);
		
		String uri = gbifUrl + search + paramBusqueda; 

		logger.info("GbifClient.doSearchSpecies(): uri=" + uri + "-");
		
		RestClient restClient = new RestClient();

		res = restClient.doGet(uri);
		
		logger.info("GbifClient.doSearchSpecies(): response Gbif=" + res + "-");
		
		return res;
		
	}
	

	private String getParametrosBusqueda(DataSetFilter filter) 
	{
		String res = "";
		int i = 0;
		if (filter.getQ() != null && !filter.getQ().equals(Constantes.cadena_vacia))
		{
			res = res + "q=" + filter.getQ() ;
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

		if (filter.getType() != null && !filter.getType().equals(Constantes.cadena_vacia))
		{
			if (i>0)
			{
				res = res + "&";
			}
			i++;
			res = res + "type=" + filter.getType();
		}
		
		if (filter.getLimit() > 0)
		{
			if (i>0)
			{
				res = res + "&";
			}
			i++;
			res = res + "limit=" + filter.getLimit();
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
			res = "?" + res;
		}

		return res;
	}
	
	
	private String getParametrosBusqueda(SpeciesFilter filter) 
	{
		String res = "";
		int i = 0;
		if (filter.getName() != null && !filter.getName().equals(Constantes.cadena_vacia))
		{
			res = res + "name=" + filter.getName() ;
			i++;
		}
		
		if (filter.getLimit() > 0)
		{
			if (i>0)
			{
				res = res + "&";
			}
			i++;
			res = res + "limit=" + filter.getLimit();
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
			res = "?" + res;
		}

		return res;
	}
	
	private String getParametrosBusqueda(OcurrencesFilter filter) 
	{
		String res = "";
		int i = 0;
		if (filter.getCountry() != null && !filter.getCountry().equals(Constantes.cadena_vacia))
		{
			res = res + "country=" + filter.getCountry() ;
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
		
		if (filter.getYear() != null && !filter.getYear().equals(Constantes.cadena_vacia) && !filter.getYear().equals("Any"))
		{
			if (i>0)
			{
				res = res + "&";
			}
			i++;
			res = res + "year=" + filter.getYear();
		}
		
		if (filter.getTaxonKey() != null && !filter.getTaxonKey().equals(Constantes.cadena_vacia))
		{
			if (i>0)
			{
				res = res + "&";
			}
			i++;
			res = res + "taxonkey=" + filter.getTaxonKey();
		}
		
		if (filter.getLimit() > 0)
		{
			if (i>0)
			{
				res = res + "&";
			}
			i++;
			res = res + "limit=" + filter.getLimit();
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
		
		
		
		if (filter.getPoint() != null && filter.getPoint().getLatitude() != 0)
		{
			if (i>0)
			{
				res = res + "&";
			}
			i++;

			res = res + getFilterGeometry(filter);
		}
		else
			if (filter.getPos1() != null && filter.getPos1().getLatitude() != 0)
		{
			if (i>0)
			{
				res = res + "&";
			}
			i++;

			res = res + getFilterGeometry(filter);;		                 
		}
		
		

		if (!res.equals(Constantes.cadena_vacia))
		{
			res = "?" + res;
		}
		
		

		return res;
	}

	public String getFilterGeometry(OcurrencesFilter filter) 
	{
		String res = "";
		
		if (filter.getPoint().getLatitude() != 0)
		{
			res = res + "geometry=POINT(" +
		                 filter.getPoint().getLatitude() + " " + filter.getPoint().getLongitude() + ") ";		                 
		}
		else
			if (filter.getPos1().getLatitude() != 0)
		{
			res = res + "geometry=POLYGON((" +
		                 filter.getPos1().getLongitude() + " " + filter.getPos1().getLatitude() + ", " +
		                 filter.getPos2().getLongitude() + " " + filter.getPos2().getLatitude() + ", " +
		                 filter.getPos3().getLongitude() + " " + filter.getPos3().getLatitude() + ", " +
		                 filter.getPos4().getLongitude() + " " + filter.getPos4().getLatitude() + ", " +
		                 filter.getPos1().getLongitude() + " " + filter.getPos1().getLatitude() + ")) ";		                 
		}
		
		return res;

	}

	public DataSet getDataSet(String key) throws NotFoundException, ConnectionException
	{
		DataSet dataSet = null;
		
		try
		{
			String uri = gbifUrl + "dataset/" + key;
	
			RestClient restClient = new RestClient();
	
			String res = restClient.doGet(uri);
			
			GbifParser gp = new GbifParser();
			
			dataSet = gp.parseDataSet(res);
		}
		catch (ConnectionException e) {
			e.printStackTrace();
			throw new ConnectionException();
		} 
		catch (Exception e) {
			e.printStackTrace();
			throw new NotFoundException();
		}
		return dataSet;
	}

	public Organization getOrganization(String key) throws NotFoundException, ConnectionException 
	{
		Organization organization = null;
		
		try
		{
			String uri = gbifUrl + "organization/" + key; 
	
			RestClient restClient = new RestClient();
	
			String res = restClient.doGet(uri);
			
			GbifParser gp = new GbifParser();
			
			organization = gp.parseOrganization(res);
		}
		catch (ConnectionException e) {
			e.printStackTrace();
			throw new ConnectionException();
		} 
		catch (Exception e) {
			e.printStackTrace();
			throw new NotFoundException();
		}
		return organization;
	}

}

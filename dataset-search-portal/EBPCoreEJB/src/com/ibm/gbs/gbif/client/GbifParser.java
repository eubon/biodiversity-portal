package com.ibm.gbs.gbif.client;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.ibm.gbs.gbif.client.data.BoundingBox;
import com.ibm.gbs.gbif.client.data.Citation;
import com.ibm.gbs.gbif.client.data.Contact;
import com.ibm.gbs.gbif.client.data.Coverage;
import com.ibm.gbs.gbif.client.data.DataSet;
import com.ibm.gbs.gbif.client.data.DataSetRow;
import com.ibm.gbs.gbif.client.data.EndPoint;
import com.ibm.gbs.gbif.client.data.GeographicCoverage;
import com.ibm.gbs.gbif.client.data.Identifier;
import com.ibm.gbs.gbif.client.data.MachineTags;
import com.ibm.gbs.gbif.client.data.OcurrenceRow;
import com.ibm.gbs.gbif.client.data.OcurrencesResult;
import com.ibm.gbs.gbif.client.data.Organization;
import com.ibm.gbs.gbif.client.data.Rank;
import com.ibm.gbs.gbif.client.data.GbifSearchResult;
import com.ibm.gbs.gbif.client.data.SpecieRow;
import com.ibm.gbs.gbif.client.data.SpeciesResult;
import com.ibm.gbs.gbif.client.data.Tags;
import com.ibm.gbs.gbif.client.data.Taxon;
import com.ibm.gbs.gbif.client.data.TaxonomicCoverage;
import com.ibm.gbs.gbif.client.data.TemporalCoverage;
import com.ibm.gbs.tramitator.util.Constantes;

public class GbifParser 
{

	Logger logger = Logger.getLogger("com.ibm.gbs");
	
	public GbifSearchResult parseSearchResult(String jsonTxt) throws ParseException 
	{
		
		GbifSearchResult sR = new GbifSearchResult();
			
		JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(jsonTxt);
        
        Long offset = (Long)jsonObject.get("offset");

        sR.setOffset(offset);
        
        Long limit = (Long)jsonObject.get("limit"); 
        sR.setLimit(limit);
        
        boolean eor = (Boolean)jsonObject.get("endOfRecords");
        sR.setEndOfRecords(eor);
        
        Long count = (Long)jsonObject.get("count");
        sR.setCount(count);

		JSONArray jResults = (JSONArray)jsonObject.get("results");

        List<DataSetRow> dataSets = parseDataSetRows(jResults);
        
        sR.setDataSets(dataSets);
		
		
		return sR;
	}

	private List<DataSetRow> parseDataSetRows(JSONArray jResults) 
	{
		List<DataSetRow> list = new ArrayList<DataSetRow>();
		
		for (Iterator iterator = jResults.iterator(); iterator.hasNext();) {
			JSONObject fila = (JSONObject) iterator.next();
			
			DataSetRow row = parseDataSetRow(fila);
			
			list.add(row);
		}
		
		return list;
	}
	
	private List<OcurrenceRow> parseOcurrenceRows(JSONArray jResults) throws ParseException 
	{
		List<OcurrenceRow> list = new ArrayList<OcurrenceRow>();
		
		for (Iterator iterator = jResults.iterator(); iterator.hasNext();) {
			JSONObject fila = (JSONObject) iterator.next();
			
			OcurrenceRow row = parseOcurrenceRow(fila);
			
			list.add(row);
		}
		
		return list;
	}
	
	
	private List<SpecieRow> parseSpeciesRows(JSONArray jResults) throws ParseException 
	{
		List<SpecieRow> list = new ArrayList<SpecieRow>();
		
		for (Iterator iterator = jResults.iterator(); iterator.hasNext();) {
			JSONObject fila = (JSONObject) iterator.next();
			
			SpecieRow row = parseSpecieRow(fila);
			
			list.add(row);
		}
		
		return list;
	}

	private DataSetRow parseDataSetRow(JSONObject jsonObject) 
	{
		DataSetRow dsRow = new DataSetRow();
		
		String key = (String)jsonObject.get("key");
		dsRow.setKey(key);
		
		String title = (String)jsonObject.get("title");
		dsRow.setTitle(title);
		
		String description = (String)jsonObject.get("description");
		dsRow.setDescription(description);
		
		String type = (String)jsonObject.get("type");
		dsRow.setType(type);
		
		String hostingOrganizationKey = (String)jsonObject.get("hostingOrganizationKey");
		dsRow.setHostingOrganizationKey(hostingOrganizationKey);
		
		String hostingOrganizationTitle = (String)jsonObject.get("hostingOrganizationTitle");
		dsRow.setHostingOrganizationTitle(hostingOrganizationTitle);
		
		String publishingCountry = (String)jsonObject.get("publishingCountry");
		dsRow.setPublishingCountry(publishingCountry);
		
		String publishingOrganizationKey = (String)jsonObject.get("publishingOrganizationKey");
		dsRow.setPublishingOrganizationKey(publishingOrganizationKey);
		
		String publishingOrganizationTitle = (String)jsonObject.get("publishingOrganizationTitle");
		dsRow.setPublishingOrganizationTitle(publishingOrganizationTitle);
		

		return dsRow;
	}

	public DataSet parseDataSet(String jsonTxt) throws ParseException 
	{
		DataSet dataSet = new DataSet();
		
		JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(jsonTxt);
        
        String key = (String)jsonObject.get("key");
        dataSet.setKey(key);
        
        String installationKey = (String)jsonObject.get("installationKey");
        dataSet.setInstallationKey(installationKey);
        
        String publishingOrganizationKey = (String)jsonObject.get("publishingOrganizationKey");
        dataSet.setPublishingOrganizationKey(publishingOrganizationKey);
        
        boolean external = (Boolean)jsonObject.get("external");
        dataSet.setExternal(external);
        
        long numConstituents = (Long)jsonObject.get("numConstituents");
        dataSet.setNumConstituents(numConstituents);
        
        String type = (String)jsonObject.get("type");
        dataSet.setType(type);
        
        String title = (String)jsonObject.get("title");
        dataSet.setTitle(title);
        
        String description = (String)jsonObject.get("description");
        dataSet.setDescription(description);
        
        String language = (String)jsonObject.get("language");
        dataSet.setLanguage(language);
        
        Citation citation = parseCitation((JSONObject)jsonObject.get("citation"));
        dataSet.setCitation(citation);
        
        String rights = (String)jsonObject.get("rights");
        dataSet.setRights(rights);
        
        boolean lockedForAutoUpdate = (Boolean)jsonObject.get("lockedForAutoUpdate");
        dataSet.setLockedForAutoUpdate(lockedForAutoUpdate);
        
        String createdBy = (String)jsonObject.get("createdBy");
        dataSet.setCreatedBy(createdBy);
        
        String modifiedBy = (String)jsonObject.get("modifiedBy");
        dataSet.setModifiedBy(modifiedBy);
        
        Date created = parseDate((String)jsonObject.get("created"));
        dataSet.setCreated(created);        
        
        Date modified = parseDate((String)jsonObject.get("modified"));
        dataSet.setModified(modified);
        
        List<Contact> contacts = parseContatcs((JSONArray)jsonObject.get("contacts"));
        dataSet.setContacts(contacts);
        
        List<EndPoint> endpoints = parseEndpoints((JSONArray)jsonObject.get("endpoints"));
        dataSet.setEndpoints(endpoints);
        
//        List<MachineTags> machineTags = parseMachineTagss((JSONArray)jsonObject.get("machineTags"));
//        dataSet.setMachineTags(machineTags);
        
//        List<String> tags = parseStrings((JSONArray)jsonObject.get("tags"));        
//        dataSet.setTags(tags);
        
        List<Identifier> identifiers = parseIdentifiers((JSONArray)jsonObject.get("identifiers"));
        dataSet.setIdentifiers(identifiers);
        
        List<String> comments = parseStrings((JSONArray)jsonObject.get("comments"));        
        dataSet.setComments(comments);
       
        List<String> bibliographicCitations = parseBibliographicCitations((JSONArray)jsonObject.get("bibliographicCitations"));        
        dataSet.setBibliographicCitations(bibliographicCitations);
        
        List<String> curatorialUnits = parseStrings((JSONArray)jsonObject.get("curatorialUnits"));        
        dataSet.setCuratorialUnits(curatorialUnits);
        
        List<TaxonomicCoverage> taxonomicCoverage = parseTaxonomicCoverages((JSONArray)jsonObject.get("taxonomicCoverages"));
        dataSet.setTaxonomicCoverages(taxonomicCoverage);
        
        List<GeographicCoverage> geographicCoverages = parseGeographicCoverage((JSONArray)jsonObject.get("geographicCoverages"));
        dataSet.setGeographicCoverages(geographicCoverages);
        
        List<TemporalCoverage> temporalCoverages = parseTemporalCoverages((JSONArray)jsonObject.get("temporalCoverages"));
        dataSet.setTemporalCoverages(temporalCoverages);
        
        String dataLanguage = (String)jsonObject.get("dataLanguage");
        dataSet.setDataLanguage(dataLanguage);
        
        String additionalInfo = (String)jsonObject.get("additionalInfo");
        dataSet.setAdditionalInfo(additionalInfo);
        
        Date pubDate = parseDate((String)jsonObject.get("pubDate"));
        dataSet.setPubDate(pubDate);
        
        		
		return dataSet;
	}

	private List<String> parseBibliographicCitations(JSONArray jsonArray) 
	{
		List<String> lista = new ArrayList<String>();
		
		for (Iterator iterator = jsonArray.iterator(); iterator.hasNext();) 
		{
			JSONObject object = (JSONObject) iterator.next();
			
			String bgc = (String)object.get("text");
			lista.add(bgc);
		}
		
		return lista;
	}

	private Date parseDate(String sDate) throws ParseException
	{    
		Date date = null;
		if (sDate != null && !sDate.equals(Constantes.cadena_vacia))
		{
	        SimpleDateFormat sdf = new SimpleDateFormat(Constantes.DATE_FORMAT);
	        
			try {
				date = sdf.parse(sDate);
			} catch (java.text.ParseException e) {
				
				e.printStackTrace();
				throw new ParseException(0);
			}
		}
		return date;
	}

	private List<TemporalCoverage> parseTemporalCoverages(JSONArray jsonArray) throws ParseException 
	{
		List<TemporalCoverage> lista = new ArrayList<TemporalCoverage>();
		
		for (Iterator iterator = jsonArray.iterator(); iterator.hasNext();) 
		{
			JSONObject object = (JSONObject) iterator.next();
			
			TemporalCoverage tc = parseTemporalCoverage(object);
			lista.add(tc);
		}
		
		return lista;
	}

	private TemporalCoverage parseTemporalCoverage(JSONObject jsonObject) throws ParseException 
	{
		TemporalCoverage tempCov = null;
		
		if (jsonObject != null)
		{
			tempCov = new TemporalCoverage();
			
		String type = (String)jsonObject.get("@type");
        tempCov.setType(type);
        
        Date start = parseDate((String)jsonObject.get("start"));
        tempCov.setStart(start);
        
        Date end = parseDate((String)jsonObject.get("end"));
        tempCov.setEnd(end);
		}
		
        return tempCov;        
	}

	private List<GeographicCoverage> parseGeographicCoverage(JSONArray jsonArray) 
	{
		List<GeographicCoverage> lista = new ArrayList<GeographicCoverage>();
		
		for (Iterator iterator = jsonArray.iterator(); iterator.hasNext();) 
		{
			JSONObject object = (JSONObject) iterator.next();
			
			GeographicCoverage gc = parseGeographicCoverage(object);
			lista.add(gc);
		}
		
		return lista;
	}

	private GeographicCoverage parseGeographicCoverage(JSONObject jsonObject) 
	{
		GeographicCoverage  geoCov = null;
		
		if (jsonObject != null)
		{
			geoCov = new GeographicCoverage();
		String description = (String)jsonObject.get("description");
        geoCov.setDescription(description);
        
        BoundingBox boundingBox = parseBoundingBox((JSONObject)jsonObject.get("boundingBox"));
        geoCov.setBoundingBox(boundingBox);
        
		}
        return geoCov;
	}

	private BoundingBox parseBoundingBox(JSONObject jsonObject) 
	{
		BoundingBox bb = new BoundingBox();
		
			double minLatitude = parseDouble(jsonObject, "minLatitude");
	        bb.setMinLatitude(minLatitude);
	        
	        double maxLatitude = parseDouble(jsonObject, "smaxLatitude");
	        bb.setMaxLatitude(maxLatitude);
	        
	        double minLongitude = parseDouble(jsonObject, "minLongitude");
	        bb.setMinLongitude(minLongitude);
	        
	        double maxLongitude = parseDouble(jsonObject, "maxLongitude");
	        bb.setMaxLongitude(maxLongitude);
	        
	        boolean globalCoverage = (Boolean)jsonObject.get("globalCoverage");
	        bb.setGlobalCoverage(globalCoverage);		

        return bb;        
	}

	private double parseDouble(JSONObject jsonObject, String sParam) 
	{
		double d = 0;
		
		boolean parseado = true;
		try {
			d = (Double)jsonObject.get(sParam);
		} catch (Exception e) {
			parseado = false;
		}
		
		if (!parseado)
		{
			try {
				
			   String sDouble = (String)jsonObject.get(sParam); 
			   if (sDouble != null && !sDouble.equals(Constantes.cadena_vacia))
			   {
				   d = Double.parseDouble(sDouble);
			   }
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
        
		return d;
	}

	private List<TaxonomicCoverage> parseTaxonomicCoverages(JSONArray jsonArray) 
	{
		List<TaxonomicCoverage> lista = new ArrayList<TaxonomicCoverage>();
		
		for (Iterator iterator = jsonArray.iterator(); iterator.hasNext();) 
		{
			JSONObject object = (JSONObject) iterator.next();
			
			TaxonomicCoverage tc = parseTaxonomicCoverage(object);
			lista.add(tc);
		}
		
		return lista;
	}

	private TaxonomicCoverage parseTaxonomicCoverage(JSONObject jsonObject) 
	{
		TaxonomicCoverage taxCov = null;
		
		if (jsonObject != null)
		{
			taxCov = new TaxonomicCoverage();
		
		String description = (String)jsonObject.get("description");
        taxCov.setDescription(description);
        
        List<Coverage> coverages = parseCoverages((JSONArray)jsonObject.get("coverages"));
        taxCov.setCoverages(coverages);
		}
        return taxCov;
	}

	private List<Coverage> parseCoverages(JSONArray jsonArray) 
	{
		List<Coverage> lista = new ArrayList<Coverage>();
		
		for (Iterator iterator = jsonArray.iterator(); iterator.hasNext();) 
		{
			JSONObject object = (JSONObject) iterator.next();
			
			Coverage coverage = parseCoverage(object);
			lista.add(coverage);
		}
		
		return lista;
	}

	private Coverage parseCoverage(JSONObject jsonObject) 
	{
		Coverage coverage = null;
		
		if (jsonObject != null)
		{
			coverage = new Coverage();
			String scientificName = (String)jsonObject.get("scientificName");
	        coverage.setScientificName(scientificName);
	        
	        String commonName = (String)jsonObject.get("commonName");
	        coverage.setCommonName(commonName);
	        
	        Rank rank = parseRank((JSONObject)jsonObject.get("rank"));
	        coverage.setRank(rank);        
		}
        return coverage;
        
	}

	private Rank parseRank(JSONObject jsonObject) 
	{
		Rank rank = null;
		if (jsonObject != null)
		{
			rank = new Rank();
		
		String verbatim = (String)jsonObject.get("verbatim");
        rank.setVerbatim(verbatim);
        
        String interpreted = (String)jsonObject.get("interpreted");
        rank.setInterpreted(interpreted);
		}
        return rank;		
	}

	private List<Identifier> parseIdentifiers(JSONArray jsonArray) throws ParseException 
	{
		List<Identifier> lista = new ArrayList<Identifier>();
		
		for (Iterator iterator = jsonArray.iterator(); iterator.hasNext();) 
		{
			JSONObject object = (JSONObject) iterator.next();
			
			Identifier i = parseIdentifier(object);
			lista.add(i);
		}
		
		return lista;
	}

	private Identifier parseIdentifier(JSONObject jsonObject) throws ParseException 
	{
		Identifier identifier= null;
		
		if (jsonObject != null)
		{
			identifier= new Identifier();
		
		long key = (Long)jsonObject.get("key");
        identifier.setKey(key);
        
        String type = (String)jsonObject.get("type");
        identifier.setType(type);
        
        String identi = (String)jsonObject.get("identifier");
        identifier.setIdentifier(identi);
                
        String createdBy = (String)jsonObject.get("createdBy");
        identifier.setCreatedBy(createdBy);
        
        Date created = parseDate((String)jsonObject.get("created"));
        identifier.setCreated(created);
		}
        return identifier;
	}

	private List<EndPoint> parseEndpoints(JSONArray jsonArray) throws ParseException
	{
		List<EndPoint> lista = new ArrayList<EndPoint>();
		
		for (Iterator iterator = jsonArray.iterator(); iterator.hasNext();) 
		{
			JSONObject object = (JSONObject) iterator.next();
			
			EndPoint ep = parseEndPoint(object);
			lista.add(ep);
		}
		
		return lista;
	}

	private EndPoint parseEndPoint(JSONObject jsonObject) throws ParseException 
	{
		EndPoint ep = null;
		if (jsonObject != null)
		{
			ep = new EndPoint();
		
		long key = (Long)jsonObject.get("key");
		ep.setKey(key);
		
		String type = (String)jsonObject.get("type");
		ep.setType(type);
		
		String url = (String)jsonObject.get("url");
		ep.setUrl(url);
		
		String createdBy = (String)jsonObject.get("createdBy");
        ep.setCreatedBy(createdBy);
        
        String modifiedBy = (String)jsonObject.get("modifiedBy");
        ep.setModifiedBy(modifiedBy);
        
        Date created = parseDate((String)jsonObject.get("created"));
        ep.setCreated(created);
        
        Date modified = parseDate((String)jsonObject.get("modified"));
        ep.setModified(modified);
        
        List<MachineTags> machineTags = parseMachineTagss((JSONArray)jsonObject.get("machineTags"));
		ep.setMachineTags(machineTags);
		}
		return ep;
	}

	private List<MachineTags> parseMachineTagss(JSONArray jsonArray) throws ParseException 
	{
		List<MachineTags> lista = new ArrayList<MachineTags>();
		
		for (Iterator iterator = jsonArray.iterator(); iterator.hasNext();) 
		{
			JSONObject object = (JSONObject) iterator.next();
			
			MachineTags machineTags= parseMachineTags(object);
			lista.add(machineTags);
		}
		
		return lista;
	}
	
	private List<Tags> parseTagss(JSONArray jsonArray) throws ParseException 
	{
		List<Tags> lista = new ArrayList<Tags>();
		
		for (Iterator iterator = jsonArray.iterator(); iterator.hasNext();) 
		{
			JSONObject object = (JSONObject) iterator.next();
			
			Tags tag= parseTags(object);
			lista.add(tag);
		}
		
		return lista;
	}

	private MachineTags parseMachineTags(JSONObject jsonObject) throws ParseException 
	{
		MachineTags mt= null;
		
		if (jsonObject != null)
		{
			mt= new MachineTags();
		  
			long key = (Long)jsonObject.get("key");
	        mt.setKey(key);
	        
	        String namespace = (String)jsonObject.get("namespace");
	        mt.setNamespace(namespace);
	        
	        String name = (String)jsonObject.get("name");
	        mt.setName(name);
	        
	        String value = (String)jsonObject.get("value");
	        mt.setValue(value);
	        
	        String createdBy = (String)jsonObject.get("createdBy");
	        mt.setCreatedBy(createdBy);
	        
	        Date created = parseDate((String)jsonObject.get("created"));
	        mt.setCreated(created);
		}
        return mt;
	}
	
	private Tags parseTags(JSONObject jsonObject) throws ParseException 
	{
		Tags tag= null;
		
		if (jsonObject != null)
		{
			tag= new Tags();
		  
			long key = (Long)jsonObject.get("key");
	        tag.setKey(key);
	        	        
	        String value = (String)jsonObject.get("value");
	        tag.setValue(value);
	        
	        String createdBy = (String)jsonObject.get("createdBy");
	        tag.setCreatedBy(createdBy);
	        
	        Date created = parseDate((String)jsonObject.get("created"));
	        tag.setCreated(created);
		}
        return tag;
	}

	private List<Contact> parseContatcs(JSONArray jsonArray) throws ParseException 
	{
		List<Contact> lista = new ArrayList<Contact>();
		
		for (Iterator iterator = jsonArray.iterator(); iterator.hasNext();) 
		{
			JSONObject object = (JSONObject) iterator.next();
			
			Contact contact = parseContact(object);
			lista.add(contact);
		}
		
		return lista;
	}

	private Contact parseContact(JSONObject jsonObject) throws ParseException 
	{
		Contact contact = new Contact();
		
		long key = (Long)jsonObject.get("key");
        contact.setKey(key);
        
        String type = (String)jsonObject.get("type");
        contact.setType(type);
        
        boolean primary = (Boolean)jsonObject.get("primary");
        contact.setPrimary(primary);
        
        // userID
        
        String firstName = (String)jsonObject.get("firstName");
        contact.setFirstName(firstName);
		
        String lastName = (String)jsonObject.get("lastName");
        contact.setLastName(lastName);
        
        
        List<String> positions = parseStrings((JSONArray)jsonObject.get("position"));        
        contact.setPositions(positions);
               
        List<String> emails = parseStrings((JSONArray)jsonObject.get("email"));        
        contact.setEmails(emails);
        
        List<String> phones = parseStrings((JSONArray)jsonObject.get("phone"));        
        contact.setPhones(phones);
        
        List<String> homepages = parseStrings((JSONArray)jsonObject.get("homepage"));
        contact.setHomepages(homepages);
        
        String organization = (String)jsonObject.get("organization");
        contact.setOrganization(organization);
        
        List<String> addresss = parseStrings((JSONArray)jsonObject.get("address"));
        contact.setAddress(addresss);
        
        String city = (String)jsonObject.get("city");
        contact.setCity(city);
        
        String province = (String)jsonObject.get("province");
        contact.setProvince(province);

        String country = (String)jsonObject.get("country");
        contact.setCountry(country);
        
        String postalCode = (String)jsonObject.get("postalCode");
        contact.setPostalCode(postalCode);
        
        String createdBy = (String)jsonObject.get("createdBy");
        contact.setCreatedBy(createdBy);
        
        String modifiedBy = (String)jsonObject.get("modifiedBy");
        contact.setModifiedBy(modifiedBy);
        
        Date created = parseDate((String)jsonObject.get("created"));
        contact.setCreated(created);
        
        Date modified = parseDate((String)jsonObject.get("modified"));
        contact.setModified(modified);
		
		
		return contact;
	}

	private List<String> parseStrings(JSONArray jArray) 
	{
		List<String> lista = null;
		if (jArray != null)
		{
			lista = new ArrayList<String>();
			
			for (int i = 0; i < jArray.size(); i++) 
	        {
	        	String p = (String)jArray.get(i);
	        	lista.add(p);			
			}
		}
        
        return lista;
	}

	private Citation parseCitation(JSONObject jsonObject) 
	{
		Citation citation = new Citation();
		
		String text = (String)jsonObject.get("text");
        citation.setText(text);
		
        return citation;				
	}

	public Organization parseOrganization(String jsonTxt) throws ParseException 
	{
		Organization org = new Organization();
		
		JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(jsonTxt);
        
        String key = (String)jsonObject.get("key");
        org.setKey(key);
        
        String endorsingNodeKey = (String)jsonObject.get("endorsingNodeKey");
        org.setEndorsingNodeKey(endorsingNodeKey);

        boolean endorsementApproved = (Boolean)jsonObject.get("endorsementApproved");
        org.setEndorsementApproved(endorsementApproved);
        
        String title = (String)jsonObject.get("title");
        org.setTitle(title);
        
        String description = (String)jsonObject.get("description");
        org.setDescription(description);
        
        String language = (String)jsonObject.get("language");
        org.setLanguage(language);
        
        List<String> homepages = parseStrings((JSONArray)jsonObject.get("homepage"));
        org.setHomepages(homepages);
                
        List<String> addresss = parseStrings((JSONArray)jsonObject.get("address"));
        org.setAddress(addresss);
        
        String city = (String)jsonObject.get("city");
        org.setCity(city);
        
        String country = (String)jsonObject.get("country");
        org.setCountry(country);
        
        String postalCode = (String)jsonObject.get("postalCode");
        org.setPostalCode(postalCode);
        
        double latitude = parseDouble(jsonObject, "latitude");
          org.setLatitude(latitude);
        
        double longitude = parseDouble(jsonObject, "longitude");
          org.setLongitude(longitude);
        
        long numPublishedDatasets = (Long)jsonObject.get("numPublishedDatasets");
        org.setNumPublishedDatasets(numPublishedDatasets);
        
        String createdBy = (String)jsonObject.get("createdBy");
        org.setCreatedBy(createdBy);
        
        String modifiedBy = (String)jsonObject.get("modifiedBy");
        org.setModifiedBy(modifiedBy);
        
        Date created = parseDate((String)jsonObject.get("created"));
        org.setCreated(created);        
        
        Date modified = parseDate((String)jsonObject.get("modified"));
        org.setModified(modified);
        
        List<Contact> contacts = parseContatcs((JSONArray)jsonObject.get("contacts"));
        org.setContacts(contacts);
        
        List<EndPoint> endpoints = parseEndpoints((JSONArray)jsonObject.get("endpoints"));
        org.setEndpoints(endpoints);
        
        List<MachineTags> machineTags = parseMachineTagss((JSONArray)jsonObject.get("machineTags"));
        org.setMachineTags(machineTags);
        
        List<Tags> tags = parseTagss((JSONArray)jsonObject.get("tags"));        
        org.setTags(tags);
        
        List<Identifier> identifiers = parseIdentifiers((JSONArray)jsonObject.get("identifiers"));
        org.setIdentifiers(identifiers);
        
        List<String> comments = parseStrings((JSONArray)jsonObject.get("comments"));        
        org.setComments(comments);
        
        return org;
	}
	
	public OcurrencesResult parseOcurrencesResult(String jsonTxt) throws ParseException 
	{
		
		OcurrencesResult oR = new OcurrencesResult();
			
		JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(jsonTxt);
        
        Long offset = (Long)jsonObject.get("offset");

        oR.setOffset(offset);
        
        Long limit = (Long)jsonObject.get("limit"); 
        oR.setLimit(limit);
        
        boolean eor = (Boolean)jsonObject.get("endOfRecords");
        oR.setEndOfRecords(eor);
        
        Long count = (Long)jsonObject.get("count");
        oR.setCount(count);

		JSONArray jResults = (JSONArray)jsonObject.get("results");

        List<OcurrenceRow> ocurrences = parseOcurrenceRows(jResults);
        
        oR.setOcurrences(ocurrences);;
		
		
		return oR;
	}
	
	public SpeciesResult parseSpeciesResult(String jsonTxt) throws ParseException 
	{
		
		SpeciesResult oR = new SpeciesResult();
			
		JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(jsonTxt);
        
        Long offset = (Long)jsonObject.get("offset");

        oR.setOffset(offset);
        
        Long limit = (Long)jsonObject.get("limit"); 
        oR.setLimit(limit);
        
        boolean eor = (Boolean)jsonObject.get("endOfRecords");
        oR.setEndOfRecords(eor);
        

		JSONArray jResults = (JSONArray)jsonObject.get("results");

        List<SpecieRow> species = parseSpeciesRows(jResults);
        
        oR.setSpecies(species);		
		
		return oR;
	}

	private OcurrenceRow parseOcurrenceRow(JSONObject jsonObject) throws ParseException 
	{
		OcurrenceRow dsRow = new OcurrenceRow();
		
		String key = ((Long)jsonObject.get("key")).toString();
		dsRow.setKey(key);
		
//		logger.debug("GbifParser.parseOcurrenceRow(): key=" + key + "-");
//		logger.info("GbifParser.parseOcurrenceRow(): key=" + key + "-");
//		
//		if (key.equals("727704995"))
//		{
//			logger.info("GbifParser.parseOcurrenceRow(): key=" + key + "-");
//		}
		
		String datasetKey = (String)jsonObject.get("datasetKey");
		dsRow.setDatasetKey(datasetKey);
		
		String publishingOrgKey = (String)jsonObject.get("publishingOrgKey");
		dsRow.setPublishingOrganizationKey(publishingOrgKey);
		
		String publishingCountry = (String)jsonObject.get("publishingCountry");
		dsRow.setPublishingCountry(publishingCountry);
		
		String protocol = (String)jsonObject.get("protocol");
		dsRow.setProtocol(protocol);
		
		String lastCrawled = (String)jsonObject.get("lastCrawled");
		dsRow.setLastCrawled(parseDate(lastCrawled));
		
		String lastParsed = (String)jsonObject.get("lastParsed");
		dsRow.setLastParsed(parseDate(lastParsed));

		// extensions
		
		String basisOfRecord = (String)jsonObject.get("basisOfRecord");
		dsRow.setBasisOfRecord(basisOfRecord);

		Taxon taxon = parseTaxon(jsonObject);
		dsRow.setTaxon(taxon);
		
		Double d;
		d = (Double)jsonObject.get("decimalLongitude");
		if (d != null)
		{
		   double decimalLongitude = (Double)jsonObject.get("decimalLongitude");//parseDouble(jsonObject, "decimalLongitude");
           dsRow.setDecimalLongitude(decimalLongitude);
		}
        
		d = (Double)jsonObject.get("decimalLatitude");
		if (d != null)
		{
           double decimalLatitude = (Double)jsonObject.get("decimalLatitude");
           dsRow.setDecimalLatitude(decimalLatitude);
		}
		
        Long year = (Long)jsonObject.get("year");
        if (year != null)
        {
        	dsRow.setYear(year);
        }

        Long month = (Long)jsonObject.get("month");
        if (month != null)
        {
        	dsRow.setMonth(month);
        }

        Long day;
        try {
			day = (Long)jsonObject.get("day");
			if (day != null)
			{
				dsRow.setDay(day);			
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        Date eventDate = parseDate((String)jsonObject.get("eventDate"));
        dsRow.setEventDate(eventDate);
        
        dsRow.setIssues(parseStrings((JSONArray)jsonObject.get("issues")));
        
        Date lastInterpreted = parseDate((String)jsonObject.get("lastInterpreted"));
        dsRow.setLastInterpreted(lastInterpreted);
        
        dsRow.setIdentifiers(parseStrings((JSONArray)jsonObject.get("identifiers")));
        dsRow.setFacts(parseStrings((JSONArray)jsonObject.get("facts")));
        dsRow.setRelations(parseStrings((JSONArray)jsonObject.get("relations")));
        
        String geodeticDatum = (String)jsonObject.get("geodeticDatum");
        dsRow.setGeodeticDatum(geodeticDatum);
        
        String ocurrenceClass = (String)jsonObject.get("class");
        dsRow.setOcurrenceClass(ocurrenceClass);
        
        String countryCode = (String)jsonObject.get("countryCode");
        dsRow.setCountryCode(countryCode);
        
        String country = (String)jsonObject.get("country");
        dsRow.setCountry(country);
        
        String gbifID = (String)jsonObject.get("gbifID");
        dsRow.setGbifID(gbifID);
        
        String institutionCode = (String)jsonObject.get("institutionCode");
        dsRow.setInstitutionCode(institutionCode);
        
        String catalogNumber = (String)jsonObject.get("catalogNumber");
        dsRow.setCatalogNumber(catalogNumber);
        
        String collectionCode = (String)jsonObject.get("collectionCode");
        dsRow.setCollectionCode(collectionCode);
        
		return dsRow;
	}
	
	
	private SpecieRow parseSpecieRow(JSONObject jsonObject) throws ParseException 
	{
		SpecieRow dsRow = new SpecieRow();
		
		String key = ((Long)jsonObject.get("key")).toString();
		dsRow.setKey(key);
		
//		logger.debug("GbifParser.parseOcurrenceRow(): key=" + key + "-");
//		logger.info("GbifParser.parseOcurrenceRow(): key=" + key + "-");
//		
//		if (key.equals("727704995"))
//		{
//			logger.info("GbifParser.parseOcurrenceRow(): key=" + key + "-");
//		}
		
		String datasetKey = (String)jsonObject.get("datasetKey");
		dsRow.setDatasetKey(datasetKey);
		
//		logger.info("GbifParser.parseSpecieRow(): key=" + key + "-");
		long n = 0;
		String nubKey  = null;
		
		try {
		   n = (Long)jsonObject.get("nubKey");
		
		  nubKey = Long.toString(n);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (nubKey != null)
		{
			dsRow.setNubKey(nubKey);
		}

		String parentKey = null;
		try {
			
		n = (Long)jsonObject.get("parentKey");
		parentKey = Long.toString(n);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (parentKey != null)
		{
			dsRow.setParentKey(parentKey);
		}
		

		Taxon taxon = parseTaxon(jsonObject);
		dsRow.setTaxon(taxon);
		
		String canonicalName = (String)jsonObject.get("canonicalName");
		dsRow.setCanonicalName(canonicalName);
		
		String vernacularName = (String)jsonObject.get("vernacularName");
		dsRow.setVernacularName(vernacularName);

		String authorship = (String)jsonObject.get("authorship");
		dsRow.setAuthorship(authorship);

		String nameType = (String)jsonObject.get("nameType");
		dsRow.setNameType(nameType);
        
		String taxonomicStatus = (String)jsonObject.get("taxonomicStatus");
		dsRow.setTaxonomicStatus(taxonomicStatus);

		String publishedIn = (String)jsonObject.get("publishedIn");
		dsRow.setPublishedIn(publishedIn);

		String accordingTo = (String)jsonObject.get("accordingTo");
		dsRow.setAccordingTo(accordingTo);

		long numDescendants = (Long)jsonObject.get("numDescendants");
		dsRow.setNumDescendants(numDescendants);
		
		String modified = (String)jsonObject.get("modified");
		dsRow.setModified(parseDate(modified));
		
		dsRow.setIssues(parseStrings((JSONArray)jsonObject.get("issues")));
        
        Date lastInterpreted = parseDate((String)jsonObject.get("lastInterpreted"));
        dsRow.setLastInterpreted(lastInterpreted);

		boolean synonym = (Boolean)jsonObject.get("synonym");
		dsRow.setSynonym(synonym);
        
        String specieClass = (String)jsonObject.get("class");
        dsRow.setSpecieClass(specieClass);
        
		return dsRow;
	}

	private Taxon parseTaxon(JSONObject jsonObject) 
	{
		Taxon taxon = new Taxon();
		Long key;
		key = (Long)jsonObject.get("taxonKey");
		if (key != null)
		{
			String taxonKey = key.toString();
			taxon.setTaxonKey(taxonKey);
		}
		
		key = (Long)jsonObject.get("kingdomKey");
		if (key != null)
		{
		   String kingdomKey = key.toString();
		   taxon.setKingdomKey(kingdomKey);
		}
		
		key = (Long)jsonObject.get("phylumKey");
		if (key != null)
		{
		   String phylumKey = key.toString();
		   taxon.setPhylumKey(phylumKey);
		}
		
		key = (Long)jsonObject.get("classKey");
		if (key != null)
		{
		   String classKey = key.toString();
		   taxon.setClassKey(classKey);
		}
		
		key = (Long)jsonObject.get("orderKey");
		if (key != null)
		{
			String orderKey = key.toString();
			taxon.setOrderKey(orderKey);
		}
		
		key = (Long)jsonObject.get("familyKey");
		if (key != null)
		{
		   String familyKey = key.toString();
		   taxon.setFamilyKey(familyKey);
		}
		
		key = (Long)jsonObject.get("genusKey");
		if (key != null)
		{
		   String genusKey = key.toString();
		   taxon.setGenusKey(genusKey);
		}
		key = (Long)jsonObject.get("speciesKey");
		if (key != null)
		{
		   String speciesKey = key.toString();
		   taxon.setSpeciesKey(speciesKey);
		}
		
		String scientificName = (String)jsonObject.get("scientificName");
		taxon.setScientificName(scientificName);
		
		String kingdom = (String)jsonObject.get("kingdom");
		taxon.setKingdom(kingdom);
		
		String phylum = (String)jsonObject.get("phylum");
		taxon.setPhylum(phylum);
		
		String order = (String)jsonObject.get("order");
		taxon.setOrder(order);
		
		String family = (String)jsonObject.get("family");
		taxon.setFamily(family);
		
		String genus = (String)jsonObject.get("genus");
		taxon.setGenus(genus);
		
		String species = (String)jsonObject.get("species");
		taxon.setSpecies(species);
		
		String genericName = (String)jsonObject.get("genericName");
		taxon.setGenericName(genericName);
		
		String specificEpithet = (String)jsonObject.get("specificEpithet");
		taxon.setSpecificEpithet(specificEpithet);
		
		String taxonRank = (String)jsonObject.get("taxonRank");
		taxon.setTaxonRank(taxonRank);		
		
		return taxon;
	}
}

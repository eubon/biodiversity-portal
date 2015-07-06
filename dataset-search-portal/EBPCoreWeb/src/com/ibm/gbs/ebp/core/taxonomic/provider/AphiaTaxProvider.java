package com.ibm.gbs.ebp.core.taxonomic.provider;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;

import org.apache.log4j.Logger;

import aphia.v1_0.AphiaNameServicePortTypeProxy;
import aphia.v1_0.AphiaRecord;
import aphia.v1_0.Vernacular;

import com.ibm.gbs.ebp.core.exception.NotFoundException;
import com.ibm.gbs.eubon.ebp.core.jpa.entity.Provider;
import com.ibm.gbs.eubon.taxonomic.TaxRecord;
import com.ibm.gbs.eubon.taxonomic.TaxVernacular;
import com.ibm.gbs.tramitator.ejb.crud.ServicioCRUD;
import com.ibm.gbs.tramitator.jsf.mbean.taxo.TaxRecordGenerator;

public class AphiaTaxProvider implements TaxProvider
{

AphiaNameServicePortTypeProxy proxy;

   @EJB
   private ServicioCRUD crud;

   private static String ENDPOINT = "http://localhost:18080/aphia.php?p=soap";
   
   private boolean marineOnly = false;
   
   private boolean fuzzy = true;
   
   private int offset = 0;
	
	private Provider provider;


    Logger logger = Logger.getLogger("com.ibm.gbs");
    
	public AphiaTaxProvider(Provider prov)
	{
		provider = prov;
	
		initProxy();
	}
	
	private void initProxy()
	{
		proxy = new AphiaNameServicePortTypeProxy();
		
		String endPoint = ENDPOINT;
		if (provider != null)
		{
			endPoint = provider.getServiceUrl();
		}
		proxy.setEndpoint(endPoint);				
	}
	
	@Override
	public String getNameByID(String gUID) throws RemoteException, NotFoundException
	{
		String res = "";
		int aphiaID = 0;
		try 
		{
			aphiaID = Integer.valueOf(gUID);
		} catch (Exception e) {
			throw new NotFoundException("Invalid ID:"+gUID);
		}
		
		res = proxy.getAphiaNameByID(aphiaID);
		
		return res;
	}
	
	@Override
	public String getGUID(String name) throws RemoteException, NotFoundException
	{ 
		logger.debug("AphiaTaxProvider.getGUID(): scientificName=" + name + "-");
         
        int GUID = 0;			
    	try {
			GUID = proxy.getAphiaID(name, marineOnly);
		} catch (Exception e) {
			throw new NotFoundException();
		}

			        
        logger.debug("AphiaTaxProvider.getGUID(): gUID=" + GUID + "-");
        
        String res = Integer.toString(GUID);
        
        return res;
	}

	@Override
	public List<TaxRecord> getRecords(String name, boolean like) throws RemoteException 
	{
		AphiaRecord[] records;
		List<TaxRecord> recordList = null;
		
        records = proxy.getAphiaRecords(name, like, fuzzy, marineOnly, offset);
			
		if (records != null && records.length > 0)
		{
			recordList = new ArrayList<TaxRecord>();
			for (int i = 0; i < records.length; i++) 
			{
				TaxRecord taxRecord = new TaxRecordGenerator().generateTaxRecord(records[i]);
				recordList.add(taxRecord);
			}	
		}
		
		return recordList;
	}

	@Override
	public List<TaxRecord> getRecordsByVernacular(String name) throws RemoteException {
		AphiaRecord[] records;
		List<TaxRecord> recordList = null;
		
		records = proxy.getAphiaRecordsByVernacular(name, true, offset);
		
		if (records != null && records.length > 0)
		{

			recordList = new ArrayList<TaxRecord>();
			for (int i = 0; i < records.length; i++) 
			{
				TaxRecord taxRecord = new TaxRecordGenerator().generateTaxRecord(records[i]);
				recordList.add(taxRecord);
			}
		}
		
		return recordList;
	}
	
	@Override
	public TaxRecord getRecordByGUID(String gUID) throws RemoteException, NotFoundException
	{
		AphiaRecord aphiaRecord;
		TaxRecord record = null;

		int aphiaID = 0;
		try 
		{
			aphiaID = Integer.valueOf(gUID);
		} catch (Exception e) {
			throw new NotFoundException("Invalid ID:"+gUID);
		}
		
		aphiaRecord = proxy.getAphiaRecordByID(aphiaID);
			
		if (aphiaRecord != null )
		{
			record = new TaxRecordGenerator().generateTaxRecord(aphiaRecord);	
		}
		
		return record;
	}
	
	@Override
	public List<TaxVernacular> getVernacularsByGUID(String gUID) throws RemoteException, NotFoundException 
	{
		List<TaxVernacular> vernacularList = null;
		
		int aphiaID = 0;
		try 
		{
			aphiaID = Integer.valueOf(gUID);
		} catch (Exception e) {
			throw new NotFoundException("Invalid ID:"+gUID);
		}
		
        Vernacular[] vernaculars = proxy.getAphiaVernacularsByID(aphiaID);
			
		if (vernaculars != null && vernaculars.length > 0)
		{
			vernacularList = new ArrayList<TaxVernacular>();
			for (int i = 0; i < vernaculars.length; i++) 
			{
				TaxVernacular taxVernacular = new TaxRecordGenerator().generateTaxVernacular(vernaculars[i]);
				vernacularList.add(taxVernacular);
			}	
		}
		
		return vernacularList;
	}
	
	public Provider getProvider() {
		return provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}

	@Override
	public List<TaxRecord> getSynonimsByGUID(String gUID) throws RemoteException, NotFoundException 
	{
		AphiaRecord[] records;
		List<TaxRecord> recordList = null;
		
		int aphiaID = 0;
		try 
		{
			aphiaID = Integer.valueOf(gUID);
		} catch (Exception e) {
			throw new NotFoundException("Invalid ID:"+gUID);
		}
		
        records = proxy.getAphiaSynonymsByID(aphiaID);
			
		if (records != null && records.length > 0)
		{
			recordList = new ArrayList<TaxRecord>();
			for (int i = 0; i < records.length; i++) 
			{
				TaxRecord taxRecord = new TaxRecordGenerator().generateTaxRecord(records[i]);
				recordList.add(taxRecord);
			}	
		}
		
		return recordList;
	}

}

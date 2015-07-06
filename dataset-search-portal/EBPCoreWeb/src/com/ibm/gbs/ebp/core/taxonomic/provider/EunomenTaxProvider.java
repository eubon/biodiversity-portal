package com.ibm.gbs.ebp.core.taxonomic.provider;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import PESI.v0_5.PESINameServicePortTypeProxy;
import PESI.v0_5.PESIRecord;
import PESI.v0_5.Vernacular;

import com.ibm.gbs.ebp.core.exception.NotFoundException;
import com.ibm.gbs.eubon.ebp.core.jpa.entity.Provider;
import com.ibm.gbs.eubon.taxonomic.TaxRecord;
import com.ibm.gbs.eubon.taxonomic.TaxVernacular;
import com.ibm.gbs.tramitator.jsf.mbean.taxo.TaxRecordGenerator;

public class EunomenTaxProvider implements TaxProvider
{

PESINameServicePortTypeProxy proxy;

   private static String ENDPOINT = "http://localhost:28080/portal/soap.php";

    Logger logger = Logger.getLogger("com.ibm.gbs");
    
	private Provider provider;
    
	public EunomenTaxProvider(Provider prov)
	{
		provider = prov;
		initProxy();
	}
	
	private void initProxy()
	{
		proxy = new PESINameServicePortTypeProxy();
		
		String endPoint = ENDPOINT;
		if (provider != null)
		{
			endPoint = provider.getServiceUrl();
		}
		proxy.setEndpoint(endPoint);		
	}
	
	public String getNameByID(String gUID) throws RemoteException, NotFoundException
	{
		String res = "";
		
		res = proxy.getPESINameByGUID(gUID);
		
		return res;
	}
	
	
	@Override
	public String getGUID(String name) throws RemoteException, NotFoundException
	{
		logger.debug("EunomenTaxProvider.getGUID(): scientificName=" + name + "-");
         
        String GUID = "";
        
        	GUID = proxy.getGUID(name);
			        
        logger.debug("EunomenTaxProvider.getGUID(): gUID=" + GUID + "-");
        
        return GUID;
	}

	@Override
	public List<TaxRecord> getRecords(String name, boolean like) throws RemoteException 
	{
		PESIRecord[] records;
		List<TaxRecord> recordList = null;
		
        records = proxy.getPESIRecords(name, like);
			
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
	public List<TaxRecord> getRecordsByVernacular(String name)
			throws RemoteException {
		PESIRecord[] records;
		List<TaxRecord> recordList = null;
		
		records = proxy.getPESIRecordsByVernacular(name);
		
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
		PESIRecord pesiRecord;
		TaxRecord record = null;
		
		pesiRecord = proxy.getPESIRecordByGUID(gUID);
			
		if (pesiRecord != null )
		{
			record = new TaxRecordGenerator().generateTaxRecord(pesiRecord);	
		}
		
		return record;
	}

	public Provider getProvider() {
		return provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}

	@Override
	public List<TaxVernacular> getVernacularsByGUID(String gUID) throws RemoteException 
	{
		List<TaxVernacular> vernacularList = null;
		
        Vernacular[] vernaculars = proxy.getPESIVernacularsByGUID(gUID);
			
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

	@Override
	public List<TaxRecord> getSynonimsByGUID(String gUID) throws RemoteException, NotFoundException
	{
		PESIRecord[] records;
		List<TaxRecord> recordList = null;
		
        records = proxy.getPESISynonymsByGUID(gUID);
			
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

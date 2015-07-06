package com.ibm.gbs.ebp.core.taxonomic;

import java.rmi.RemoteException;
import java.util.List;

import com.ibm.gbs.ebp.core.exception.NotFoundException;
import com.ibm.gbs.ebp.core.taxonomic.provider.TaxProvider;
import com.ibm.gbs.eubon.taxonomic.TaxRecord;
import com.ibm.gbs.eubon.taxonomic.TaxVernacular;

public interface TaxService 
{
	
	public String getGUID(String name, TaxProvider taxProvider) throws RemoteException, NotFoundException;
	
	public List<TaxRecord> getRecords(String name, boolean like, TaxProvider taxProvider) throws RemoteException, NotFoundException;

	public List<TaxRecord> getRecordsByVernacular(String name, TaxProvider taxProvider) throws RemoteException, NotFoundException;
	
	public String getNameByID(String gUID, TaxProvider taxProvider) throws RemoteException, NotFoundException;
	
	public TaxRecord getRecordByGUID(String gUID, TaxProvider taxProvider) throws RemoteException, NotFoundException;
	
	public List<TaxVernacular> getVernacularsByGUID(String gUID, TaxProvider taxProvider) throws RemoteException, NotFoundException;
	
	public List<TaxRecord> getSynonymsByGUID(String gUID, TaxProvider taxProvider) throws RemoteException, NotFoundException;
	
	public TaxProvider getProvider(long id);
	
}

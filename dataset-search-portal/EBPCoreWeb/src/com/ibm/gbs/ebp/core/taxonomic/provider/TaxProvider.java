package com.ibm.gbs.ebp.core.taxonomic.provider;

import java.rmi.RemoteException;
import java.util.List;

import com.ibm.gbs.ebp.core.exception.NotFoundException;
import com.ibm.gbs.eubon.ebp.core.jpa.entity.Provider;
import com.ibm.gbs.eubon.taxonomic.TaxRecord;
import com.ibm.gbs.eubon.taxonomic.TaxVernacular;

public interface TaxProvider {
		
	public String getGUID(String name) throws RemoteException, NotFoundException;
	
	public List<TaxRecord> getRecords(String name, boolean like) throws RemoteException, NotFoundException;
	
	public List<TaxRecord> getRecordsByVernacular(String name) throws RemoteException, NotFoundException;

	public String getNameByID(String gUID) throws RemoteException, NotFoundException;

	public TaxRecord getRecordByGUID(String gUID) throws RemoteException, NotFoundException;

	public List<TaxVernacular> getVernacularsByGUID(String gUID) throws RemoteException, NotFoundException;

	public List<TaxRecord> getSynonimsByGUID(String gUID) throws RemoteException, NotFoundException;
	
	public Provider getProvider();
	
	

}

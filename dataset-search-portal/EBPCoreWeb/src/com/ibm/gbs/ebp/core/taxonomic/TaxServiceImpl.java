package com.ibm.gbs.ebp.core.taxonomic;

import java.rmi.RemoteException;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.ibm.gbs.ebp.core.exception.NotFoundException;
import com.ibm.gbs.ebp.core.taxonomic.provider.AphiaTaxProvider;
import com.ibm.gbs.ebp.core.taxonomic.provider.EunomenTaxProvider;
import com.ibm.gbs.ebp.core.taxonomic.provider.TaxProvider;
import com.ibm.gbs.eubon.ebp.core.jpa.entity.Provider;
import com.ibm.gbs.eubon.taxonomic.TaxRecord;
import com.ibm.gbs.eubon.taxonomic.TaxVernacular;
import com.ibm.gbs.tramitator.ejb.crud.ServicioCRUD;
import com.ibm.gbs.tramitator.util.Constantes;
import com.ibm.gbs.tramitator.util.configuracion.Propiedades;


public class TaxServiceImpl implements TaxService
{

	@EJB
	private ServicioCRUD crud;

	public TaxServiceImpl()
	{		
		super();
	}

	public TaxServiceImpl(ServicioCRUD _crud)
	{
		crud = _crud;		
	}
	
	public String getGUID(String name, TaxProvider taxProvider) throws RemoteException, NotFoundException
	{
		String res = "";
		
		res = taxProvider.getGUID(name);
		
		return res;
	}
	
	public List<TaxRecord> getRecords(String name, boolean like, TaxProvider taxProvider) throws RemoteException, NotFoundException
	{
		List<TaxRecord> records = null;
		
		records = taxProvider.getRecords(name, like);
		
		return records;
	}
	
	
	public List<TaxRecord> getRecordsByVernacular(String name, TaxProvider taxProvider) throws RemoteException, NotFoundException
	{
		List<TaxRecord> records = null;
		
		records = taxProvider.getRecordsByVernacular(name);
		
		return records;
	}
	
	public TaxProvider getProvider(long id)
	{
		TaxProvider taxProvider = null;
		
		String idProvider = String.valueOf(id);
		
		Provider prov = (Provider)crud.obtenerObjeto(Provider.class, id);
		
		if (idProvider.equals(Propiedades.getPropiedad(Constantes.CFG_EUNOMEN_ID)))
		{
			taxProvider = new EunomenTaxProvider(prov);			
		}
		else if (idProvider.equals(Propiedades.getPropiedad(Constantes.CFG_WORMS_ID)))
		{
			taxProvider = new AphiaTaxProvider(prov);			
		}
		
		
		return taxProvider;

		
	}
	
	public String getNameByID(String gUID, TaxProvider taxProvider) throws RemoteException, NotFoundException
	{
		String res = "";
		
		res = taxProvider.getNameByID(gUID);
		
		return res;
	}
	
	public TaxRecord getRecordByGUID(String gUID, TaxProvider taxProvider) throws RemoteException, NotFoundException
	{
		TaxRecord record = null;
		
		record = taxProvider.getRecordByGUID(gUID);
		
		return record;
	}

	@Override
	public List<TaxVernacular> getVernacularsByGUID(String gUID,
			TaxProvider taxProvider) throws RemoteException, NotFoundException 
	{
		List<TaxVernacular> records = null;
		
		records = taxProvider.getVernacularsByGUID(gUID);
		
		return records;
	}

	@Override
	public List<TaxRecord> getSynonymsByGUID(String gUID, TaxProvider taxProvider) throws RemoteException, NotFoundException 
	{
		List<TaxRecord> records = null;
		
		records = taxProvider.getSynonimsByGUID(gUID);
		
		return records;
	}


}

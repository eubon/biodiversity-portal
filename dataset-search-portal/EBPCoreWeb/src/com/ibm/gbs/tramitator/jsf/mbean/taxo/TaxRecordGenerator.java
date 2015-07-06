package com.ibm.gbs.tramitator.jsf.mbean.taxo;

import PESI.v0_5.PESIRecord;
import PESI.v0_5.Vernacular;
import aphia.v1_0.AphiaRecord;

import com.ibm.gbs.eubon.taxonomic.TaxRecord;
import com.ibm.gbs.eubon.taxonomic.TaxVernacular;
import com.ibm.gbs.tramitator.util.Constantes;

public class TaxRecordGenerator 
{

	public TaxRecord generateTaxRecord(PESIRecord pesiRecord)
	{
		TaxRecord record = null;
		
		if (pesiRecord != null)
		{
			record = new TaxRecord();
			record.set_class(pesiRecord.get_class());
			record.setAuthority(pesiRecord.getAuthority());
			record.setCitation(pesiRecord.getCitation());
			record.setFamily(pesiRecord.getFamily());
			record.setGenus(pesiRecord.getGenus());
			record.setGUID(pesiRecord.getGUID());
			record.setKingdom(pesiRecord.getKingdom());
			record.setMatch_type(pesiRecord.getMatch_type());
			record.setOrder(pesiRecord.getOrder());
			record.setPhylum(pesiRecord.getPhylum());
			record.setRank(pesiRecord.getRank());
			record.setScientificname(pesiRecord.getScientificname());
			record.setStatus(pesiRecord.getStatus());
			record.setUrl(pesiRecord.getUrl());
			record.setValid_authority(pesiRecord.getValid_authority());
			record.setValid_guid(pesiRecord.getValid_guid());
			record.setValid_name(pesiRecord.getValid_name());
			
			String GUIDurl = record.getGUID();
			if (GUIDurl.contains("faunaeur.org"))
			{
				GUIDurl = GUIDurl.substring(30);
			}
			else 
			{
				GUIDurl = "";
			}
			
			record.setGUIDurl(Constantes.URL_FAUNAEUR + GUIDurl);
		}
		return record;
	}
	
	
	public TaxRecord generateTaxRecord(AphiaRecord aphiaRecord)
	{
		TaxRecord record = null;
		
		if (aphiaRecord != null)
		{
			record = new TaxRecord();
			record.set_class(aphiaRecord.get_class());
			record.setAuthority(aphiaRecord.getAuthority());
			record.setCitation(aphiaRecord.getCitation());
			record.setFamily(aphiaRecord.getFamily());
			record.setGenus(aphiaRecord.getGenus());
			String GUID = Integer.toString(aphiaRecord.getAphiaID());
			record.setGUID(GUID);
			record.setKingdom(aphiaRecord.getKingdom());
			record.setMatch_type(aphiaRecord.getMatch_type());
			record.setOrder(aphiaRecord.getOrder());
			record.setPhylum(aphiaRecord.getPhylum());
			record.setRank(aphiaRecord.getRank());
			record.setScientificname(aphiaRecord.getScientificname());
			record.setStatus(aphiaRecord.getStatus());
			record.setUrl(aphiaRecord.getUrl());
			record.setValid_authority(aphiaRecord.getValid_authority());
			GUID = Integer.toString(aphiaRecord.getValid_AphiaID());
			record.setValid_guid(GUID);
			record.setValid_name(aphiaRecord.getValid_name());	
			
			String GUIDurl = record.getUrl();			
			record.setGUIDurl(GUIDurl);
		}
		return record;
	}


	public TaxVernacular generateTaxVernacular(Vernacular pesiVernacular) 
	{
		TaxVernacular taxVernacular = null;
	
		
		if (pesiVernacular != null)
		{
			taxVernacular = new TaxVernacular();
			taxVernacular.setLanguage(pesiVernacular.getLanguage());
			taxVernacular.setLanguage_code(pesiVernacular.getLanguage_code());
			taxVernacular.setVernacular(pesiVernacular.getVernacular());			
		}
		
		return taxVernacular;
	}
	
	public TaxVernacular generateTaxVernacular(aphia.v1_0.Vernacular aphiaVernacular) 
	{
		TaxVernacular taxVernacular = null;
	
		
		if (aphiaVernacular != null)
		{
			taxVernacular = new TaxVernacular();
			taxVernacular.setLanguage(aphiaVernacular.getLanguage());
			taxVernacular.setLanguage_code(aphiaVernacular.getLanguage_code());
			taxVernacular.setVernacular(aphiaVernacular.getVernacular());			
		}
		
		return taxVernacular;
	}

}

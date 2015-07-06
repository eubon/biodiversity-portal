package com.ibm.gbs.ebp.core.dataset;

import javax.ejb.EJB;

import com.ibm.gbs.ebp.core.dataset.provider.CSWDataSetProvider;
import com.ibm.gbs.ebp.core.dataset.provider.DataSetProvider;
import com.ibm.gbs.ebp.core.dataset.provider.GbifDataSetProvider;
import com.ibm.gbs.eubon.ebp.core.jpa.entity.Provider;
import com.ibm.gbs.tramitator.ejb.crud.ServicioCRUD;
import com.ibm.gbs.tramitator.util.Constantes;
import com.ibm.gbs.tramitator.util.configuracion.Propiedades;


public class DataSetServiceImpl implements DataSetService
{

	@EJB
	private ServicioCRUD crud;

	public DataSetServiceImpl()
	{		
		super();
	}

	public DataSetServiceImpl(ServicioCRUD _crud)
	{
		crud = _crud;		
	}
	
	public DataSetProvider getProvider(long id)
	{
		DataSetProvider dataSetProvider = null;
		
		String idProvider = String.valueOf(id);
		
		Provider prov = (Provider)crud.obtenerObjeto(Provider.class, id);
		
/*		if (idProvider.equals(Propiedades.getPropiedad(Constantes.CFG_GBIFN_ID)))
		{
			dataSetProvider = new GbifDataSetProvider(prov);			
		}
		
		if (idProvider.equals(Propiedades.getPropiedad(Constantes.CFG_LTER_ID)))
		{
			dataSetProvider = new CSWDataSetProvider(prov);			
		}
	*/	
		return dataSetProvider;

		
	}

}

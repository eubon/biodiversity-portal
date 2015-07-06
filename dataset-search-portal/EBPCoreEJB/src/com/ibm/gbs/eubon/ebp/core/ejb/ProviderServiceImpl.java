package com.ibm.gbs.eubon.ebp.core.ejb;

import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import org.apache.log4j.Logger;

import com.ibm.gbs.eubon.ebp.core.dao.CategoryDAO;
import com.ibm.gbs.eubon.ebp.core.dao.ProviderDAO;
import com.ibm.gbs.eubon.ebp.core.jpa.entity.Category;
import com.ibm.gbs.eubon.ebp.core.jpa.entity.Content;
import com.ibm.gbs.eubon.ebp.core.jpa.entity.Function;
import com.ibm.gbs.eubon.ebp.core.jpa.entity.FunctionProvider;
import com.ibm.gbs.eubon.ebp.core.jpa.entity.Provider;
import com.ibm.gbs.tramitator.ejb.crud.ServicioCRUD;
import com.ibm.gbs.tramitator.jpa.util.Criterio;
import com.ibm.gbs.tramitator.util.log.InterceptorLog;

@Stateless
@Interceptors({ InterceptorLog.class })
public class ProviderServiceImpl implements ProviderService {

	Logger log = Logger.getLogger(ProviderServiceImpl.class);

	@EJB
	ProviderDAO providerDAO;

	@EJB
	ServicioCRUD crud;
	
	@Override
	public List<Provider> listadoPaginacion(Criterio criterio) {
		List<Provider> listado = providerDAO.listadoPaginacion(criterio);
		
		return listado;
	}

	@Override
	public int countPaginacion(Criterio criterio) {
		int countPaginacion = providerDAO.countPaginacion(criterio);
		
		return countPaginacion;
	}

	@Override
	public Provider guardar(Provider provider) {
		
		Provider c = (Provider) crud.guardar(provider);
		
		return c;	
		
	}

	@Override
	public Provider read(long id) {
		
		Provider provider = (Provider) providerDAO.read(id);
		
		if (provider!= null)
		{
		  for (Iterator iterator = provider.getFunctionProviders().iterator(); iterator.hasNext();) {
			FunctionProvider fp = (FunctionProvider) iterator.next();
			Function f = fp.getFunction();
			if (f != null)
			{
				f.getName();
			}
			
		}
		  log.debug("ProviderServiceImpl.getProvider - Provider=" + provider.getId() + "-" + provider.getName());
		}
		else
		{
			log.debug("ProviderServiceImpl.getProvider - Not found-" );
		}
			
		return provider;
	}
	
	@Override
	public List<Provider> getProviders() 
	{
		List<Provider> lista = providerDAO.getProviders();
		return lista;
	}	
	
	@Override
	public void delete(Provider provider) 
	{		
		crud.delete(provider);		
	}

}

package com.ibm.gbs.ebp.core.dataset;

import com.ibm.gbs.ebp.core.dataset.provider.DataSetProvider;

public interface DataSetService 
{
	
	public DataSetProvider getProvider(long id);
	
}

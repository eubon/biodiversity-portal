package com.ibm.gbs.ebp.core.dataset.provider;

import com.ibm.gbs.ebp.core.exception.ConnectionException;
import com.ibm.gbs.ebp.core.exception.NotFoundException;
import com.ibm.gbs.eubon.ebp.core.jpa.entity.Provider;
import com.ibm.gbs.eubon.filters.DataSetFilter;

public interface DataSetProvider {
		
	
	public Provider getProvider();
	
	public SearchResult getDataSets(DataSetFilter dataSetFilter) throws NotFoundException, ConnectionException;

}

package com.ibm.gbs.eubon.ebp.core.dao;

import java.util.List;

import com.ibm.gbs.eubon.ebp.core.jpa.entity.ServiceType;

public interface ServiceTypeDAO {

	
	ServiceType read(long id);
	
	public List<ServiceType> getServiceTypes();

}
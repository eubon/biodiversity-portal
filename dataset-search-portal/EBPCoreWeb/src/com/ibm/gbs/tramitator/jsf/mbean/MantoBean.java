package com.ibm.gbs.tramitator.jsf.mbean;

import com.ibm.gbs.tramitator.util.cache.Cache;

public class MantoBean {
	
	public String clearCache() {
		Cache.getInstancia().clear();
		return "";
	}

}

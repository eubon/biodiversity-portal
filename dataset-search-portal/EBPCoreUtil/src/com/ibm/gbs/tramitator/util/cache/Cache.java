package com.ibm.gbs.tramitator.util.cache;

import org.apache.log4j.Logger;

import com.vipan.util.ExpiringCache;

public class Cache {

	private static Cache instancia;
	
	private static Logger log = Logger.getLogger(Cache.class);
	private ExpiringCache cache; 
	
	private Cache(){
	}
	
	public static synchronized void init(){
		if (instancia == null){
			Cache inst = new Cache();
			final int timeToLive = 1800000; 
			final int cacheAccessTimeout = 1800000; 
			final int maxEntries = 100; 
			final int timerInterval = 300000;
			inst.cache = new ExpiringCache(timeToLive, cacheAccessTimeout, maxEntries, timerInterval);
			instancia = inst;
			log.info("Cache.init - Caché no iniciado, iniciando con valores por defecto, timeToLive " + timeToLive + ", cacheAccessTimeout " + cacheAccessTimeout + ", maxEntries " + maxEntries + ", timerInterval " + timerInterval);
		}
	}
	
	public static synchronized void init(long timeToLive, long cacheAccessTimeout, int maxEntries, long timerInterval){
		if (instancia == null){
			Cache inst = new Cache();
			inst.cache = new ExpiringCache(timeToLive, cacheAccessTimeout, maxEntries, timerInterval);
			instancia = inst;
			log.info("Cache.init - Caché iniciado, timeToLive " + timeToLive + ", cacheAccessTimeout " + cacheAccessTimeout + ", maxEntries " + maxEntries + ", timerInterval " + timerInterval);
		}
	}
	
	public static Cache getInstancia(){
		if (instancia == null)
			init();
		return instancia;
	}
	
	public void put(String clave, Object objeto){
		if (cache!=null)
			cache.admit(clave, objeto);
	}

	public Object get(String clave){
		Object entrada = null;
		if (cache!=null)
			entrada = cache.recover(clave);
		return entrada;
	}
	
	public void remove(String clave){
		cache.discard(clave);
	}
	
	public void clear(){
		cache.clear();
		log.debug("Cache.clear() - Caché eliminada");
	}

}

	
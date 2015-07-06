package com.ibm.gbs.tramitator.jsf.mbean.propiedades;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.ibm.gbs.tramitator.ejb.ServicioConfiguracion;
import com.ibm.gbs.tramitator.jpa.entity.Configuracion;
import com.ibm.gbs.tramitator.util.configuracion.Propiedades;

public class UtilPropiedades {
	public static synchronized void initProps(ServicioConfiguracion srvConfig) throws NamingException, IOException {
		String ruta = (String) InitialContext.doLookup("java:comp/env/cfgPath");
		Propiedades.inicializa(ruta);
		
		List<Configuracion> cfg = srvConfig.leer();
		Map<String, String> mapCfg = Configuracion.listConfiguracionAMap(cfg);
		Propiedades.inicializa(mapCfg);
	}
}

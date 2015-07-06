/**
 * License Agreement.
 *
 * Rich Faces - Natural Ajax for Java Server Faces (JSF)
 *
 * Copyright (C) 2007 Exadel, Inc.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License version 2.1 as published by the Free Software Foundation.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301  USA
 */



package com.ibm.gbs.tramitator.jsf.mbean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.ibm.gbs.tramitator.util.Constantes;
import com.ibm.gbs.tramitator.util.cache.Cache;
import com.ibm.gbs.tramitator.util.configuracion.Propiedades;

@ManagedBean(name = "cacheBean")
@SessionScoped
public class CacheBean implements Serializable{

	int dato;
	
	public int getDato(){
		dato++;
		return dato;
	}
	
	public int getDatoCache(){
		Integer datoCache = (Integer) Cache.getInstancia().get("pruebaCache.dato");
		if (datoCache==null){
			datoCache = dato;
			Cache.getInstancia().put("pruebaCache.dato", datoCache);
		}
		return datoCache;
	}
	
	public String getDatoPropiedades(){
		String dp = Propiedades.getPropiedad(Constantes.CFG_DATO_PRUEBA_PROPIEDADES);
		if (dp==null){
			dp = "(No existe la propiedad)";
		}
		return dp;
	}
	
	public String getDatoPropiedadesFichero(){
		String dp = Propiedades.getPropiedad(Constantes.CFG_DATO_PRUEBA_PROPIEDADES_FICHERO);
		if (dp==null){
			dp = "(No existe la propiedad)";
		}
		return dp;
	}	
	
	public String pruebaCache(){
		return "pruebaCache";
	}

}

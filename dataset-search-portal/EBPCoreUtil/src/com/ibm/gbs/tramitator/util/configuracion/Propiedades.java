package com.ibm.gbs.tramitator.util.configuracion;

/**
 Lee y Guarda Properties de un fichero de Properties.
 Clase que hereda de Properties.

 Establece las variables de configuración de un fichero de configuració�n
 que se recibe en un String en el constructor.

 Se usa para leer y guardar las variables de configuraci�ón.

 * Fecha de creación: (19/1/00 12:21:04)
 * @author: IBM
 */
 import java.util.*;
import java.io.*;

//import map.ces.intercepciones.EJBLocator;
//import map.ces.logica.UtilDao;
//import map.ces.modelo.Configuracion;

import org.apache.log4j.Logger;


public class Propiedades  {
	private Properties prop = new Properties();
	
	private static Propiedades instancia = null;
	
	
	
	Logger log = Logger.getLogger("map.ces.logica.utilidades.Propiedades");

/**
 Genera la instancia, recibe un fichero de properties y luego las lee.
 
 * Fecha de creación: (19/1/00 12:57:23)
 * @param fichero java.lang.String
 * @throws IOException
 */
public static synchronized void inicializa(String fichero) throws IOException 
{
	instancia = new Propiedades();
	instancia.prop = leerPropiedades(fichero);
}


public static synchronized void inicializa(Map<String, String> props) 
{
	if (instancia == null){
		instancia = new Propiedades();
	}
	
	
	for (String clave: props.keySet()){
		String valor = props.get(clave);
		if (valor!=null)
			instancia.prop.put(clave, valor);
	}
}

/**
 * Constructor privado
 *
 */
private Propiedades()
{
    super();
}

/**
Genera la instancia.

* Fecha de creación: (19/1/00 12:57:23)
* @param fichero java.lang.String
* @throws IOException
*/
public static synchronized void inicializa()
{
	instancia = new Propiedades();
	
	
}

/**
 * 
 * Propiedades
 * @return
 */
public static Propiedades getInstance()
{
    return instancia;
}

/**
 * 
 * Propiedades
 * @return
 * @throws IOException
 */
public static Propiedades getInstance(String fichero) throws IOException
{
    inicializa(fichero);
    return instancia;
}

/**
 * Devuelve la propiedad que se corresponde con la clave recibida.
 * String
 * @param clave
 * @return
 * @throws IOException
 */
public static String getPropiedad(String clave)
{
    String res = null;
	res = getInstance().prop.getProperty(clave);
    
	if (res == null)
	{
	    return "";
	}
	else
	{    
	    return res;
	}
}

/**
Lee Propiedades del fichero de propiedades definido.

* Fecha de creación: (19/1/00 12:44:45)
*/
public static Properties leerPropiedades(String fichero) throws IOException
{
	
	Properties p = null;
	
	FileInputStream fin = null;

	try 
	{
		fin = new FileInputStream(fichero);
	} 
	catch(FileNotFoundException e) 
	{
//		log.debug("Propiedades.leerPropiedades(): No se encuentra el archivo:"+ fichero+"-");		
		throw e;
	}

	try 
	{
		if(fin != null) 
		{
			p = new Properties();
			p.load(fin);
			fin.close();
		}
	}
	catch(IOException e) 
	{
//		log.debug("Propiedades.leerPropiedades(): Error en la lectura del archivo:"+ fichero+"-");
		throw e;
	}
	return p;
}

}
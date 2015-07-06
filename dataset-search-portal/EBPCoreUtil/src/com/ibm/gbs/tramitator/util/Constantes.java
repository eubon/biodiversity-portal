
package com.ibm.gbs.tramitator.util;

public class Constantes {
	
	public static final String appName = "EBP";
	public static final String version = "201503201338";
	
	public static final String cadena_vacia = "";
	public static final String no = "no";
	public static final String si = "si";
	
	public static final String DATOS_EN_CONTENIDO = "CONTENIDO";
	public static final String DATOS_EN_FIRMA = "FIRMA";
	
	public static final String CFG_MODIFICACION = "modificacion";
	public static final String CFG_INTERVALO_ACTUALIZACION_PROPIEDADES = "intervaloActualizacionPropiedades";
	public static final String CFG_DATO_PRUEBA_PROPIEDADES = "datoPruebaPropiedades";
	public static final String CFG_DATO_PRUEBA_PROPIEDADES_FICHERO = "datoPruebaPropiedadesFichero";
	
	public static final String CFG_AFIRMA_DUMMY = "firmaDummy";
	public static final String CFG_MAIL_ACTIVO = "mail.activo";
	public static final String CFG_VALIDAR_HASH = "envioDocumento.validarHash";

	//public static final String CFG_DEFAULT_LIMIT = "cfg.default.limit";
	public static final String CFG_DEFAULT_OCURRENCES_LIMIT = "cfg.default.ocurrences.limit";
	public static final String CFG_DEFAULT_SPECIES_LIMIT = "cfg.default.species.limit";
	public static final String CFG_DEFAULT_DATASET_LIMIT = "cfg.default.dataset.limit";
	
	public static final String CACHE_KEY_TRANSICIONES = "transiciones";
	public static final String CACHE_KEY_TRANSICIONES_ESTADO = "transicionesEstado";
	public static final String CACHE_KEY_ESTADOS = "estados";
	public static final String CACHE_KEY_ESTADOS_ENI = "estadosEni";
	public static final String CACHE_KEY_ESTADOS_REENVIO = "estadosReenvio";
	public static final String CACHE_KEY_MAP_TIPOS_DOCUMENTO = "mapTiposDocumento";
	public static final String CACHE_KEY_LIST_TIPOS_DOCUMENTO = "listTiposDocumento";
	public static final String CACHE_KEY_SELECT_ITEMS_TIPOS_DOCUMENTO = "siTiposDocumento";
	public static final String CACHE_KEY_SELECT_ITEMS_ESTADOS_ELABORACION = "estadosElaboracion";
	public static final String CACHE_KEY_TIPOS_CONTRATO = "tiposContrato";
	public static final String CACHE_KEY_SUBTIPOS_CONTRATO = "subtiposContrato";
	public static final String CACHE_KEY_TIPOS_TRAMITACION = "tiposTramitacion";
	public static final String CACHE_KEY_TIPOS_ADJUDICACION = "tiposAdjudicacion";
	public static final String CACHE_KEY_ALMACENES = "almacenes";
	public static final String CACHE_KEY_FAMILIAS_PRODUCTO = "familiasProducto";
	public static final String CACHE_KEY_TIPOS_PRODUCTO = "tiposProducto";
	public static final String CACHE_KEY_ACCION_ROL = "accionRol";
	public static final String CACHE_KEY_ROLES = "roles";
	public static final String CACHE_KEY_CLIENTETEAC = "clienteTeac";
	public static final String CACHE_KEY_TIPOS_DOCUMENTO = "tiposDocumento";
	public static final String CACHE_KEY_CODIGOS_RESPUESTA = "codigoRespuesta";
	public static final String CACHE_KEY_CODIGOS_RESPUESTA_ERROR = "codigoRespuestaError";
	
	public static final String CACHE_KEY_LIST_CATEGORIES = "categories";
	public static final String CACHE_KEY_LIST_CONTENTS = "contents";
	
	public static final int CODIGO_PROCEDIMIENTO = 1;
	
	public static final String FORMATO_FECHAHORA = "dd-MM-yyyy HH:mm:ss";
	
	public static final String AUDIT_LOGIN_BEAN_GETUSER = "LOGIN_BEAN_GETUSER";
	
	public static final String URL_FAUNAEUR = "http://www.faunaeur.org/full_results.php?id=";
	
	
	
	public static final String RUTA_PCAP = "/usr/local/procure/pcap.pdf";
	
	public static final String CFG_EUNOMEN_ID = "EUNOMEN_ID";
	
	public static final String CFG_WORMS_ID = "WORMS_ID";
	
	public static final String CFG_TAXONOMIC_PROVIDER_ID = "TAXONOMIC_PROVIDER_ID";
	public static final String CFG_DATASET_PROVIDER_ID = "DATASET_PROVIDER_ID";

	public static final String CFG_GBIFN_ID = "GBIF_ID";
	public static final String CFG_LTER_ID = "LTER_ID";

	
	public static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS";
	
	public static final String DATE_FORMAT_YYYY_MM_dd = "yyyy-MM-dd";

	public static final String INTERFACE_WEB = "WE";
	public static final String INTERFACE_SOAP = "SO";

	
	// Nombres de las etiquetas de la respuesta ResultSet de CSW
	public static final String CSW_SEARCHRESULTS = "csw:SearchResults";
	public static final String CSW_RECORD = "csw:Record";
	
	public static final String CSW_IDENTIFIER = "dc:identifier";
	public static final String CSW_DATE = "dc:date";
	public static final String CSW_TITLE = "dc:title";
	public static final String CSW_TYPE = "dc:type";
	public static final String CSW_SUBJECT = "dc:subject";
	public static final String CSW_MODIFIED = "dct:modified";
	public static final String CSW_DESCRIPTION = "dc:description";
	public static final String CSW_RIGHTS = "dc:rights";
	public static final String CSW_LANGUAGE = "dc:language";
	public static final String CSW_SOURCE = "dc:source";
	public static final String CSW_LOWERCORNER = "ows:LowerCorner";
	public static final String CSW_UPPERCORNER = "ows:UpperCorner";
	public static final String CSW_ABSTRACT = "dct:abstract";
	public static final String CSW_URI = "dc:URI";


	// Nombres de las operaciones de búsqueda en GBIF
	public static final String CFG_GBIF_DATASET_SEARCH = "cfg.gbif.dataset.search";
	public static final String CFG_GBIF_OCCURRENCE_SEARCH = "cfg.gbif.occurrence.search";
	public static final String CFG_GBIF_SPECIES_SEARCH = "cfg.gbif.species.search";

}

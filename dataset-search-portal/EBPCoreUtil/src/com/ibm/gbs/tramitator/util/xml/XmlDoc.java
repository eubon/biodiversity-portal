package com.ibm.gbs.tramitator.util.xml;

import java.io.IOException;
import java.io.StringReader;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * Esta clase sirve para parsear un documento xml.
 * 	Tiene métodos para pasar todos los valores del documento xml a un properties.
 * 	También puede dar valor a los campos del xml obteniendo los valores de un properties  .
 * 
 * @author IBM
 */
public class XmlDoc 
{
	/** Doc. **/
	private Document doc;
	
/**
 * Constructor. 
 */
public XmlDoc() {
	super();
}

/**
 * Obtiene un objeto properties con los valores que hay en el documento xml.
 * 
 * @return java.util.Properties
 */
public final java.util.Properties aProperties() 
{
	Properties datos = new Properties();
	
	aProperties(doc, datos);

	return datos;
}

/**
 * Obtiene un objeto properties con los valores que hay en el documento xml.
 * @param node node 
 * @param datos Datos
 */
private void aProperties(Node node, Properties datos) 
{
	int type = node.getNodeType();
	switch (type)
	{
		case Node.DOCUMENT_NODE:
			aProperties(((Document)node).getDocumentElement(), datos);
			break;
		case Node.ELEMENT_NODE:
			NodeList hijo = node.getChildNodes();
			if (hijo != null)
			{
				int len = hijo.getLength();
				for (int i = 0; i < len; i++)
				{
				   aProperties(hijo.item(i), datos);
				}
			}
			break;

		case Node.TEXT_NODE:
			datos.put(node.getParentNode().getNodeName(),node.getNodeValue());
			break;			
		default:
			break;
	}
}

/**
 * Obtiene el documento xml en un String.
 * 
 * @return String
 */
public  final String aString() 
{
	StringBuffer sDoc = new StringBuffer();
	aString(getDoc(),sDoc); 
	return sDoc.toString();
}

/**
 * Obtiene el documento xml en un String. 
 * 
 * @param node org.w3c.dom.Node
 * @param sDoc StringBuffer
 */
private void aString(Node node, StringBuffer sDoc) 
{
	int type = node.getNodeType();
	switch (type) 
	{
		case Node.DOCUMENT_NODE:
			aString(((Document) node).getDocumentElement(), sDoc);
			break;
		case Node.ELEMENT_NODE:
			sDoc.append("<" + node.getNodeName());
			NamedNodeMap attrs = node.getAttributes();
			for (int i = 0; i < attrs.getLength(); i++) {
				Node attr = attrs.item(i);
				sDoc.append(" " + attr.getNodeName() + "=\""
						+ attr.getNodeValue() + "\"");
			}
			sDoc.append(">");

			sDoc.append(aStringChildren(node));
			break;
		case Node.ENTITY_REFERENCE_NODE:
			sDoc.append("&" + node.getNodeName() + ";");
			break;
		case Node.CDATA_SECTION_NODE:
			sDoc.append("<![CDATA[" + node.getNodeValue() + "]]>");
			break;
		case Node.TEXT_NODE:
			sDoc.append(node.getNodeValue());
			break;
		case Node.PROCESSING_INSTRUCTION_NODE:
			sDoc.append("<?" + node.getNodeName());
			String data = node.getNodeValue();
			sDoc.append(" " + data + "?>");
			break;
		default:
			break;
	}
	
	if (type == Node.ELEMENT_NODE)
	{
		sDoc.append("</" + node.getNodeName() + ">");
	}
}

/**
 * Metodo para obtener los hijos.
 * @param node Node
 * @return String
 */
private String aStringChildren(Node node) {
	StringBuffer sDoc = new StringBuffer();
	NodeList hijos = node.getChildNodes();
	if (hijos != null)
	{
		int len = hijos.getLength();
		for (int i = 0; i < len; i++)
		{
		   aString(hijos.item(i), sDoc);
		}
	}

	return sDoc.toString();
}

/**
 * Se modifican los valores del documento xml con los valores del Properties.
 * 
 * @param datos java.util.Properties
 */
public final  void darValorXML(java.util.Properties datos) 
{
	darValorXML(getDoc(),datos);
}

/**
 * Se modifican los valores del documento xml con los valores del Properties.
 * 
 * @param node  Node
 * @param datos java.util.Properties
 */
private void darValorXML(Node node, java.util.Properties datos) 
{
	int type = node.getNodeType();
	switch (type)
	{
		case Node.DOCUMENT_NODE:
			darValorXML(((Document)node).getDocumentElement(), datos);
			break;
		case Node.ELEMENT_NODE:
			NodeList children = node.getChildNodes();
			if (children != null)
			{
				int len = children.getLength();
				for (int i = 0; i < len; i++)
				{
				   darValorXML(children.item(i), datos);
				}
			}
			break;
		case Node.TEXT_NODE:
			String valor = datos.getProperty(node.getParentNode().getNodeName(),node.getNodeValue());
			node.setNodeValue(valor);
			break;			
		default:
			break;
	}

}
/**
 * Devuelve el objeto Document que contiene el �rbol del xml.
 * 
 * @return org.w3c.dom.Document
 */
public final org.w3c.dom.Document getDoc() {
	return doc;
}

/**
 * Establece el objeto Document que contiene el árbol del xml.
 * 
 * @param d org.w3c.dom.Document
 */
public final void setDoc(Document d) {
	doc = d;
}

/**
 * Parsea el documento xml que viene en un String y lo establece como documento para seguir 
 * tratándolo posteriormente como si le hubieramos llamado con setDoc(doc).
 * 
 * @return boolean
 * @param xmlString java.lang.String
 */
public final boolean parseXmlString(String xmlString) 
{
	boolean parseado = false;
	
	StringReader sr = new StringReader(xmlString);
	InputSource iSrc = new InputSource(sr);
	try
	{
		//DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		//DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		//doc = dBuilder.parse(iSrc);
		
		DOMParser parser = new DOMParser();
		parser.parse(iSrc);
		doc = parser.getDocument();
		doc.getDocumentElement().normalize();
		
		parseado = true;
	}
	catch (IOException e)
	{
		parseado = false;
	} catch (SAXException e) {
		parseado = false;
	}	
	return parseado;
}


}
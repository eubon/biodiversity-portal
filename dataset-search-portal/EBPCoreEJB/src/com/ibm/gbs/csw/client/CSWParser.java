package com.ibm.gbs.csw.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.ibm.gbs.csw.client.data.CSWRecord;
import com.ibm.gbs.csw.client.data.CSWSearchResult;
import com.ibm.gbs.csw.client.data.CSWUri;
import com.ibm.gbs.tramitator.util.Constantes;
import com.ibm.gbs.tramitator.util.DateUtil;
import com.ibm.gbs.tramitator.util.xml.XmlDoc;

public class CSWParser {

	Logger logger = Logger.getLogger("com.ibm.gbs");
	
	
	public CSWSearchResult parseSearchResult(String xml) 
	{
		CSWSearchResult sr = null;
		
		XmlDoc xmlDoc = new XmlDoc();
		boolean parseado = xmlDoc.parseXmlString(xml);
		
		if (parseado)
		{
			sr = new CSWSearchResult();
						
			sr = parseCSWSearchResult(xmlDoc.getDoc(), sr);
			
			logger.debug("CSWParser.parseSearchResult(): xml= -");			
		}
				
		return sr;
	}
	
	private CSWSearchResult parseCSWSearchResult(Node node, CSWSearchResult sr) 
	{
		
		int type = node.getNodeType();
		switch (type) 
		{
			case Node.DOCUMENT_NODE:
				sr = parseCSWSearchResult(((Document) node).getDocumentElement(), sr);
				break;
			case Node.ELEMENT_NODE:
				//sDoc.append("<" + node.getNodeName());
				if (node.getNodeName().equals(Constantes.CSW_SEARCHRESULTS))
				{
					//logger.debug("CSWParser.parseCSWSearchResult(): = csw:SearchResults-");	
					sr = parseSR(node, sr);
				}
				else if (node.getNodeName().equals(Constantes.CSW_RECORD)) 
				{
					//logger.debug("CSWParser.parseSearchResult(): csw:Record= -");
					sr = parseRecord(node, sr);					
				}
				sr = parseChildren(node, sr);
				break;
			case Node.ENTITY_REFERENCE_NODE:
				//sDoc.append("&" + node.getNodeName() + ";");
				break;
			case Node.CDATA_SECTION_NODE:
				//sDoc.append("<![CDATA[" + node.getNodeValue() + "]]>");
				break;
			case Node.TEXT_NODE:
				//sDoc.append(node.getNodeValue());
				break;
			case Node.PROCESSING_INSTRUCTION_NODE:
				//sDoc.append("<?" + node.getNodeName());
				//String data = node.getNodeValue();
				//sDoc.append(" " + data + "?>");
				break;
			default:
				break;
		}
		
		return sr;
	}

	private CSWSearchResult parseChildren(Node node, CSWSearchResult sr) {
		NodeList hijos = node.getChildNodes();
		if (hijos != null)
		{
			int len = hijos.getLength();
			for (int i = 0; i < len; i++)
			{
			   sr = parseCSWSearchResult(hijos.item(i), sr);
			}
		}
		return sr;
	}

	private CSWSearchResult parseRecord(Node node, CSWSearchResult sr) {
		logger.debug("CSWParser.parseRecord(): node="+node.getNodeName() +"-");
		
		CSWRecord record = new CSWRecord();
				 
				Element eElement = (Element) node;
				
				NodeList nodes = eElement.getElementsByTagName(Constantes.CSW_IDENTIFIER);
				if (nodes != null && nodes.getLength() > 0)
				{
					record.setIdentifier(nodes.item(0).getTextContent());
				}

				nodes = eElement.getElementsByTagName(Constantes.CSW_DATE);
				if (nodes != null && nodes.getLength() > 0)
				{
					record.setIdentifier(nodes.item(0).getTextContent());
					
					String sdate = nodes.item(0).getTextContent();
					Date d = DateUtil.parseDate(sdate, Constantes.DATE_FORMAT_YYYY_MM_dd);
					record.setDate(d);
				}
				
				nodes = eElement.getElementsByTagName(Constantes.CSW_ABSTRACT);
				if (nodes != null && nodes.getLength() > 0)
				{
					record.setAbstractTxt(nodes.item(0).getTextContent());
				}
				
				nodes = eElement.getElementsByTagName(Constantes.CSW_TITLE);
				if (nodes != null && nodes.getLength() > 0)
				{
					record.setTitle(nodes.item(0).getTextContent());
				}
				
				nodes = eElement.getElementsByTagName(Constantes.CSW_TYPE);
				if (nodes != null && nodes.getLength() > 0)
				{
					record.setType(nodes.item(0).getTextContent());
				}
				
				nodes = eElement.getElementsByTagName(Constantes.CSW_SUBJECT);
				if (nodes != null && nodes.getLength() > 0)
				{
					List<String> subs = new ArrayList<String>();
					for (int i = 0; i < nodes.getLength(); i++) 
					{
						subs.add(nodes.item(i).getTextContent());
					}
					record.setSubjects(subs);
				}

//				nodes = eElement.getElementsByTagName(Constantes.CSW_MODIFIED);
//				if (nodes != null && nodes.getLength() > 0)
//				{
//					String smod = nodes.item(0).getTextContent();
//					Date m = DateUtil.parseDate(smod, Constantes.DATE_FORMAT);
//					record.setModified(m);
//				}
				
				nodes = eElement.getElementsByTagName(Constantes.CSW_DESCRIPTION);
				if (nodes != null && nodes.getLength() > 0)
				{
					record.setDescription(nodes.item(0).getTextContent());
				}
				
				nodes = eElement.getElementsByTagName(Constantes.CSW_RIGHTS);
				if (nodes != null && nodes.getLength() > 0)
				{
					record.setRights(nodes.item(0).getTextContent());
				}
	 
				nodes = eElement.getElementsByTagName(Constantes.CSW_LANGUAGE);
				if (nodes != null && nodes.getLength() > 0)
				{
					record.setLanguage(nodes.item(0).getTextContent());
				}
				
				nodes = eElement.getElementsByTagName(Constantes.CSW_SOURCE);
				if (nodes != null && nodes.getLength() > 0)
				{
					record.setSource(nodes.item(0).getTextContent());
				}
				
				nodes = eElement.getElementsByTagName(Constantes.CSW_URI);
				if (nodes != null && nodes.getLength() > 0)
				{
					List<CSWUri> uris= new ArrayList<CSWUri>();
					for (int i = 0; i < nodes.getLength(); i++) 
					{
						CSWUri uri = new CSWUri();
						uri.setUri(nodes.item(i).getTextContent());
						
						Element eElementuri = (Element) nodes.item(i);
						
						uri.setName(eElementuri.getAttribute("name"));
						uris.add(uri);
					}
					record.setUris(uris);
				}
				
				sr.getRecords().add(record);
		
		return sr;
	}

	private CSWSearchResult parseSR(Node node, CSWSearchResult sr) {
		logger.info("CSWParser.parseSR(): node="+node.getNodeName() +"-");
		
		Element eElement = (Element) node;
		
		long norm = Long.parseLong(eElement.getAttribute("numberOfRecordsMatched"));
		sr.setNumberOfRecordsMatched(norm);
		
		long norr = Long.parseLong(eElement.getAttribute("numberOfRecordsReturned"));
		sr.setNumberOfRecordsReturned(norr);
		
		long nr = Long.parseLong(eElement.getAttribute("nextRecord"));
		sr.setNextRecord(nr);
		
/*		NamedNodeMap attrs = node.getAttributes();		 
		for (int i = 0; i < attrs.getLength(); i++) {
			Node attr = attrs.item(i);
			if (attr.getNodeName().equals("numberOfRecordsMatched"))
			{
				long norm = Long.parseLong(attr.getNodeValue());
				sr.setNumberOfRecordsMatched(norm);
			}
			if (attr.getNodeName().equals("numberOfRecordsReturned"))
			{
				long norr = Long.parseLong(attr.getNodeValue());
				sr.setNumberOfRecordsReturned(norr);
			}
			if (attr.getNodeName().equals("nextRecord"))
			{
				long nr = Long.parseLong(attr.getNodeValue());
				sr.setNextRecord(nr);
			}
		}		
*/
		
		return sr;
	}

}

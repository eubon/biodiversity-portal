package com.ibm.gbs.tramitator.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

public class DateUtil {

	public static XMLGregorianCalendar date2XMLGregorianCalendar(Date date) throws RuntimeException
	{
		XMLGregorianCalendar res = null;
		try {

            GregorianCalendar cale = (GregorianCalendar) GregorianCalendar.getInstance();
            cale.setTime(new Date());
            res = DatatypeFactory.newInstance().newXMLGregorianCalendar(cale);

		} catch (DatatypeConfigurationException e) {

            e.printStackTrace();
            throw new RuntimeException(e);
      }


		return res;
	}
	
	
	public static Date xMLGregorianCalendar2Date(XMLGregorianCalendar xgc)
	{
		GregorianCalendar gc = xgc!=null?xgc.toGregorianCalendar():null;
		return gc!=null?gc.getTime():null;
	}
	
	
	public static Date parseDate(String sDate, String format)
	{    
		Date date = null;
		if (sDate != null && !sDate.equals(Constantes.cadena_vacia))
		{
	        SimpleDateFormat sdf = new SimpleDateFormat(format);
	        
			try 
			{
				date = sdf.parse(sDate);
			} catch (java.text.ParseException e) 
			{
				e.printStackTrace();
				date = null;
			}
		}
		return date;
	}
	
}

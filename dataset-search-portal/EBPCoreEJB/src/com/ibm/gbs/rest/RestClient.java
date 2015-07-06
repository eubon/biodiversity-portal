package com.ibm.gbs.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.ibm.gbs.ebp.core.exception.ConnectionException;

public class RestClient 
{
	Logger logger = Logger.getLogger("com.ibm.gbs");
	
	public String doGet(String uri) throws ConnectionException
	{		
		String res = "";
		try {
			 
			URL url = new URL(uri);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection(); 
			connection.setRequestMethod("GET");
			//connection.setRequestProperty("", "application/xml");   
			//JAXBContex jc = JAXBContext.newInstance(Customer.class); 
			InputStream is = connection.getInputStream(); 
			//Customer customer = (Customer) jc.createUnmarshaller().unmarshal(xml);   
			
			res = getStringFromInputStream(is);
			
			
			logger.debug("RestClient.doGet(): res=" + res+"-");			
			
			connection.disconnect();
			
		} catch (Exception e) {
			throw new ConnectionException();
		}
		
		return res;
	}
	
	private static String getStringFromInputStream(InputStream is) {
		 
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
 
		String line;
		try {
 
			br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
 
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		/* codificación a UTF-8 
		 * pero parece que lo hace por defecto
		 * 
		byte[] ptext = null;
		try {
			ptext = sb.toString().getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			//  Auto-generated catch block
			e.printStackTrace();
		}
		
		String res = new String(ptext);
		*/
		return sb.toString();
 
	}

}

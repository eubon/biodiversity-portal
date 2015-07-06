package com.ibm.gbs.gbif.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;

import javax.xml.bind.JAXBContext;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class GbifTest {

	public static void main(String[] args) 
	{
		
		try {
			
			String uri = "http://localhost:38080/v1/dataset/search?q=plant&publishingCountry=AR"; 
			URL url = new URL(uri);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection(); 
			connection.setRequestMethod("GET");
			//connection.setRequestProperty("", "application/xml");   
			//JAXBContext jc = JAXBContext.newInstance(Customer.class); 
			InputStream xml = connection.getInputStream(); 
			//Customer customer = (Customer) jc.createUnmarshaller().unmarshal(xml);   
			
			String res = getStringFromInputStream(xml);
			
			
			
			JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(res);
            
            Long offset = (Long)jsonObject.get("offset");

			System.out.println(res);
			System.out.println("offset="+offset);
			
			JSONArray results = (JSONArray)jsonObject.get("results");
			System.out.println("results="+results);
			
			for (Iterator iterator = results.iterator(); iterator.hasNext();) {
				JSONObject fila = (JSONObject) iterator.next();
				String key = (String)fila.get("key");
				System.out.println("key="+key);
				
			}
			
			
			connection.disconnect();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

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
 
		return sb.toString();
 
	}

}

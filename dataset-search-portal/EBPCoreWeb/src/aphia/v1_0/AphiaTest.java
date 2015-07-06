package aphia.v1_0;

import java.rmi.RemoteException;
import java.util.Calendar;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;


public class AphiaTest {
	
	private static void initLog() {
		String rutaLog="/usr/local/ebp/log4j.xml";
		DOMConfigurator.configure(rutaLog);
	}
	
	
	public static void main(String[] args) 
	{
		String scientificName = "lynx";
		boolean like = false;
		boolean fuzzy = false;
		boolean marineOnly = false;
		int offset = 0;
		
		String result = "no";
		int aphiaID = 0;
		
		Logger logger = Logger.getLogger("com.ibm.gbs");
		
		logger.debug("PESITest.main:()Logger"); 
		
		
		 logger.debug("AphiaTest.main(): scientificName=" + scientificName + "- like=" + like + "-");
	        result = "Taxonomic Search Result - " + Calendar.getInstance().getTime() + "-"; 
	        
	        try {
	        	
	        	AphiaNameServicePortTypeProxy proxy = new AphiaNameServicePortTypeProxy();
	        	proxy.setEndpoint("http://localhost:18080/aphia.php?p=soap");
	        
	        	try
	        	{
		        	aphiaID = proxy.getAphiaID(scientificName, false);
	        		
	        	}catch (Exception e)
	        	{
					logger.debug("AphiaTest.main(): exception:" + e + "-");
	        	}
				logger.debug("AphiaTest.main(): getGUID: result=" + result + "-");
				
				result = proxy.getAphiaNameByID(aphiaID);
				
				logger.debug("AphiaTest.main(): getAphiaNameByID: result=" + result + "-");

				AphiaRecord record2[] = proxy.getAphiaRecords(scientificName, like, fuzzy, marineOnly, offset);
				
				logger.debug("AphiaTest.main(): getAphiaRecords: result=" + record2 + "-");
				
				AphiaRecord record = proxy.getAphiaRecordByID(aphiaID);
				
				
				logger.debug("AphiaTest.main(): getAphiaRecordByID: result=" + record + "-");

				
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	        
	        logger.debug("TaxoBean.taxSearch(): result=" + result + "-");

	}

}

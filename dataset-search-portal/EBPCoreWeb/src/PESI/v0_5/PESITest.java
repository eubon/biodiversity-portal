package PESI.v0_5;

import java.rmi.RemoteException;
import java.util.Calendar;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.xml.parsers.FactoryConfigurationError;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import PESIv0_5.PESINameServicePortTypeProxy;
import PESIv0_5.PESIRecord;

public class PESITest {

	private static void initLog() {
		String rutaLog="/usr/local/ebp/log4j.xml";
		DOMConfigurator.configure(rutaLog);
	}
	
	
	public static void main(String[] args) 
	{
		String scientificName = "lynx";
		boolean like = false;
		String result = "no";
		
		initLog();
		Logger logger = Logger.getLogger("com.ibm.gbs");
		
		logger.debug("PESITest.main:()Logger"); 
		 logger.debug("TaxoBean.taxSearch(): scientificName=" + scientificName + "- like=" + like + "-");
	        result = "Taxonomic Search Result - " + Calendar.getInstance().getTime() + "-"; 
	        
	        try {
	        	
	        	PESINameServicePortTypeProxy PESIProxy = new PESINameServicePortTypeProxy();
	        	PESIProxy.setEndpoint("http://localhost:28080/portal/soap.php");
//	        PESINameServiceLocator PESILocator = new PESINameServiceLocator();
	  //      PESINameServicePortType PESIport = PESILocator.getPESINameServicePort();
	        
	        
				result = PESIProxy.getGUID(scientificName);
				logger.debug("TaxoBean.taxSearch(): getGUID: result=" + result + "-");
				
				result = PESIProxy.getPESINameByGUID(result);
				
				logger.debug("TaxoBean.taxSearch(): getPESINameByGUID: result=" + result + "-");

				PESIRecord record2[] = PESIProxy.getPESIRecords(scientificName, like);
				
				logger.debug("TaxoBean.taxSearch(): getPESIRecords: result=" + record2 + "-");
				
				PESIRecord record = PESIProxy.getPESIRecordByGUID(result);
				
				
				logger.debug("TaxoBean.taxSearch(): getPESIRecordByGUID: result=" + record + "-");

				
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	        
	        logger.debug("TaxoBean.taxSearch(): result=" + result + "-");

	}

}

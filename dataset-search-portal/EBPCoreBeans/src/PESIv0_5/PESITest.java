package PESIv0_5;

import java.rmi.RemoteException;
import java.util.Calendar;

import PESIv0_5.PESINameServicePortTypeProxy;
import PESIv0_5.PESIRecord;

public class PESITest {

	public static void main(String[] args) 
	{
		String scientificName = "lynx";
		boolean like = false;
		String result = "no";
		
		 System.out.println("TaxoBean.taxSearch(): scientificName=" + scientificName + "- like=" + like + "-");
	        result = "Taxonomic Search Result - " + Calendar.getInstance().getTime() + "-"; 
	        
	        try {
	        	
	        	PESINameServicePortTypeProxy PESIProxy = new PESINameServicePortTypeProxy();
	        	PESIProxy.setEndpoint("http://localhost:28080/portal/soap.php");
//	        PESINameServiceLocator PESILocator = new PESINameServiceLocator();
	  //      PESINameServicePortType PESIport = PESILocator.getPESINameServicePort();
	        
	        
				result = PESIProxy.getGUID(scientificName);
				System.out.println("TaxoBean.taxSearch(): getGUID: result=" + result + "-");
				
				result = PESIProxy.getPESINameByGUID(result);
				
				System.out.println("TaxoBean.taxSearch(): getPESINameByGUID: result=" + result + "-");

				PESIRecord record2[] = PESIProxy.getPESIRecords(scientificName, like);
				
				System.out.println("TaxoBean.taxSearch(): getPESIRecords: result=" + record2 + "-");
				
				PESIRecord record = PESIProxy.getPESIRecordByGUID(result);
				
				
				System.out.println("TaxoBean.taxSearch(): getPESIRecordByGUID: result=" + record + "-");

				
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	        
	        System.out.println("TaxoBean.taxSearch(): result=" + result + "-");

	}

}

package com.ibm.gbs.tramitator.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.log4j.Logger;

public class StreamUtil {

	private static final Logger LOG = Logger.getLogger(StreamUtil.class);

	  public static void inputStreamToOutputStream(InputStream is, OutputStream os)
	  {
		  try
		  {
			  byte buf[]=new byte[1024];
			  int len;
			  while((len=is.read(buf))>0)
				  os.write(buf,0,len);
			  os.close();
			  is.close();
		  }
		  catch (IOException e){
			  LOG.error("StreamUtil.inputStreamToOutputStream(): Se ha producido un error al copiar los datos", e);
		  }
	  }
}

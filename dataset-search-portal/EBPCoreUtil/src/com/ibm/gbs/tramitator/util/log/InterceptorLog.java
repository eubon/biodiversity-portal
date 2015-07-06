package com.ibm.gbs.tramitator.util.log;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import org.apache.log4j.Logger;

import com.ibm.gbs.tramitator.util.rendimiento.LogTime;

public class InterceptorLog {

	Logger log = Logger.getLogger(InterceptorLog.class);	
	
	public InterceptorLog() {
		super();
	}

	@AroundInvoke
	public final Object log(InvocationContext ic) throws Exception 
	{
//		log.debug(ic.getMethod().getName() + " - inicio");
		LogTime logTime = new LogTime();
		logTime.setBegin();
		Object res = ic.proceed();
		logTime.setEnd();
		if(log.isDebugEnabled())
		{
		   log.debug(ic.getMethod().getDeclaringClass().getSimpleName() + "." + ic.getMethod().getName()+ "() - TIEMPO_PROCESO - " + logTime.getInterval());
		}
		return res;
	}

}

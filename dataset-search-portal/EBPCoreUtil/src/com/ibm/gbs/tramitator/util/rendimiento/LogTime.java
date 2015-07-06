package com.ibm.gbs.tramitator.util.rendimiento;

public class LogTime 
{
	private long i = 0;
	private long f = 0;
	
	public LogTime()
	{
		i = System.currentTimeMillis();
	}

	/**
	 * Fija el Timestamp del final.
	 * @param _end Final
	 */
	public final void setEnd() {
		f = System.currentTimeMillis();
	}

	public void setBegin() {
		i = System.currentTimeMillis();
	}
	
	/**
	 * Devuelve la diferencia de tiempos.
	 * @return Diferencia
	 */
	public final long getInterval() {
		if (f == 0)
			setEnd();
		return f - i;
	}

	/**
	 * Devuelve el String de la clase.
	 * @return String de la clase
	 */
	public final String toString() {		
		//return "LogTime:Begin="+getBegin()+"*End="+getEnd()+"*Interval(ns)="+getInterval()+"=";	
		return "LogTime:=Interval(ms)=" + getInterval() + "=";
	}


	
}

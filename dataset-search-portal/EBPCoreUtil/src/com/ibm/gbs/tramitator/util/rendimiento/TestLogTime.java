package com.ibm.gbs.tramitator.util.rendimiento;

public class TestLogTime {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// 
		LogTime logTime = new LogTime();
		logTime.setBegin();
		
		ejecucionProceso();
		logTime.setEnd();
		
		long duracion = logTime.getInterval();
		
		System.out.println("TestLogTime.main():" + duracion + "-");

	}

	private static void ejecucionProceso() {
		int l=100;
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < l; j++) {
				for (int k = 0; k < 25; k++) {
					
				String s1 = new String("Rochester");
					
				String s2 = new String("Rochester");
				}
			
		}
		}
	}

}

package com.ibm.gbs.tramitator.ejb.timers;

import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

public interface TimerPropertiesUpdater {

	/**
	 * 
	 */
	void shutDownTimer();

	/**
	 * 
	 */
	void startUpTimer();

	@Timeout
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	void execute(Timer timer);

}